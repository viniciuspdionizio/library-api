package com.elotech.viniciuspdionizio.library_api.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;
import com.elotech.viniciuspdionizio.library_api.model.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BookMapperTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void shouldMapBookRequestToEntity() {
        var dto = new BookRequestDTO("Book 1", "Vinicius Dionizio", "123456", LocalDate.now(), BookCategory.FICTION);
        var entity = bookMapper.toEntity(dto);
        assertNull(entity.getId());
        assertEquals(dto.title(), entity.getTitle());
        assertEquals(dto.author(), entity.getAuthor());
        assertEquals(dto.category(), entity.getCategory());
        assertEquals(dto.isbn(), entity.getIsbn());
    }

    @Test
    public void shouldMapBookEntityToDTO() {
        var entity = new BookEntity();
        entity.setTitle("Book 1");
        entity.setAuthor("Vinicius Dionizio");
        entity.setPublishedDate(LocalDate.now());
        var dto = bookMapper.toDTO(entity);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getTitle(), dto.title());
        assertEquals(entity.getAuthor(), dto.author());
        assertEquals(entity.getPublishedDate(), dto.publishedDate());
        assertEquals(entity.getCategory(), dto.category());
        assertEquals(entity.getIsbn(), dto.isbn());
    }

    @Test
    void shouldUpdateEntity() {
        var entity = new BookEntity();
        entity.setTitle("Book 1");
        entity.setAuthor("Vinicius Dionizio");
        entity.setIsbn("123456");
        entity.setPublishedDate(LocalDate.now());
        var dto = new BookRequestDTO("Book 2", "Vinicius de Paiva Dionizio", "1234567", LocalDate.now().minusDays(10), BookCategory.BIOGRAPHY);
        assertNotEquals(dto.title(), entity.getTitle());
        assertNotEquals(dto.author(), entity.getAuthor());
        assertNotEquals(dto.isbn(), entity.getIsbn());
        assertNotEquals(dto.publishedDate(), entity.getPublishedDate());
        assertNotEquals(dto.category(), entity.getCategory());

        this.bookMapper.update(dto, entity);
        assertEquals(dto.title(), entity.getTitle());
        assertEquals(dto.author(), entity.getAuthor());
        assertEquals(dto.isbn(), entity.getIsbn());
        assertEquals(dto.publishedDate(), entity.getPublishedDate());
        assertEquals(dto.category(), entity.getCategory());
    }

}
