package com.example.interviewproductassignment.service.impl;

import com.example.interviewproductassignment.exception.ProductNotFoundException;
import com.example.interviewproductassignment.model.Product;
import com.example.interviewproductassignment.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {
    private final ConcurrentMap<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>(products.values());
        result.sort(Comparator.comparingLong(Product::getId));
        return result;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @Override
    public Product createProduct(String name, BigDecimal price) {
        Long id = idGenerator.incrementAndGet();
        Product product = new Product(id, name, price);
        products.put(id, product);
        return product;
    }
}
