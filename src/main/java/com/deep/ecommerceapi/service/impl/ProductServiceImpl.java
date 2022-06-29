package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.exception.ProductNotFound;
import com.deep.ecommerceapi.repository.ProductRepository;
import com.deep.ecommerceapi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        Product savedStudent=productRepository.save(product);
        log.info("Student with the id :"+savedStudent.getId()+" has been saved");
        return savedStudent;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductByProductId(Long productId) {
      Product product=productRepository.findById(productId).
              orElseThrow(()->new ProductNotFound(HttpStatus.BAD_REQUEST,"Product not found with the id "+productId));
        return product;
    }

    @Override
    public List<Product> findByProductCategory(String productCategory) {
        List<Product>productList=productRepository.findAllByProductCategoriesCategory(productCategory);
        return productList;
    }
}
