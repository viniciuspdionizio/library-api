package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.LoanMapper;
import com.elotech.viniciuspdionizio.library_api.repository.BookRepository;
import com.elotech.viniciuspdionizio.library_api.repository.LoanRepository;
import com.elotech.viniciuspdionizio.library_api.repository.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    /**
     * Recupera todos os empréstimos com base em filtros opcionais.
     * Este método busca todos os empréstimos no sistema. Você pode filtrar os resultados
     * por ID de usuário {@code userId} e/ou ID de livro {@code bookId}. O status do empréstimo
     * é obrigatório e os resultados são retornados em uma página, controlada pelo objeto {@link Pageable}.
     *
     * @param userId   O ID do usuário cujos empréstimos serão buscados. Pode ser nulo.
     * @param bookId   O ID do livro cujos empréstimos serão buscados. Pode ser nulo.
     * @param status   O status do empréstimo. Não pode ser nulo.
     * @param pageable Um objeto `Pageable` para controlar a paginação e a ordenação dos resultados. Não pode ser nulo.
     * @return Uma página de objetos {@link LoanResponseDTO} com os empréstimos encontrados.
     */
    public Page<LoanResponseDTO> getAll(@Nullable Integer userId, @Nullable Integer bookId,
                                        @Nonnull Boolean status, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().and(Sort.by("id")));
        return this.loanRepository.findAll(userId, bookId, status, pageable).map(loanMapper::toDTO);
    }

    /**
     * Registra um novo empréstimo.
     * Este método cria um novo empréstimo com os dados fornecidos em {@link LoanRequestDTO}.
     * O empréstimo será adicionado ao sistema e um objeto {@link LoanResponseDTO} representando
     * o empréstimo registrado será retornado.
     *
     * @param requestData Os dados do empréstimo a ser registrado. Não pode ser nulo.
     * @return Um objeto {@link LoanResponseDTO} do empréstimo que foi registrado.
     */
    @Transactional
    public LoanResponseDTO register(@Nonnull LoanRequestDTO requestData) {
        this.checkIfExists(requestData.userId(), requestData.bookId());
        var entity = this.loanMapper.toEntity(requestData);
        this.loanRepository.registerLoan(entity);
        return this.loanMapper.toDTO(entity);
    }

    /**
     * Atualiza os dados de um empréstimo existente.
     * Este método modifica as informações de um empréstimo identificado pelo {@code id}.
     * Pelo menos um dos parâmetros {@code status} ou {@code returnDate} deve ser fornecido. Se ambos forem nulos, uma exceção {@link IllegalArgumentException} é lançada.
     * O empréstimo é buscado no repositório, e se não for encontrado, uma exceção {@link ObjectNotFoundException} é lançada. Um objeto
     *
     * @param id         O ID do empréstimo a ser atualizado. Não pode ser nulo.
     * @param status     O novo status do empréstimo. Pode ser nulo.
     * @param returnDate A nova data de devolução. Pode ser nula.
     * @return Um objeto {@link LoanResponseDTO} do empréstimo que foi atualizado.
     * @throws IllegalArgumentException Se ambos os parâmetros {@code status} e {@code returnDate} forem nulos.
     * @throws ObjectNotFoundException  Se o empréstimo com o ID fornecido não existir.
     */
    @Transactional
    public LoanResponseDTO update(@Nonnull Integer id, @Nullable Boolean status, @Nullable LocalDate returnDate) {
        if (Stream.of(status, returnDate).allMatch(Objects::isNull))
            throw new IllegalArgumentException("At least one param should be not null [%s, %s]".formatted("status", "returnDate"));
        var entity = this.loanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Empréstimo"));
        if (status != null) entity.setStatus(status);
        if (returnDate != null) entity.setReturnDate(returnDate);
        return this.loanMapper.toDTO(entity);
    }

    /**
     * Verifica se um usuário e um livro existem no sistema.
     * Este método checa a existência de um usuário com o ID fornecido e de um livro com o ID fornecido.
     * Se o usuário ou o livro não forem encontrados, uma exceção {@link ObjectNotFoundException} é lançada.
     *
     * @param userId O ID do usuário a ser verificado. Pode ser nulo.
     * @param bookId O ID do livro a ser verificado. Pode ser nulo.
     * @throws ObjectNotFoundException Se o usuário com o ID fornecido não existir ou se
     *                                 o livro com o ID fornecido não existir.
     */
    private void checkIfExists(Integer userId, Integer bookId) throws ObjectNotFoundException {
        if (userId != null && !this.userRepository.existsById(userId))
            throw new ObjectNotFoundException(userId, "Usuário");
        if (bookId != null && !this.bookRepository.existsById(bookId))
            throw new ObjectNotFoundException(bookId, "Livro");
    }

}
