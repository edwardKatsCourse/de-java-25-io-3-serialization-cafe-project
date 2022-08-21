package service;

import entity.Product;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProductService {

    void createProduct() throws IOException;

    void listProductNames();

    void listProductNamesAvailable();

    void listProductInfoProductsFile(Product product);

    void listProductInfoSearchByCategory(Product product) throws FileNotFoundException;

    void deleteProductByFullPath(Product product);

    void deleteProductByLookup(Product product) throws IOException;

    void updateProductInfo();
}
