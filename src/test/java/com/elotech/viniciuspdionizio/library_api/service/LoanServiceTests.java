package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LoanServiceTests {

    @Autowired
    private LoanService loanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    private static UserResponseDTO user;
    private static BookResponseDTO book;


    private LoanRequestDTO createLoanRequestDTO() {
        return new LoanRequestDTO(user.id(), book.id(), LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @BeforeEach
    void setUp() throws Exception {
        user = this.userService.register(new UserRequestDTO("User for loan", "email@elotech.com.br", "123456"));
        book = this.bookService.register(new BookRequestDTO("Book for loan", "author", "1234756", LocalDate.of(2020, 1, 1), BookCategory.SCIENCE));
    }

    @Test
    void shouldRegisterLoan() {
        var response = this.loanService.register(createLoanRequestDTO());
        assertNotNull(response);
    }

    @Test
    void shouldUpdateOnlyStatus() {
        var loan = this.loanService.register(createLoanRequestDTO());
        var response = this.loanService.update(loan.id(), true, null);
        assertNotNull(response);
        assertNotEquals(loan, response);
        assertEquals(Boolean.TRUE, response.status());
    }

    @Test
    void shouldUpdateOnlyReturnDate() {
        var loan = this.loanService.register(createLoanRequestDTO());
        var response = this.loanService.update(loan.id(), false, LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()));
        assertNotNull(response);
        assertEquals(loan.id(), response.id());
        assertNotEquals(loan, response);
        assertEquals(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()), response.returnDate());
    }

    @Test
    void shouldNotRegisterLoanWithAlreadyActiveLoanForBook() {

    }

}
