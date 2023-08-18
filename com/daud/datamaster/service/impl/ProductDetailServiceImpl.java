package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.ProductDetailReqDTO;
import com.daud.datamaster.dto.response.ProductDetailResDTO;
import com.daud.datamaster.entity.*;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.ProductDetailMapper;
import com.daud.datamaster.repository.ProductDetailRepository;
import com.daud.datamaster.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("productDetailService")
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    private final ProductDetailMapper productDetailMapper;

    private final BrandService brandService;

    private final ProductService productService;

    private final StorageService storageService;



    @Override
    public void createPD(ArrayList<ProductDetailReqDTO> dtos) {

        List<ProductDetail> list = dtos.stream().map((dto) -> {

            Brand brand = brandService.findBrand(dto.getBrandId());
            Product product = productService.findProduct(dto.getProductId());
            Storage storage = storageService.findStorage(dto.getStorageId());

            ProductDetail productDetail = new ProductDetail();
            productDetail.setProduct(product);
            productDetail.setBrand(brand);
            productDetail.setQuantityBrand(brand.getQuantitySupplier());
            productDetail.setCreatedAt(LocalDate.now());
            productDetail.setQuantity(dto.getQuantity());
            productDetail.setPrice(dto.getPrice());
            productDetail.setStorage(storage);
            return productDetail;
        }).collect(Collectors.toList());
        ArrayList<ProductDetail> productDetails = new ArrayList<ProductDetail>(list);
        productDetailRepository.saveAll(productDetails);
    }

    @Override
    public List<ProductDetail> findProductsByBrand(Long brandId) {
        return productDetailRepository.findProductDetailsByBrandId(brandId);
    }

    @Override
    public void setQuantitySumEqualsBrand(Long brandId, List<ProductDetailReqDTO> dtos) {
        int sum = findProductsByBrand(brandId).stream().mapToInt(ProductDetail::getQuantity).sum();

        List<ProductDetail> productDetails = dtos.stream().map((dto -> {
            ProductDetail productDetail = new ProductDetail();
            Brand brand = brandService.findBrand(dto.getBrandId());

            productDetail.setId(dto.getProductDetailId());
            productDetail.setProduct(productDetail.getProduct());
            productDetail.setBrand(productDetail.getBrand());
            productDetail.setCreatedAt(productDetail.getCreatedAt());
            productDetail.setQuantityBrandBefore(brand.getQuantityBefore());
            productDetail.setQuantityBrand(brand.getQuantitySupplier());
            productDetail.setQuantitySum(sum);
            productDetail.setQuantityBefore(productDetail.getQuantityBefore());
            productDetail.setQuantityAdd(productDetail.getQuantityAdd());
            productDetail.setUpdateAt(LocalDate.now());
            productDetail.setQuantity(productDetail.getQuantity());
            productDetail.setPrice(productDetail.getPrice());
            productDetail.setStorage(productDetail.getStorage());
            return productDetail;
        })).collect(Collectors.toList());
        productDetailRepository.saveAll(productDetails);
    }

    @Override
    public ProductDetailResDTO findById(Long id) {
        ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return productDetailMapper.toDTO(productDetail);
    }

    @Override
    @Transactional
    public void updatePD(Long productDetailId, ProductDetailReqDTO dto) {
        Brand brand = brandService.findBrand(dto.getBrandId());
        Product product = productService.findProduct(dto.getProductId());
        Storage storage = storageService.findStorage(dto.getStorageId());
        List<TransactionDetail> transactionDetails = findTransactionByProductActive(productDetailId);
        int sumMin = transactionDetails.stream().mapToInt(TransactionDetail::getQuantity).sum();

        ProductDetail productDetail = productDetailRepository.findById(productDetailId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        productDetail.setProduct(dto.getProductId()==null? productDetail.getProduct() : product);
        productDetail.setBrand(dto.getBrandId()==null? productDetail.getBrand() : brand);
        productDetail.setCreatedAt(productDetail.getCreatedAt());
        productDetail.setQuantityBrandBefore(brand.getQuantityBefore());
        productDetail.setQuantityBrand(brand.getQuantitySupplier());
        productDetail.setQuantitySum(productDetail.getQuantitySum());
        productDetail.setQuantityBefore(dto.getQtyBefore()==null? productDetail.getQuantityBefore() : productDetail.getQuantity());
        productDetail.setQuantityAdd(dto.getQtyAdd()==null? productDetail.getQuantityAdd() : dto.getQtyAdd());
        productDetail.setUpdateAt(LocalDate.now());
        productDetail.setQuantity(dto.getQtyAdd()==null? productDetail.getQuantity() :
                productDetail.getQuantity() + dto.getQtyAdd());
        productDetail.setPrice(dto.getPrice()==null? dto.getPrice() : productDetail.getPrice());
        productDetail.setStorage(dto.getStorageId()==null? productDetail.getStorage() : storage);
        productDetailRepository.save(productDetail);
    }

    @Override
    public void deletePD(Long productDetailId) {
        ProductDetail productDetail = productDetailRepository.findById(productDetailId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        productDetailRepository.delete(productDetail);
    }

    @Override
    public ProductDetail findPD(Long id) {
        return productDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));

    }

    @Override
    public ProductDetailResDTO construcDTO(ProductDetail productDetail) {
        return productDetailMapper.toDTO(productDetail);
    }

    @Override
    public List<TransactionDetail> findTransactionByProductActive(Long productDetailId) {
        return productDetailRepository.findTransactionByProductDetailAndActive(productDetailId);
    }
}
