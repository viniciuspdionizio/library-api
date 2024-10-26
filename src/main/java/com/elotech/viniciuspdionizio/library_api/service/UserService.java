package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserResponseDTO getById(@Nonnull Integer id) {
        return this.userRepository.findById(id)
                .map(this.userMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(id, "user"));
    }

    public Page<UserResponseDTO> getAll(@Nullable String filter, @Nonnull Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().and(Sort.by("id")));
        return this.userRepository.findAll(filter, pageable).map(this.userMapper::toDTO);
    }

    @Transactional
    public UserResponseDTO register(@Nonnull UserRequestDTO requestData) {
        var entity = this.userMapper.toEntity(requestData);
        this.userRepository.save(entity);
        return this.userMapper.toDTO(entity);
    }

    @Transactional
    public UserResponseDTO update(@Nonnull Integer id, @Nonnull UserRequestDTO requestData) {
        var entity = this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "user"));
        this.userMapper.update(requestData, entity);
        return this.userMapper.toDTO(entity);
    }

    @Transactional
    public void delete(@Nonnull Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw new ObjectNotFoundException(id, "user");
        }
        this.userRepository.deleteById(id);
    }

}
