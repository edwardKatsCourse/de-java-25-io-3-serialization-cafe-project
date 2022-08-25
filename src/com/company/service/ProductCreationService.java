package com.company.service;

import com.company.model.Product;

public interface ProductCreationService {

    void createProduct(Product product);
    void createProduct(String name, double price, String description, String category, boolean isAvailable);
}
