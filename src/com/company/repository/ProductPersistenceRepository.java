package com.company.repository;

import com.company.model.Product;
import com.company.model.ProductList;

import java.util.List;
import java.util.Map;

public interface ProductPersistenceRepository {

    void persist(Product product);
    List<ProductList> getProductList();
    Map<String, Product> getProductMap();
    Map<String, List<Product>> getCategoryProducts();
}
