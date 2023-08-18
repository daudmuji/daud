package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.SupplierRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.Supplier;

import java.util.UUID;

public interface SupplierService {

    public void createSupplier(SupplierRequestDTO dto);

    public SupplierResponseDTO findSupplierById(UUID id);

    public ResultPageResponseDTO<SupplierResponseDTO> findSuppliersByName(Integer pages, Integer limit, String sortBy,
                                                                          String direction, String supplierName);

    public void updateSupplier(UUID supplierId , SupplierRequestDTO dto);

    public void deleteSupplier(UUID supplierId);

    public SupplierResponseDTO findSupplierByName(String supplierName);

    public Supplier findSupplier(UUID id);

    public SupplierResponseDTO construcDTO(Supplier supplier);
}
