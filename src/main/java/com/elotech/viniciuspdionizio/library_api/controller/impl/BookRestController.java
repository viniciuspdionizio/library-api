package com.elotech.viniciuspdionizio.library_api.controller.impl;

import com.elotech.viniciuspdionizio.library_api.controller.BookController;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRestController implements BookController {

    private final BookService bookService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @Override
    @GetMapping("/page")
    public Page<BookResponseDTO> getAll(@RequestParam(required = false) String filter, Pageable pageable) {
        return bookService.getAll(filter, pageable);
    }

    @Override
    @PostMapping
    public ResponseEntity<BookResponseDTO> register(@RequestBody @Valid BookRequestDTO requestData) {
        var resp = bookService.register(requestData);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.id()).toUri();
        return ResponseEntity.created(uri).body(resp);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Integer id, @RequestBody @Valid BookRequestDTO requestData) {
        return ResponseEntity.ok(bookService.update(id, requestData));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
