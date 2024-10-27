package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.service.UserService;
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

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestContollerTests {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    int userId;

    @BeforeEach
    void setUp() {
        this.userId = this.userService.register(new UserRequestDTO("Name", "email@email.com", "123456")).id();
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/page"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldNotGetUserByIdWithNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldRegisterNewUser() throws Exception {
        var user = new UserRequestDTO("Vinicius", "vinicius@email.com", "123456");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotRegisterUserWithEmptyBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldNotRegisterUserWithNullName() throws Exception {
        var user = new UserRequestDTO(null, "vinicius@email.com", "123456");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldUpdateUser() throws Exception {
        var user = new UserRequestDTO("Updated name", "vinicius@email.com", "123456");
        mockMvc.perform(MockMvcRequestBuilders.put("/users/%d".formatted(userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/%d".formatted(userId)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
