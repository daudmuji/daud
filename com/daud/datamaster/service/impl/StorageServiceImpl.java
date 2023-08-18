package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.StorageRequestDTO;
//import com.daud.datamaster.dto.request.StorageUpdateRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.StorageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.Storage;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.StorageMapper;
import com.daud.datamaster.repository.StorageRepository;
import com.daud.datamaster.service.StorageService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("storageService")
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    private final StorageMapper storageMapper;

    @Override
    public void createStorage(StorageRequestDTO dto) {
        Storage storage = storageMapper.toEntity(dto);
        storageRepository.save(storage);
    }

    @Override
    public StorageResponseDTO findStorageById(UUID id) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return storageMapper.toDTO(storage);
    }

    @Override
    public List<ProductDetail> findProductsByStorage(UUID storageId) {
        return storageRepository.findProductsByStorageId(storageId);
    }

    @Override
    public ResultPageResponseDTO<StorageResponseDTO> findStoragesByName(Integer pages, Integer limit, String sortBy, String direction, String storageName) {
        storageName = StringUtils.isBlank(storageName)? "%" : "%"+storageName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Storage> pageResult = storageRepository.findStoragesByNameLikeIgnoreCase(storageName, pageable);
        List<StorageResponseDTO> list = pageResult.stream().map(s -> storageMapper.toDTO(s)).collect(Collectors.toList());
        ArrayList<StorageResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages()); //PARAMETER YANG ADA DISINI
        //HAMPIR SAMA DENGAN PARAMETER YANG ADA DI PAGE REQUST. NAMUN YANG MEMBEDAKAN ADALAH KLW DI RESULTNYA ITU PERLU DATANYA/LIST
        //KLW DI PAGE REQUEST PERLU SORT
        //SEDANGKAN DI METHOD SERVICE ITU SEMUA DIPERLUKAN KECUALI ELEMENT :
        //DARI MULAI PAGES, LIMIT/SIZE/ELEMENT/TOTALELEMENT, SORT BY, DIRECTION, DAN PROPERTY YANG DIGUNAKAN UNTUK PENCATRIAN
    }

    @Override
    public void updateStorage(UUID storageId, StorageRequestDTO dto) {
        int sum = findProductsByStorage(storageId).stream().mapToInt(ProductDetail::getQuantity).sum();

        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        storage.setName(dto.getStorageName()==null?storage.getName() : dto.getStorageName());
        storage.setPhoneNumber(dto.getStorageNumberPhone()==null? storage.getPhoneNumber() : dto.getStorageNumberPhone());
        storage.setAddress(dto.getStorageAddress()==null? storage.getAddress() : dto.getStorageAddress());
        storage.setCreatedAt(storage.getCreatedAt());
        storage.setQuantityBefore(dto.getQtyBefore()==null? storage.getQuantityBefore() : storage.getQuantity());
        storage.setUpdateAt(LocalDate.now());
        storage.setQuantity(sum);
        storageRepository.save(storage);
    }

    @Override
    public void deleteStorage(UUID storageId) {
        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        storageRepository.delete(storage);
    }

    @Override
    public StorageResponseDTO findStorageByName(String storageName) {
        Storage storage = storageRepository.findByName(storageName).orElseThrow(() -> new ResourceNotFoundException("invalid name"));
        return storageMapper.toDTO(storage);
    }

    @Override
    public Storage findStorage(UUID id) {
        return storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid name"));

    }

    @Override
    public StorageResponseDTO construcDTO(Storage storage) {
        return storageMapper.toDTO(storage);
    }
}
