package com.backend.labskyapi.services.mappers;

import com.backend.labskyapi.controller.dtos.PassageiroCPFResponseDTO;
import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.models.Passageiro;
import com.backend.labskyapi.models.Passagem;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PassageiroMapper {
    PassageiroResponseDTO map(Passageiro source);
    PassageiroCPFResponseDTO mapCpf(Passageiro source);

    void update(@MappingTarget PassageiroResponseDTO target, Passagem source);
}
