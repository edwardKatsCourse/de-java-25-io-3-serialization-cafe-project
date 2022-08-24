package service;

import entity.Product;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProductCreationService {

    void createProduct() throws IOException;
}
