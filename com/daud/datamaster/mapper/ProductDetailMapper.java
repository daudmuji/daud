package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.ProductDetailReqDTO;
import com.daud.datamaster.dto.response.BrandResponseDTO;
import com.daud.datamaster.dto.response.ProductDetailResDTO;
import com.daud.datamaster.entity.*;
import com.daud.datamaster.service.BrandService;
import com.daud.datamaster.service.ProductService;
import com.daud.datamaster.service.StorageService;
import com.daud.datamaster.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class ProductDetailMapper {

    private final BrandService brandService;

    private final ProductService productService;

    private final StorageService storageService;


    public ProductDetailResDTO toDTO(ProductDetail productDetail) {
        ProductDetailResDTO dto = new ProductDetailResDTO();
        dto.setProductDetailId(productDetail.getId());
        dto.setProduct(productService.construcDTO(productDetail.getProduct()));
        dto.setBrand(brandService.construcDTO(productDetail.getBrand()));
        dto.setQuantityBrandBefore(productDetail.getQuantityBrandBefore());
        dto.setQuantityBrand(productDetail.getQuantityBrand());
        dto.setQuantitySum(productDetail.getQuantitySum());
        dto.setCreatedAt(productDetail.getCreatedAt());
        dto.setQtyBefore(productDetail.getQuantityBefore());
        dto.setQtyAdd(productDetail.getQuantityAdd());

        dto.setUpdateAt(productDetail.getUpdateAt());
        dto.setQuantity(productDetail.getQuantity());
        dto.setPrice(productDetail.getPrice());
        dto.setStorage(storageService.construcDTO(productDetail.getStorage()));
        return dto;
    }
}
