package service;

import entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductReadingService {

    List<String> listProductNames() throws IOException;

    List<String> listProductNamesAvailable() throws IOException;

    String listProductInfoProductsFile(Product product) throws IOException;

    String listProductInfoSearchByCategory(Product product) throws IOException;
}
