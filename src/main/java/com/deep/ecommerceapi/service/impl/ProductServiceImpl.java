package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.exception.ProductNotFound;
import com.deep.ecommerceapi.repository.ProductRepository;
import com.deep.ecommerceapi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        log.info("Product with the id :"+savedStudent.getId()+" has been saved");
        return savedStudent;
    }

    @Override
    public List<Product> getAllProducts(String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        log.info("Fetching all the products");
        return productRepository.findAll(sort);
    }

    @Override
    public Product getProductByProductId(Long productId) {
      log.info("Fetching product with the id "+productId);
      Product product=productRepository.findById(productId).
              orElseThrow(()->new ProductNotFound(HttpStatus.BAD_REQUEST,"Product not found with the id "+productId));
        return product;
    }

    @Override
    public List<Product> findByProductCategory(String productCategory) {
        log.info("Fetching product with category: "+productCategory);
        List<Product>productList=productRepository.findAllByProductCategoriesCategory(productCategory);
        return productList;
    }

    @Override
    public String deleteProductById(Long productId) {
        log.info("Deleting product with the id "+productId);
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }
}
