package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BookController {

    ResponseEntity<BookResponseDTO> getById(Integer id);

    Page<BookResponseDTO> getAll(String filter, Boolean status, Pageable pageable);

    ResponseEntity<BookResponseDTO> register(BookRequestDTO requestData);

    ResponseEntity<BookResponseDTO> update(Integer id, BookRequestDTO requestData);

    ResponseEntity<Void> delete(Integer id);

}
