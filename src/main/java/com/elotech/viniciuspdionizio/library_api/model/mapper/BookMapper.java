package com.elotech.viniciuspdionizio.library_api.model.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookEntity toEntity(BookRequestDTO dto);

    BookResponseDTO toDTO(BookEntity entity);

    @Mapping(target = "id", ignore = true)
    void update(BookRequestDTO dto, @MappingTarget BookEntity entity);

}
