package com.deep.ecommerceapi.service;

import com.deep.ecommerceapi.entity.Product;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProductByProductId(Long productId);
    public List<Product> findByProductCategory(String productCategory);
}
