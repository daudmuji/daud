package com.daud.datamaster.service;


import com.daud.datamaster.dto.request.StorageRequestDTO;
//import com.daud.datamaster.dto.request.StorageUpdateRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.StorageResponseDTO;
import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.Storage;

import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public interface StorageService {

    public void createStorage(StorageRequestDTO dto);

    public StorageResponseDTO findStorageById(UUID id);


    public List<ProductDetail> findProductsByStorage(UUID storageId);

    public ResultPageResponseDTO<StorageResponseDTO> findStoragesByName(Integer pages, Integer limit, String sortBy,
                                                                        String direction, String storageName);

    public void updateStorage(UUID storageId, StorageRequestDTO dto);

    public void deleteStorage(UUID storageId);

    public StorageResponseDTO findStorageByName(String storageName);

    public Storage findStorage(UUID id);

    public StorageResponseDTO construcDTO(Storage storage);

}
