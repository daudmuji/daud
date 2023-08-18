package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.BrandRequestDTO;
import com.daud.datamaster.dto.response.BrandResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Brand;

import java.time.LocalDate;
import java.util.ArrayList;

public interface BrandService {

    public void createBrands(ArrayList<BrandRequestDTO> dtos);

    public BrandResponseDTO findBrandById(Long id);

    public ResultPageResponseDTO<BrandResponseDTO> findBrands(Integer pages, Integer limit, String sortBy,
                                                              String direction, String brandName, LocalDate createdAt);

    public void updateBrand(Long brandId, BrandRequestDTO dto);

    public void deleteBrand(Long brandId);

    public BrandResponseDTO findBrandByName(String brandName);

    public Brand findBrand(Long id);

    public BrandResponseDTO construcDTO(Brand brand);

}
