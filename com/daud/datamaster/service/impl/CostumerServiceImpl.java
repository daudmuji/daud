package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.CostumerRequestDTO;
import com.daud.datamaster.dto.response.CostumerResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.Costumer;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.CostumerMapper;
import com.daud.datamaster.repository.CostumerRepository;
import com.daud.datamaster.service.CostumerService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("costumerService")
@RequiredArgsConstructor
public class CostumerServiceImpl implements CostumerService {

    private final CostumerRepository costumerRepository;

    private final CostumerMapper costumerMapper;

    @Override
    public void createCostumer(CostumerRequestDTO dto) {
        Costumer costumer = costumerMapper.toEntity(dto);
        costumerRepository.save(costumer);
    }

    @Override
    public CostumerResponseDTO findCostumerById(UUID id) {
        Costumer costumer = costumerRepository.findCostumerById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return costumerMapper.toDTO(costumer);
    }

    @Override
    public ResultPageResponseDTO<CostumerResponseDTO> findCostumersByName(Integer pages, Integer limit, String sortBy, String direction, String costumerName) {
        costumerName = StringUtils.isBlank(costumerName) ? "%" : "%" + costumerName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Costumer> pageResult = costumerRepository.findCostumersByNameLikeIgnoreCase(costumerName, pageable);
        List<CostumerResponseDTO> list = pageResult.stream().map(c -> {
            CostumerResponseDTO dto = new CostumerResponseDTO();
            dto.setCostumerId(c.getId());
            dto.setCostumerName(c.getName());
            dto.setCostumerNumberPhone(c.getNumberPhone());
            dto.setCostumerAddress(c.getAddress());
            dto.setCreatedAt(c.getCreatedAt());
            dto.setUpdateAt(c.getUpdateAt());
            return dto;
        }).collect(Collectors.toList());
        ArrayList<CostumerResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public void updateCostumer(UUID costumerId, CostumerRequestDTO dto) {
        Costumer costumer = costumerRepository.findCostumerById(costumerId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        costumerMapper.updateEntity(dto, costumer);
        costumerRepository.save(costumer);
    }

    @Override
    public void deleteCostumer(UUID costumerId) {
        Costumer costumer = costumerRepository.findCostumerById(costumerId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        costumerRepository.delete(costumer);
    }

    @Override
    public CostumerResponseDTO findCostumerByName(String costumerName) {
        Costumer costumer = costumerRepository.findCostumerByName(costumerName).orElseThrow(() -> new ResourceNotFoundException("invalid name"));
        return costumerMapper.toDTO(costumer);
    }

    @Override
    public Costumer findCostumer(UUID id) {
        return costumerRepository.findCostumerById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
    }

    @Override
    public CostumerResponseDTO construcDTO(Costumer costumer) {
        return costumerMapper.toDTO(costumer);
    }
}
