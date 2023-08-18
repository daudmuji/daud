package com.daud.datamaster.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CostumerResponseDTO {

    private UUID costumerId;

    private String costumerName;

    private String costumerNumberPhone;

    private String costumerAddress;

    private LocalDate createdAt;

    private LocalDate updateAt;
}
