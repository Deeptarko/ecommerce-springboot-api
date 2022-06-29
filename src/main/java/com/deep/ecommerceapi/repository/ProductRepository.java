package com.deep.ecommerceapi.repository;

import com.deep.ecommerceapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

  List<Product> findAllByProductCategoriesCategory(String productCategory);
}
