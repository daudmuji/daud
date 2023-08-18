package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.BrandRequestDTO;
import com.daud.datamaster.dto.response.BrandResponseDTO;
import com.daud.datamaster.entity.Brand;
import com.daud.datamaster.entity.Supplier;
import com.daud.datamaster.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final SupplierService supplierService;

    public BrandResponseDTO toDTO(Brand brand) {
        BrandResponseDTO dto = new BrandResponseDTO();
        dto.setBrandId(brand.getId());
        dto.setBrandName(brand.getName());
        dto.setBrandDescription(brand.getDescription());
        dto.setSupplier(supplierService.construcDTO(brand.getSupplier()));
        dto.setCreatedAt(brand.getCreatedAt());
        dto.setQuantityBefore(brand.getQuantityBefore());
        dto.setQtyAdd(brand.getQuantityAdd());
        dto.setUpdateAt(brand.getUpdateAt());
        dto.setQuantity(brand.getQuantitySupplier());
        return dto;
    }

    public void updateEntity(BrandRequestDTO dto, Brand brand)  {
        Supplier supplier = supplierService.findSupplier(dto.getSupplierId());
        brand.setName(dto.getBrandName()==null? brand.getName() : dto.getBrandName());
        brand.setSupplier(dto.getSupplierId()==null? brand.getSupplier() : supplier);
        brand.setCreatedAt(brand.getCreatedAt());
        brand.setQuantityBefore(supplier.getQuantityBefore());
        brand.setQuantityAdd(supplier.getQuantityAdd());
        brand.setUpdateAt(LocalDate.now());
        brand.setQuantitySupplier(supplier.getQuantity());
        brand.setDescription(dto.getBrandDescription()==null? brand.getDescription() : dto.getBrandDescription());
    }
}
