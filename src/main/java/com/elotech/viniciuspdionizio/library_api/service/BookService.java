package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.config.exception.LoanExistsActiveException;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.BookMapper;
import com.elotech.viniciuspdionizio.library_api.repository.BookRepository;
import com.elotech.viniciuspdionizio.library_api.repository.LoanRepository;
import com.elotech.viniciuspdionizio.library_api.repository.UserRepository;
import com.elotech.viniciuspdionizio.library_api.util.PageableUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final BookMapper bookMapper;

    /**
     * Busca um livro pelo ID.
     * Este método tenta encontrar um livro no repositório usando o ID que você passar.
     * Se o livro for encontrado, ele é convertido para um objeto `BookResponseDTO` e retornado.
     * Se não encontrar o livro, uma exceção `ObjectNotFoundException` é lançada.
     *
     * @param id O ID do livro que você quer buscar. Não pode ser nulo.
     * @return Um objeto `BookResponseDTO` do livro encontrado.
     * @throws ObjectNotFoundException Se não encontrar o livro com o ID informado.
     */
    public BookResponseDTO getById(@Nonnull Integer id) {
        return this.bookRepository.findById(id)
                .map(this.bookMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Livro"));
    }

    /**
     * Recupera todos os livros com base nos filtros aplicados.
     * Este método busca todos os livros do repositório. Você pode passar um filtro
     * para buscar livros específicos e um parâmetro de status para filtrar por
     * livros ativos ou inativos. Os resultados são retornados em uma página, que
     * pode ser controlada pelo objeto `Pageable`.
     *
     * @param filter   Um filtro opcional para buscar livros específicos. Pode ser nulo.
     * @param status   Um boolean que indica se deseja buscar apenas livros ativos (não emprestados) ou inativos (já emprestados), caso informe null, buscará ambos.
     * @param pageable Um objeto `Pageable` para controlar a paginação e a ordenação dos resultados. Não pode ser nulo.
     * @return Uma página de objetos {@link BookResponseDTO} com os livros encontrados.
     */
    public Page<BookResponseDTO> getAll(@Nullable String filter, Boolean status, @Nonnull Pageable pageable) {
        pageable = PageableUtil.addSort(pageable, "id");
        return this.bookRepository.findAll(filter, status, pageable).map(this.bookMapper::toDTO);
    }

    /**
     * Obtém recomendações de livros para um usuário específico.
     * Este método busca livros recomendados com base nas preferências do usuário
     * identificado pelo `userId`.
     *
     * @param userId O ID do usuário para quem as recomendações são feitas. Não pode ser nulo.
     * @return Uma lista de objetos `BookResponseDTO` com as recomendações de livros.
     */
    public List<BookResponseDTO> getRecommendationsByUserId(@Nonnull Integer userId) {
        this.checkIfExists(userId);
        return this.bookRepository.findAllRecommendations(userId)
                .stream().map(this.bookMapper::toDTO).toList();
    }

    /**
     * Registra um novo livro.
     * Este método cria um novo livro usando os dados fornecidos em {@link BookRequestDTO}.
     * O livro será adicionado ao repositório e um objeto {@link BookResponseDTO}
     * representando o livro registrado será retornado.
     *
     * @param requestData Os dados do livro a ser registrado. Não pode ser nulo.
     * @return Um objeto `BookResponseDTO` do livro que foi registrado.
     */
    @Transactional
    public BookResponseDTO register(@Nonnull BookRequestDTO requestData) {
        var entity = this.bookMapper.toEntity(requestData);
        this.bookRepository.save(entity);
        return this.bookMapper.toDTO(entity);
    }

    /**
     * Atualiza um livro existente.
     * Este método modifica as informações de um livro já registrado,
     * identificado pelo `id`, com os dados fornecidos em `requestData`.
     * Um objeto {@link BookResponseDTO} representando o livro atualizado é retornado.
     *
     * @param id          O ID do livro a ser atualizado. Não pode ser nulo.
     * @param requestData Os novos dados do livro. Não pode ser nulo.
     * @return Um objeto {@link BookResponseDTO} do livro que foi atualizado.
     */
    @Transactional
    public BookResponseDTO update(@Nonnull Integer id, @Nonnull BookRequestDTO requestData) {
        var entity = this.bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Livro"));
        this.bookMapper.update(requestData, entity);
        return this.bookMapper.toDTO(entity);
    }

    /**
     * Deleta um livro.
     * Este método remove o livro identificado pelo `id` do repositório.
     * Se o livro for encontrado e deletado com sucesso, não retorna nada.
     *
     * @param id O ID do livro a ser deletado. Não pode ser nulo.
     */
    @Transactional
    public void delete(@Nonnull Integer id) {
        if (!this.bookRepository.existsById(id)) throw new ObjectNotFoundException(id, "Livro");
        if (this.loanRepository.existsActiveLoan(null, id)) throw new LoanExistsActiveException();
        this.bookRepository.deleteById(id);
    }

    /**
     * Verifica se um usuário existe.
     * Este método checa se um usuário com o ID fornecido existe no sistema.
     *
     * @param userId O ID do usuário a ser verificado. Não pode ser nulo.
     * @throws ObjectNotFoundException Se o usuário com o ID fornecido não existir.
     */
    private void checkIfExists(@Nonnull Integer userId) throws ObjectNotFoundException {
        if (!this.userRepository.existsById(userId)) throw new ObjectNotFoundException(userId, "Usuário");
    }


}
