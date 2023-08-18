package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.ProductRequestDTO;
import com.daud.datamaster.dto.response.ProductResponseDTO;
import com.daud.datamaster.entity.Product;

import java.util.ArrayList;

public interface ProductService {

    public void createProduct(ArrayList<ProductRequestDTO> dtos);

    public ProductResponseDTO findProductById(Long id);

    public void updateProduct(Long productId, ProductRequestDTO dto);

    public void deleteProduct(Long productId);

    public ProductResponseDTO findProductByName(String productName);

    public Product findProduct(Long id);

    public ProductResponseDTO construcDTO(Product product);
}
