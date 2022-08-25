package com.company.service.impl;

import com.company.model.Product;
import com.company.model.ProductList;
import com.company.repository.ProductPersistenceRepository;
import com.company.repository.impl.ProductPersistenceRepositoryImpl;
import com.company.service.ProductReadingService;

import java.io.*;
import java.util.List;

public class ProductReadingServiceImpl implements ProductReadingService {

    private static final ProductPersistenceRepository persistenceService = new ProductPersistenceRepositoryImpl();

    @Override
    public List<String> listProductNames() throws IOException {
        return persistenceService.getProductList()
                .stream()
                .map(ProductList::getProductName)
                .toList()
                ;
    }

    @Override
    public List<String> listProductNamesAvailable() throws IOException {
        return persistenceService.getProductList().stream()
                .filter(ProductList::isAvailable)
                .map(ProductList::getProductName)
                .toList()
                ;
    }

    @Override
    public String findProductInfo(String productPath) {
        return persistenceService.getProductMap()
                .get(productPath)
                .toString()
                ;
    }

    @Override
    public String findProductInfo(String category, String productName) {
        var products = persistenceService.getCategoryProducts().get(category);
        if (products == null || products.isEmpty()) {
            return null;
        }

        return products.stream()
                .filter(x -> x.getName().equals(productName))
                .findFirst()
                .map(Product::getName)
                .orElse(null)
                ;

    }
}