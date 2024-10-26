package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestContollerTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/page"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldNotRegisterUserWithEmptyBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldRegisterNewUser() throws Exception {
        var user = new UserRequestDTO("Vinicius", "viniciuspdionizio@gmail.com", "123456");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}