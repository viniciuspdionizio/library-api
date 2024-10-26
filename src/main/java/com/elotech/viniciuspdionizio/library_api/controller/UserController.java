package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<UserResponseDTO> getById(Integer id);

    Page<UserResponseDTO> getAll(String filter, Pageable pageable);

    Page<BookResponseDTO> getRecommendations(Integer id, Boolean status, Pageable pageable);

    ResponseEntity<UserResponseDTO> register(UserRequestDTO userRequestDTO);

    ResponseEntity<UserResponseDTO> update(Integer id, UserRequestDTO userRequestDTO);

    ResponseEntity<Void> delete(Integer id);

}
