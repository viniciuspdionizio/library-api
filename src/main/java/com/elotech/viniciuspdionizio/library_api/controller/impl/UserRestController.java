package com.elotech.viniciuspdionizio.library_api.controller.impl;

import com.elotech.viniciuspdionizio.library_api.controller.UserController;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.elotech.viniciuspdionizio.library_api.service.BookService;
import com.elotech.viniciuspdionizio.library_api.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController implements UserController {

    private final UserService userService;
    private final BookService bookService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    @GetMapping("/page")
    public Page<UserResponseDTO> getAll(@RequestParam(required = false) String filter, Pageable pageable) {
        return this.userService.getAll(filter, pageable);
    }

    @Override
    @GetMapping("/{id}/recommendations")
    public Page<BookResponseDTO> getRecommendations(@PathVariable Integer id,
                                                    @RequestParam(required = false) Boolean status,
                                                    Pageable pageable) {
        return this.bookService.getRecommendationsByUserId(id, status, pageable);
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        var response = this.userService.register(userRequestDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Integer id, @RequestBody UserRequestDTO userRequestDTO) {
        var response = this.userService.update(id, userRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
