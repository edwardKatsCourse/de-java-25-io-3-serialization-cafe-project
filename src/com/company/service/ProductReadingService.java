package com.company.service;

import java.io.IOException;
import java.util.List;

public interface ProductReadingService {

    List<String> listProductNames() throws IOException;

    List<String> listProductNamesAvailable() throws IOException;

    String findProductInfo(String productPath) throws IOException;

    String findProductInfo(String category, String productName) throws IOException;
}
