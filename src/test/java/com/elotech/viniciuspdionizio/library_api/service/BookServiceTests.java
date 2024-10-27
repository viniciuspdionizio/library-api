package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookService bookService;

    private BookRequestDTO generateValidBook() {
        return new BookRequestDTO("Book 1", "Author 1", "1234", LocalDate.now(), BookCategory.FICTION);
    }

    private BookRequestDTO generateInvalidBook() {
        return new BookRequestDTO("", "Author 1", "1234", null, BookCategory.FICTION);
    }

    @Test
    void shouldRegisterWithValidData() {
        var response = bookService.register(generateValidBook());
        assertNotNull(response.id());
    }

    @Test
    void shouldNotRegisterWithInvalidData() {
        assertThrows(ConstraintViolationException.class, () -> bookService.register(generateInvalidBook()));
    }

    @Test
    void shouldUpdateBook() {
        var book = generateValidBook();
        var registerResponse = bookService.register(book);
        var id = registerResponse.id();
        assertNotNull(registerResponse.id());
        var secondBook = new BookRequestDTO("Book 2", "Author 2", "1234", LocalDate.now(), BookCategory.FICTION);
        var updateResponse = bookService.update(id, secondBook);
        assertNotNull(updateResponse.id());
        assertEquals(updateResponse.id(), registerResponse.id());
        assertNotEquals(updateResponse, registerResponse);
    }

}
