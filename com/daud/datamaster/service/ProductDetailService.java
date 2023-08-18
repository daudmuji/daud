package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.ProductDetailReqDTO;
import com.daud.datamaster.dto.response.ProductDetailResDTO;
import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.TransactionDetail;

import java.util.ArrayList;
import java.util.List;

public interface ProductDetailService {

    public void createPD(ArrayList<ProductDetailReqDTO> dtos);

    public List<ProductDetail> findProductsByBrand(Long brandId);

    public void setQuantitySumEqualsBrand(Long brandId, List<ProductDetailReqDTO> dtos);

    public ProductDetailResDTO findById(Long id);

    public void updatePD(Long productDetailId, ProductDetailReqDTO dto);


    public void deletePD(Long productDetailId);

    public ProductDetail findPD(Long id);

    public ProductDetailResDTO construcDTO(ProductDetail productDetail);

    public List<TransactionDetail> findTransactionByProductActive(Long productDetailId);
}
