package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void shouldRegisterUser() {
        var user = new UserRequestDTO("Vinicius", "vinicius@email.com", "123456");
        var response = userService.register(user);
        assertNotNull(response.id());
    }

    @Test
    void shouldNotRegisterWithInvalidEmail() {
        var user = new UserRequestDTO("Vinicius", "invalid", "12345");
        assertThrows(ConstraintViolationException.class, () -> userService.register(user));
    }

    @Test
    void shouldUpdateUser() {
        var user = new UserRequestDTO("Vinicius", "vinicius@email.com", "123456");
        var registerResponse = userService.register(user);
        var id = registerResponse.id();
        assertNotNull(registerResponse.id());
        user = new UserRequestDTO("Name updated", "emailupdated@email.com", "123457");
        var updateResponse = userService.update(id, user);
        assertNotNull(updateResponse.id());
        assertEquals(registerResponse.id(), updateResponse.id());
        assertNotEquals(updateResponse, registerResponse);
    }

}
