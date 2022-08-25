package com.company.repository.impl;

import com.company.model.Product;
import com.company.model.ProductList;
import com.company.repository.ProductInitializationRepository;
import com.company.utils.Constants;
import com.company.utils.PersistenceUtils;
import com.company.utils.Tuple2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductInitializationRepositoryImpl implements ProductInitializationRepository {

    static {
        File baseDir = new File(Constants.BASE_FOLDER);
        if (!baseDir.exists()) {
            baseDir.mkdir();
        }
    }

    @Override
    public ArrayList<ProductList> getProductList() throws IOException {
        File file = new File(Constants.PRODUCT_LIST_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (var ois = new ObjectInputStream(new FileInputStream(Constants.PRODUCT_LIST_PATH))) {

            return (ArrayList<ProductList>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Product> getAllProducts(ArrayList<ProductList> productList) {
        if (productList.isEmpty()) {
            return new HashMap<>();
        }

        return productList.stream()
                .map(ProductList::getPath)
                .map(path -> new Tuple2<>(path, PersistenceUtils.readProduct(path)))
                .collect(
                        Collectors.toMap(
                                Tuple2::t1,
                                Tuple2::t2
                                // what to do with duplicates
                                // map type, i.e. TreeMap
                        )
                );
    }

}
