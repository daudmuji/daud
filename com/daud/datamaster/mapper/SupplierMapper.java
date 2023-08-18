package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.SupplierRequestDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.Supplier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequestDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getSupplierName());
        supplier.setPhoneNumber(dto.getSupplierPhoneNumber());
        supplier.setAddress(dto.getSupplierAddress());
        supplier.setCreatedAt(LocalDate.now());
        supplier.setQuantity(dto.getQuantity());
        return supplier;
    }

    public SupplierResponseDTO toDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setSupplierName(supplier.getName());
        dto.setSupplierPhoneNumber(supplier.getPhoneNumber());
        dto.setSupplierAddress(supplier.getAddress());
        dto.setCreatedAt(supplier.getCreatedAt());
        dto.setQuantityBefore(supplier.getQuantityBefore());
        dto.setQtyAdd(supplier.getQuantityAdd());
        dto.setUpdateAt(supplier.getUpdateAt());
        dto.setQuantity(supplier.getQuantity());
        return dto;
    }

    public void updateEntity(SupplierRequestDTO dto, Supplier supplier) {
        supplier.setName(dto.getSupplierName()==null? supplier.getName() : dto.getSupplierName());
        supplier.setPhoneNumber(dto.getSupplierPhoneNumber()==null? supplier.getPhoneNumber() : dto.getSupplierPhoneNumber());
        supplier.setAddress(dto.getSupplierAddress()==null? supplier.getAddress() : dto.getSupplierAddress());
        supplier.setCreatedAt(supplier.getCreatedAt());
        supplier.setQuantityBefore(dto.getQtyBefore()==null? supplier.getQuantityBefore() : supplier.getQuantity());
        supplier.setQuantityAdd(dto.getQtyAdd()==null? supplier.getQuantityAdd() : dto.getQtyAdd());
        supplier.setUpdateAt(LocalDate.now());
        supplier.setQuantity(dto.getQtyAdd()==null?supplier.getQuantity() :
                supplier.getQuantity() + dto.getQtyAdd());
    }
}
