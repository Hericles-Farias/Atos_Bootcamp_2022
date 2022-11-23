package com.br.atos2022.bss.mapper;
import com.br.atos2022.bss.dto.electricvehicleDTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.br.atos2022.bss.models.electricVehicle;

@Mapper(componentModel = "spring")
public interface electricVehicleMapper {
    
    electricvehicleDTO toElectricVehicleDTO(electricVehicle ev);

    
    List<electricvehicleDTO> toElectricVehicleDTOs(List<electricVehicle>evs);

    electricVehicle toElectricVehicle(electricvehicleDTO evDTO);
}
