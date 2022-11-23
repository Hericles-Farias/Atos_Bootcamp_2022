package com.br.atos2022.bss.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.br.atos2022.bss.dto.planDTO;
import com.br.atos2022.bss.models.plan;

@Mapper(componentModel="spring")
public interface planMapper {
       
    planDTO toplanDTO(plan plan);

    
    List<planDTO> toplanDTOs(List<plan>plans);

    plan toPlan(planDTO planDTO); 



}
