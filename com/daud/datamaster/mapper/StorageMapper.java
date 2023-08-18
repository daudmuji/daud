package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.StorageRequestDTO;
import com.daud.datamaster.dto.response.StorageResponseDTO;
import com.daud.datamaster.entity.Storage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class StorageMapper {

    public Storage toEntity(StorageRequestDTO dto) {
        Storage storage = new Storage();
        storage.setName(dto.getStorageName());
        storage.setPhoneNumber(dto.getStorageNumberPhone());
        storage.setAddress(dto.getStorageAddress());
        storage.setCreatedAt(LocalDate.now());
        storage.setQuantity(dto.getQuantity());
        return storage;
    }

    public StorageResponseDTO toDTO(Storage storage) {
        StorageResponseDTO dto = new StorageResponseDTO();
        dto.setStorageId(storage.getId());
        dto.setStorageName(storage.getName());
        dto.setStorageNumberPhone(storage.getPhoneNumber());
        dto.setStorageAddress(storage.getAddress());
        dto.setCreatedAt(storage.getCreatedAt());
        dto.setQtyBefore(storage.getQuantityBefore());
        dto.setUpdateAt(storage.getUpdateAt());
        dto.setQuantity(dto.getQuantity());
        return dto;
    }

    public void updateEntity(StorageRequestDTO dto, Storage storage) {

    }
}
