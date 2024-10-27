package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class LoanRestControllerTests {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private LoanRequestDTO createRequestDTO(Integer userId, Integer bookId) {
        return new LoanRequestDTO(userId, bookId, LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @Test
    void shouldGetLoans() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loans/page"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldRegisterNewLoan() throws Exception {
        var loan = this.createRequestDTO(1, 31);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void shouldNotRegisterLoanWithEmptyBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/loans"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldNotRegisterLoanWithNullBookId() throws Exception {
        var loan = this.createRequestDTO(1, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
