package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.ProfitRequestDTO;
import com.daud.datamaster.dto.response.ProfitResponseDTO;
import com.daud.datamaster.entity.Profit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProfitMapper {

    public Profit toEntity(ProfitRequestDTO dto) {
        Profit profit = new Profit();
        profit.setCreatedAt(LocalDate.now());
        return profit;
    }

    public ProfitResponseDTO toDTO(Profit profit) {
        ProfitResponseDTO dto = new ProfitResponseDTO();
        dto.setProfitId(profit.getId());
        dto.setCreatedAt(profit.getCreatedAt());
        dto.setUpdateAt(profit.getUpdateAt());
        dto.setProfitQuantity(profit.getQuantity());
        dto.setProfitAmount(profit.getAmount());
        return dto;
    }
}
