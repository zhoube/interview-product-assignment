package com.example.interviewproductassignment.service;

import com.example.interviewproductassignment.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(String name, BigDecimal price);
}
