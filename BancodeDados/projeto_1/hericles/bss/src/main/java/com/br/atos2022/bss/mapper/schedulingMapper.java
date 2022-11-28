package com.br.atos2022.bss.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.br.atos2022.bss.dto.schedulingDTO;
import com.br.atos2022.bss.models.scheduling;

@Mapper(componentModel="spring")
public interface schedulingMapper {
       
    schedulingDTO toschedulingDTO(scheduling sched);

    
    List<schedulingDTO> toschedulingDTOs(List<scheduling>scheds);

    scheduling toScheduling(schedulingDTO schedDTO); 


}
