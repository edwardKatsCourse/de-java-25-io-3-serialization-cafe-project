package com.company.repository.impl;

import com.company.model.Product;
import com.company.model.ProductList;
import com.company.repository.ProductInitializationRepository;
import com.company.repository.ProductPersistenceRepository;
import com.company.utils.Constants;
import com.company.utils.PersistenceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductPersistenceRepositoryImpl implements ProductPersistenceRepository {

    private static final ArrayList<ProductList> productList;
    private static final Map<String, Product> pathToProductMap;
    private static final Map<String, List<Product>> categoryToProductMap;
    private static final ProductInitializationRepository productInitService = new ProductInitializationRepositoryImpl();


    static {
        try {
            productList = productInitService.getProductList();
            pathToProductMap = productInitService.getAllProducts(productList);
            categoryToProductMap =
                    pathToProductMap
                            .values()
                            .stream()
                            .collect(Collectors.groupingBy(
                                    Product::getCategory,
                                    Collectors.mapping(
                                            product -> product,
                                            Collectors.toList()
                                    )
                            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void persist(Product product) {
        try {
            String productPath = PersistenceUtils.normalizedProductPath(product);

            if (!pathToProductMap.containsKey(productPath)) {

                File categoryFolder = new File(Constants.BASE_FOLDER + File.separator + product.getCategory());
                if (!categoryFolder.exists()) {
                    categoryFolder.mkdirs();
                }

                new File(productPath).createNewFile();

                productList.add(new ProductList(product.getName(), productPath, product.isAvailable()));
                pathToProductMap.put(productPath, product);
            }


            PersistenceUtils.saveObject(Constants.PRODUCT_LIST_PATH, productList);
            PersistenceUtils.saveObject(productPath, product);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<ProductList> getProductList() {
        return productList;
    }


    @Override
    public Map<String, Product> getProductMap() {
        return pathToProductMap;
    }

    @Override
    public Map<String, List<Product>> getCategoryProducts() {
        return categoryToProductMap;
    }
}
