package com.company.service.impl;

import com.company.model.Product;
import com.company.repository.ProductPersistenceRepository;
import com.company.repository.impl.ProductPersistenceRepositoryImpl;
import com.company.service.ProductCreationService;

public class ProductCreationServiceImpl implements ProductCreationService {

    private static final ProductPersistenceRepository repository = new ProductPersistenceRepositoryImpl();

    @Override
    public void createProduct(Product product) {
        repository.persist(product);
    }

    @Override
    public void createProduct(String name, double price, String description, String category, boolean isAvailable) {
        var product = new Product(
                name,
                price,
                description,
                category,
                isAvailable
        );

        this.createProduct(product);
    }
}
