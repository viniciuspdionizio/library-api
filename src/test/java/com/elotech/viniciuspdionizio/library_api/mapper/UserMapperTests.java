package com.elotech.viniciuspdionizio.library_api.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.UserEntity;
import com.elotech.viniciuspdionizio.library_api.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldMapUserRequestToEntity() {
        var entity = userMapper.toEntity(new UserRequestDTO("Vinicius", "viniciuspdionizio@gmail.com", "123456"));
        assertNull(entity.getId());
        assertEquals("Vinicius", entity.getName());
        assertEquals("viniciuspdionizio@gmail.com", entity.getEmail());
        assertEquals("123456", entity.getPhoneNumber());
    }

    @Test
    public void shouldMapUserEntityToDTO() {
        var entity = new UserEntity();
        entity.setName("Vinicius");
        entity.setEmail("viniciuspdionizio@gmail.com");
        entity.setPhoneNumber("123456");
        var dto = userMapper.toDTO(entity);
        assertEquals("Vinicius", dto.name());
        assertEquals("viniciuspdionizio@gmail.com", dto.email());
        assertEquals("123456", dto.phoneNumber());
    }

    @Test
    void shouldUpdateEntity() {
        var entity = new UserEntity();
        entity.setName("Vinicius");
        entity.setEmail("viniciuspdionizio@gmail.com");
        entity.setPhoneNumber("123456");
        var dto = new UserRequestDTO("Vinicius", "viniciuspdionizio@gmail.com", null);
        this.userMapper.update(dto, entity);
        assertEquals("Vinicius", dto.name());
        assertEquals("viniciuspdionizio@gmail.com", dto.email());
        assertNotEquals("123456", dto.phoneNumber());
    }

}
