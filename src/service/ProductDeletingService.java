package service;

import entity.Product;

import java.io.IOException;

public interface ProductDeletingService {
    void deleteProductByFullPath(Product product) throws IOException;

    void deleteProductByLookup(Product product) throws IOException;
}
