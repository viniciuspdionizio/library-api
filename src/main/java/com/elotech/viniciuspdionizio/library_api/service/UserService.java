package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.config.exception.LoanExistsActiveException;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.UserMapper;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final UserMapper userMapper;

    /**
     * Busca um usuário pelo ID.
     * Este método tenta encontrar um usuário no sistema usando o ID fornecido.
     * Se o usuário for encontrado, ele é retornado como um objeto {@link UserResponseDTO}.
     * Se não for encontrado, uma exceção {@link ObjectNotFoundException} é lançada.
     *
     * @param id O ID do usuário a ser buscado. Não pode ser nulo.
     * @return Um objeto {@link UserResponseDTO} do usuário encontrado.
     * @throws ObjectNotFoundException Se o usuário com o ID fornecido não for encontrado.
     */
    public UserResponseDTO getById(@Nonnull Integer id) throws ObjectNotFoundException {
        return this.userRepository.findById(id)
                .map(this.userMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Usuário"));
    }

    /**
     * Recupera todos os usuários com base em filtros opcionais.
     * Este método busca todos os usuários do sistema. Você pode passar um filtro
     * para buscar usuários específicos. Os resultados são retornados em uma página,
     * que pode ser controlada pelo objeto {@link Pageable}.
     *
     * @param filter   Um filtro opcional para buscar usuários específicos. Pode ser nulo.
     * @param pageable Um objeto {@link Pageable} para controlar a paginação e a ordenação dos resultados. Não pode ser nulo.
     * @return Uma página de objetos {@link UserResponseDTO} com os usuários encontrados.
     */
    public Page<UserResponseDTO> getAll(@Nullable String filter, @Nonnull Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().and(Sort.by("id")));
        return this.userRepository.findAll(filter, pageable).map(this.userMapper::toDTO);
    }

    /**
     * Registra um novo usuário.
     * Este método cria um novo usuário com os dados fornecidos em `requestData`.
     * O usuário será adicionado ao sistema e um objeto {@link UserResponseDTO} representando
     * o usuário registrado será retornado.
     *
     * @param requestData {@link UserRequestDTO} Os dados do usuário a ser registrado. Não pode ser nulo.
     * @return Um objeto {@link UserResponseDTO} do usuário que foi registrado.
     */
    @Transactional
    public UserResponseDTO register(@Nonnull UserRequestDTO requestData) {
        var entity = this.userMapper.toEntity(requestData);
        this.userRepository.save(entity);
        return this.userMapper.toDTO(entity);
    }

    /**
     * Atualiza um usuário existente.
     * Este método modifica as informações de um usuário já registrado,
     * identificado pelo {@code id}, com os dados fornecidos em {@link UserRequestDTO}.
     * Um objeto {@link UserResponseDTO} representando o usuário atualizado é retornado.
     *
     * @param id          O ID do usuário a ser atualizado. Não pode ser nulo.
     * @param requestData Os novos dados do usuário. Não pode ser nulo.
     * @return Um objeto {@link UserResponseDTO} do usuário que foi atualizado.
     * @throws ObjectNotFoundException Se o usuário com o ID fornecido não existir.
     */
    @Transactional
    public UserResponseDTO update(@Nonnull Integer id, @Nonnull UserRequestDTO requestData) throws ObjectNotFoundException {
        var entity = this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Usuário"));
        this.userMapper.update(requestData, entity);
        return this.userMapper.toDTO(entity);
    }

    /**
     * Remove um usuário do sistema.
     * Este método deleta um usuário identificado pelo {@code id}.
     * Se o usuário não for encontrado, uma exceção {@link ObjectNotFoundException} é lançada.
     *
     * @param id O ID do usuário a ser deletado. Não pode ser nulo.
     * @throws ObjectNotFoundException Se o usuário com o ID fornecido não existir.
     */
    @Transactional
    public void delete(@Nonnull Integer id) throws ObjectNotFoundException {
        if (!this.userRepository.existsById(id)) throw new ObjectNotFoundException(id, "Usuário");
        if (this.loanRepository.existsActiveLoan(id, null)) throw new LoanExistsActiveException();
        this.userRepository.deleteById(id);
    }

}
