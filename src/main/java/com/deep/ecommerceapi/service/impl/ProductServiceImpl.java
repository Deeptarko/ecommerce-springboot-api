package com.deep.ecommerceapi.service.impl;

import com.deep.ecommerceapi.dto.ProductResponseDTO;
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
import java.util.stream.Collectors;

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
    public List<ProductResponseDTO> getAllProducts(String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        log.info("Fetching all the products");
        List<Product>productList= productRepository.findAll(sort);
        List<ProductResponseDTO> responseList=productList.stream().map((product)->mapToDto(product)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public Product getProductByProductId(Long productId) {
      log.info("Fetching product with the id "+productId);
      Product product=productRepository.findById(productId).
              orElseThrow(()->new ProductNotFound(HttpStatus.BAD_REQUEST,"Product not found with the id "+productId));
        return product;
    }

    @Override
    public List<ProductResponseDTO> findByProductCategory(String productCategory) {
        log.info("Fetching product with category: "+productCategory);
        List<Product>productList=productRepository.findAllByProductCategoriesCategory(productCategory);
        List<ProductResponseDTO> responseList=productList.stream().map((product)->mapToDto(product)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public String deleteProductById(Long productId) {
        log.info("Deleting product with the id "+productId);
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }

    //Utility Function-Entity to DTO
    public ProductResponseDTO mapToDto(Product product){
        ProductResponseDTO productResponseDTO=ProductResponseDTO.builder()
                .descriptions(product.getDescriptions())
                .category(product.getProductCategories().getCategory())
                .weight(product.getWeight())
                .price(product.getPrice())
                .id(product.getId())
                .name(product.getName())
                .build();
        return productResponseDTO;
    }

}
