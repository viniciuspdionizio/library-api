package com.elotech.viniciuspdionizio.library_api.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import com.elotech.viniciuspdionizio.library_api.model.entity.LoanEntity;
import com.elotech.viniciuspdionizio.library_api.model.entity.UserEntity;
import com.elotech.viniciuspdionizio.library_api.model.mapper.LoanMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class LoanMapperTests {

    @Autowired
    private LoanMapper loanMapper;

    @Test
    public void shouldMapLoanRequestToEntity() {
        var today = LocalDate.now();
        var dto = new LoanRequestDTO(1, 1, today, today.plusDays(3));
        var entity = loanMapper.toEntity(dto);
        assertNull(entity.getId());
        assertEquals(entity.getUser().getId(), dto.userId());
        assertEquals(entity.getBook().getId(), dto.bookId());
        assertEquals(entity.getLoanDate(), dto.loanDate());
        assertEquals(entity.getReturnDate(), dto.returnDate());
    }

    @Test
    public void shouldMapEntityToDTO() {
        var entity = new LoanEntity();
        entity.setId(1);
        entity.setUser(new UserEntity(2));
        entity.setBook(new BookEntity(3));
        entity.setLoanDate(LocalDate.now());
        entity.setReturnDate(LocalDate.now().plusDays(3));
        var dto = loanMapper.toDTO(entity);
        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.user().id(), entity.getUser().getId());
        assertEquals(dto.book().id(), entity.getBook().getId());
        assertEquals(dto.loanDate(), entity.getLoanDate());
        assertEquals(dto.returnDate(), entity.getReturnDate());
    }
}