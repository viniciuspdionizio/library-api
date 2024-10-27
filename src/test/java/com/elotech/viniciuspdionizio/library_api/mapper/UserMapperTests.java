package com.elotech.viniciuspdionizio.library_api.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.UserEntity;
import com.elotech.viniciuspdionizio.library_api.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldMapDTOToEntity() {
        var dto = new UserRequestDTO("Vinicius", "vinicius@email.com", "123456");
        var entity = userMapper.toEntity(dto);
        assertNull(entity.getId());
        assertEquals(dto.name(), entity.getName());
        assertEquals(dto.email(), entity.getEmail());
        assertEquals(dto.phoneNumber(), entity.getPhoneNumber());
    }

    @Test
    public void shouldMapEntityTODTO() {
        var entity = new UserEntity(1);
        entity.setName("Vinicius");
        entity.setEmail("vinicius@email.com");
        entity.setPhoneNumber("123456");
        var dto = userMapper.toDTO(entity);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getName(), dto.name());
        assertEquals(entity.getEmail(), dto.email());
        assertEquals(entity.getPhoneNumber(), dto.phoneNumber());
    }

    @Test
    void shouldUpdateEntity() {
        var entity = new UserEntity(1);
        entity.setName("Original name");
        entity.setEmail("original@email.com");
        entity.setPhoneNumber("123456");
        var dto = new UserRequestDTO("Edited name", "edited@email.com", null);
        assertNotNull(entity.getId());
        assertNotEquals(dto.name(), entity.getName());
        assertNotEquals(dto.email(), entity.getEmail());
        assertNotEquals(dto.phoneNumber(), entity.getPhoneNumber());

        this.userMapper.update(dto, entity);
        assertNotNull(entity.getId());
        assertEquals(dto.name(), entity.getName());
        assertEquals(dto.email(), entity.getEmail());
        assertEquals(dto.phoneNumber(), entity.getPhoneNumber());
    }

}
