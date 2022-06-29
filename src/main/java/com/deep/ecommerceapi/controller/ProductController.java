package com.deep.ecommerceapi.controller;

import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product>saveProduct(@RequestBody Product product){
        Product savedProduct=productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getAllProducts(){
        List<Product>productList=productService.getAllProducts();
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        Product product=productService.getProductByProductId(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/category/{productCategory}")
    public ResponseEntity<List<Product>> getProductByProductCategory(@PathVariable String productCategory){
        List<Product> productList=productService.findByProductCategory(productCategory);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }
}
