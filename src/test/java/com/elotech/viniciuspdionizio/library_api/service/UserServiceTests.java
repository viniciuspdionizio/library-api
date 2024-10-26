package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void shouldNotRegisterWithInvalidEmail() {
        var user = new UserRequestDTO("Vinicius", "invalid", "12345");
        Assertions.assertThrows(ConstraintViolationException.class, () -> userService.register(user));
    }

}
