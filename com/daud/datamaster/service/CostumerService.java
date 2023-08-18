package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.CostumerRequestDTO;
import com.daud.datamaster.dto.response.CostumerResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Costumer;

import java.util.UUID;

public interface CostumerService {

    public void createCostumer(CostumerRequestDTO dto);

    public CostumerResponseDTO findCostumerById(UUID id);

    public ResultPageResponseDTO<CostumerResponseDTO> findCostumersByName(Integer pages, Integer limit, String sortBy,
                                                                          String direction, String costumerName);

    public void updateCostumer(UUID costumerId, CostumerRequestDTO dto);

    public void deleteCostumer(UUID costumerId);

    public CostumerResponseDTO findCostumerByName(String costumerName);

    public Costumer findCostumer(UUID id);

    public CostumerResponseDTO construcDTO(Costumer costumer);
}
