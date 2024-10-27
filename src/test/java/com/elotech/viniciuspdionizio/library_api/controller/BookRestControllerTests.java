package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;
import com.elotech.viniciuspdionizio.library_api.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTests {

    @Autowired
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private Integer bookId;

    @BeforeEach
    void setUp() {
        bookId = this.bookService.register(new BookRequestDTO("Title", "Author", "1234", LocalDate.now(), BookCategory.DEFAULT)).id();
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/page"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldNotGetBookByIdWithNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldRegisterNewBook() throws Exception {
        var book = new BookRequestDTO("Title", "Author", "1234", LocalDate.now(), BookCategory.DEFAULT);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotRegisterBookWithEmptyBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldNotRegisterBookWithNullTitle() throws Exception {
        var book = new BookRequestDTO(null, "Author", "1234", LocalDate.now(), BookCategory.DEFAULT);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldUpdateBook() throws Exception {
        var book = new BookRequestDTO("Title", "Author", "1234", LocalDate.now(), BookCategory.DEFAULT);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/%d".formatted(bookId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/%d".formatted(bookId)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
