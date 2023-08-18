package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.BrandRequestDTO;
import com.daud.datamaster.dto.request.SupplierRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.Brand;
import com.daud.datamaster.entity.Supplier;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.SupplierMapper;
import com.daud.datamaster.repository.SupplierRepository;
import com.daud.datamaster.service.SupplierService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("supplierService")
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    @Override
    @Transactional
    public void createSupplier(SupplierRequestDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getSupplierName());
        supplier.setPhoneNumber(dto.getSupplierPhoneNumber());
        supplier.setAddress(dto.getSupplierAddress());
        supplier.setCreatedAt(LocalDate.now());
        supplier.setQuantity(dto.getQuantity());
        BrandRequestDTO dtoBrand = dto.getBrand();
        Brand brand = new Brand();
        brand.setName(dtoBrand.getBrandName());
        brand.setSupplier(supplier);
        brand.setCreatedAt(LocalDate.now());
        brand.setQuantitySupplier(supplier.getQuantity());
        brand.setDescription(dtoBrand.getBrandDescription());
        supplier.setBrand(brand);
        supplierRepository.save(supplier);
    }

    @Override
    public SupplierResponseDTO findSupplierById(UUID id) {
        Supplier supplier = supplierRepository.findSupplierById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public ResultPageResponseDTO<SupplierResponseDTO> findSuppliersByName(Integer pages, Integer limit, String sortBy, String direction, String supplierName) {
        supplierName = StringUtils.isBlank(supplierName)?"%":"%"+supplierName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Supplier> pageResult = supplierRepository.findSuppliersByNameLikeIgnoreCase(supplierName, pageable);
        List<SupplierResponseDTO> list = pageResult.stream().map(s -> supplierMapper.toDTO(s)).collect(Collectors.toList());
        ArrayList<SupplierResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    @Transactional
    public void updateSupplier(UUID supplierId , SupplierRequestDTO dto) {
        Supplier supplier = supplierRepository.findSupplierById(supplierId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        supplier.setName(dto.getSupplierName()==null? supplier.getName() : dto.getSupplierName());
        supplier.setPhoneNumber(dto.getSupplierPhoneNumber()==null? supplier.getPhoneNumber() : dto.getSupplierPhoneNumber());
        supplier.setAddress(dto.getSupplierAddress()==null? supplier.getAddress() : dto.getSupplierAddress());
        supplier.setCreatedAt(supplier.getCreatedAt());
        supplier.setQuantityBefore(dto.getQtyBefore()==null? supplier.getQuantityBefore() : supplier.getQuantity());
        supplier.setQuantityAdd(dto.getQtyAdd()==null? supplier.getQuantityAdd() : dto.getQtyAdd());
        supplier.setUpdateAt(LocalDate.now());
        supplier.setQuantity(dto.getQtyAdd()==null?supplier.getQuantity() :
                supplier.getQuantity() + dto.getQtyAdd());

        Brand brand = supplier.getBrand();
        BrandRequestDTO dtoBrand = dto.getBrand();
        brand.setId(supplier.getBrand().getId());
        brand.setName(dtoBrand.getBrandName()==null? brand.getName() : dtoBrand.getBrandName());
        brand.setSupplier(dtoBrand.getSupplierId()==null? brand.getSupplier() : supplier);
        brand.setCreatedAt(brand.getCreatedAt());
        brand.setQuantityBefore(supplier.getQuantityBefore());
        brand.setQuantityAdd(supplier.getQuantityAdd());
        brand.setUpdateAt(LocalDate.now());
        brand.setQuantitySupplier(supplier.getQuantity());
        brand.setDescription(dtoBrand.getBrandDescription()==null? brand.getDescription() : dtoBrand.getBrandDescription());
        supplier.setBrand(brand);

        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(UUID supplierId) {
        Supplier supplier = supplierRepository.findSupplierById(supplierId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierResponseDTO findSupplierByName(String supplierName) {
        Supplier supplier = supplierRepository.findSupplierByName(supplierName).orElseThrow(() -> new ResourceNotFoundException("invalid name"));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public Supplier findSupplier(UUID id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
    }

    @Override
    public SupplierResponseDTO construcDTO(Supplier supplier) {
        return supplierMapper.toDTO(supplier);
    }
}
