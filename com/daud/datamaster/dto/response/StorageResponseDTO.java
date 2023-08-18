package com.daud.datamaster.dto.response;

import com.daud.datamaster.entity.ProductDetail;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class StorageResponseDTO {

    private UUID storageId;

    private String storageName;

    private String storageNumberPhone;

    private String storageAddress;

    //private List<ProductDetailResponseDTO> productDetailList;

    private LocalDate createdAt;

    private Integer qtyBefore;

    private LocalDate updateAt;

    private Integer quantity;
}
