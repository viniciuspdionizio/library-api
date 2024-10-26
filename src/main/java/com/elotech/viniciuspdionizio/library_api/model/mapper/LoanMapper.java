package com.elotech.viniciuspdionizio.library_api.model.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, BookMapper.class})
public interface LoanMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "book.id", source = "bookId")
    LoanEntity toEntity(LoanRequestDTO dto);

    LoanResponseDTO toDTO(LoanEntity entity);

}
