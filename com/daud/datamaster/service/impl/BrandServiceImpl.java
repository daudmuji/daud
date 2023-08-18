package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.BrandRequestDTO;
import com.daud.datamaster.dto.response.BrandResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Brand;
import com.daud.datamaster.entity.Supplier;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.BrandMapper;
import com.daud.datamaster.repository.BrandRepository;
import com.daud.datamaster.service.BrandService;
import com.daud.datamaster.service.SupplierService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("brandService")
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    private final SupplierService supplierService;

    @Override
    public void createBrands(ArrayList<BrandRequestDTO> dtos) {
        List<Brand> list = dtos.stream().map((dto) -> {
            Supplier supplier = supplierService.findSupplier(dto.getSupplierId());
            Brand brand = new Brand();
            brand.setName(dto.getBrandName());
            brand.setSupplier(supplier);
            brand.setCreatedAt(LocalDate.now());
            brand.setQuantitySupplier(supplier.getQuantity());
            brand.setDescription(dto.getBrandDescription());
            return brand;
        }).collect(Collectors.toList());
        ArrayList<Brand> brands = new ArrayList<Brand>(list);
        brandRepository.saveAll(brands);
    }

    @Override
    public BrandResponseDTO findBrandById(Long id) {
        Brand brand = brandRepository.findBrandById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return brandMapper.toDTO(brand);
    }

    @Override
    public ResultPageResponseDTO<BrandResponseDTO> findBrands(Integer pages, Integer limit, String sortBy,
                                                              String direction, String brandName, LocalDate createdAt) {
        brandName = StringUtils.isBlank(brandName) ? "%" : "%" + brandName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Brand> pageResult = brandRepository.findBrandByNameLikeIgnoreCaseAndCreatedAt(brandName, createdAt, pageable);
        List<BrandResponseDTO> list = pageResult.stream().map(b -> {
            BrandResponseDTO dto = new BrandResponseDTO();
            dto.setBrandId(b.getId());
            dto.setBrandName(b.getName());
            dto.setBrandDescription(b.getDescription());
            dto.setSupplier(supplierService.construcDTO(b.getSupplier()));
            dto.setCreatedAt(b.getCreatedAt());
            dto.setQuantityBefore(b.getQuantityBefore());
            dto.setQtyAdd(b.getQuantityAdd());
            dto.setUpdateAt(b.getUpdateAt());
            dto.setQuantity(b.getQuantitySupplier());
            return dto;
        }).collect(Collectors.toList());
        ArrayList<BrandResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public void updateBrand(Long brandId, BrandRequestDTO dto) {
        Brand brand = brandRepository.findBrandById(brandId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        brandMapper.updateEntity(dto, brand);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        Brand brand = brandRepository.findBrandById(brandId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        brandRepository.delete(brand);
    }

    @Override
    public BrandResponseDTO findBrandByName(String brandName) {
        Brand brand = brandRepository.findBrandByName(brandName).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return brandMapper.toDTO(brand);
    }

    @Override
    public Brand findBrand(Long id) {
        return brandRepository.findBrandById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
    }

    @Override
    public BrandResponseDTO construcDTO(Brand brand) {
        return brandMapper.toDTO(brand);
    }
}
