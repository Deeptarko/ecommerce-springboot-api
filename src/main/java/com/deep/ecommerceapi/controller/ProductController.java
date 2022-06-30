package com.deep.ecommerceapi.controller;

import com.deep.ecommerceapi.entity.Product;
import com.deep.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.AppConstants;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Save a product
    @PostMapping("/save")
    public ResponseEntity<Product>saveProduct(@RequestBody Product product){
        Product savedProduct=productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //Get All Products
    @GetMapping("/products")
    public ResponseEntity<List<Product>>getAllProducts(
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        List<Product>productList=productService.getAllProducts(sortBy,sortDir);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    //Get Product By Id
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        Product product=productService.getProductByProductId(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    //Get Product By Category
    @GetMapping("/category/{productCategory}")
    public ResponseEntity<List<Product>> getProductByProductCategory(@PathVariable String productCategory){
        List<Product> productList=productService.findByProductCategory(productCategory);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    //Deleting Product By Id
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        String msg=productService.deleteProductById(productId);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
