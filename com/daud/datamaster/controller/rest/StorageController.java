package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.StorageRequestDTO;
//import com.daud.datamaster.dto.request.StorageUpdateRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.StorageResponseDTO;
import com.daud.datamaster.service.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/storage")
    public ResponseEntity<Void> createNewStorage(@Valid @RequestBody StorageRequestDTO dto) {
        storageService.createStorage(dto);
        return ResponseEntity.created(URI.create("/storage")).build();
    }

    @GetMapping("/storage/{id}")
    public ResponseEntity<StorageResponseDTO> findStorageById(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.ok(storageService.findStorageById(id));
    }

    @GetMapping("/storages")
    public ResponseEntity<ResultPageResponseDTO<StorageResponseDTO>> findStorages(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "storageName", required = false) String storageName
    ) {
        return ResponseEntity.ok(storageService.findStoragesByName(pages, limit, sortBy, direction, storageName));
    }

    @PutMapping("/storage/{storageId}")
    public ResponseEntity<Void> updateStorageById(@PathVariable(name = "storageId") UUID storageId,
                                                  @RequestBody StorageRequestDTO dto) {
        storageService.updateStorage(storageId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/storage/{storageId}")
    public ResponseEntity<Void> deleteStorageById(@PathVariable(name = "storageId") UUID storageId) {
        storageService.deleteStorage(storageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/storage/name/{storageName}")
    public ResponseEntity<StorageResponseDTO> findStorageByName(@PathVariable(name = "storageName") String storageName) {
        return ResponseEntity.ok(storageService.findStorageByName(storageName));
    }
}
