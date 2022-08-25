package com.company.repository;

import com.company.model.Product;
import com.company.model.ProductList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface ProductInitializationRepository {

    ArrayList<ProductList> getProductList() throws IOException;
    Map<String, Product> getAllProducts(ArrayList<ProductList> productList) throws IOException;
}
