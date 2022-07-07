package com.deep.ecommerceapi.service;

import com.deep.ecommerceapi.dto.ProductResponseDTO;
import com.deep.ecommerceapi.entity.Product;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<ProductResponseDTO> getAllProducts(String sortBy,String sortDir);
    public Product getProductByProductId(Long productId);
    public List<ProductResponseDTO> findByProductCategory(String productCategory);
    public String deleteProductById(Long productId);
}
