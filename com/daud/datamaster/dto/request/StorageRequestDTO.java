package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StorageRequestDTO {

    @NotBlank
    private String storageName;

    @NotNull
    private String storageNumberPhone;

    @NotBlank
    private String storageAddress;

    private List<Long> productDetailIdList;

    private Integer qtyBefore;

    private Integer quantity;

}
