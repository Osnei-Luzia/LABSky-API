package com.backend.labskyapi.services.mappers;

import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.controller.dtos.PassagemResponseDTO;
import com.backend.labskyapi.models.Passagem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PassagemMapper {
    Passagem map(PassagemRequestDTO source);

    PassagemResponseDTO map(Passagem source);
}
