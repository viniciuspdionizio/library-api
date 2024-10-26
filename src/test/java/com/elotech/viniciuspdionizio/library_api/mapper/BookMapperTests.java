package com.elotech.viniciuspdionizio.library_api.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import com.elotech.viniciuspdionizio.library_api.model.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BookMapperTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void shouldMapBookRequestToEntity() {
        var entity = bookMapper.toEntity(new BookRequestDTO("Book 1", "Vinicius Dionizio", "123456", LocalDate.now()));
        assertNull(entity.getId());
        assertEquals("Book 1", entity.getTitle());
        assertEquals("Vinicius Dionizio", entity.getAuthor());
        assertEquals(LocalDate.now(), entity.getPublishedDate());
    }

    @Test
    public void shouldMapBookEntityToDTO() {
        var entity = new BookEntity();
        entity.setTitle("Book 1");
        entity.setAuthor("Vinicius Dionizio");
        entity.setPublishedDate(LocalDate.now());
        var dto = bookMapper.toDTO(entity);
        assertEquals("Book 1", dto.title());
        assertEquals("Vinicius Dionizio", dto.author());
        assertEquals(LocalDate.now(), dto.publishedDate());
    }

    @Test
    void shouldUpdateEntity() {
        var entity = new BookEntity();
        entity.setTitle("Book 1");
        entity.setAuthor("Vinicius Dionizio");
        entity.setIsbn("123456");
        entity.setPublishedDate(LocalDate.now());
        var dto = new BookRequestDTO("Book 2", "Vinicius de Paiva Dionizio", "1234567", LocalDate.now().minusDays(10));
        this.bookMapper.update(dto, entity);
        assertEquals("Book 2", entity.getTitle());
        assertEquals("Vinicius de Paiva Dionizio", entity.getAuthor());
        assertEquals("1234567", entity.getIsbn());
        assertEquals(LocalDate.now().minusDays(10), entity.getPublishedDate());
    }

}
