package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.CostumerRequestDTO;
import com.daud.datamaster.dto.response.CostumerResponseDTO;
import com.daud.datamaster.entity.Costumer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CostumerMapper {

    public Costumer toEntity(CostumerRequestDTO dto) {
        Costumer costumer = new Costumer();
        costumer.setName(dto.getCostumerName());
        costumer.setNumberPhone(dto.getCostumerNumberPhone());
        costumer.setAddress(dto.getCostumerAddress());
        costumer.setCreatedAt(LocalDate.now());
        return costumer;
    }

    public CostumerResponseDTO toDTO(Costumer costumer) {
        CostumerResponseDTO dto = new CostumerResponseDTO();
        dto.setCostumerId(costumer.getId());
        dto.setCostumerName(costumer.getName());
        dto.setCostumerNumberPhone(costumer.getNumberPhone());
        dto.setCostumerAddress(costumer.getAddress());
        dto.setCreatedAt(costumer.getCreatedAt());
        dto.setUpdateAt(costumer.getUpdateAt());
        return dto;
    }

    public void updateEntity(CostumerRequestDTO dto, Costumer costumer) {
        costumer.setName(dto.getCostumerName()==null? costumer.getName() : dto.getCostumerName());
        costumer.setNumberPhone(dto.getCostumerNumberPhone()==null? costumer.getNumberPhone() : dto.getCostumerNumberPhone());
        costumer.setAddress(dto.getCostumerAddress()==null? costumer.getAddress() : dto.getCostumerAddress());
        costumer.setCreatedAt(costumer.getCreatedAt());
        costumer.setUpdateAt(LocalDate.now());
    }
}
