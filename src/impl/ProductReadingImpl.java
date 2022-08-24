package impl;

import entity.Product;
import service.ProductReadingService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReadingImpl implements ProductReadingService {

    static String path = "cafe" + File.separator + "products" + File.separator;
    static final String pathGeneralFile = "cafe" + File.separator + "products.cafe";

    @Override
    public List<String> listProductNames() throws IOException {
        List<String> productNames = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathGeneralFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank()) {
                    String name = "";
                    for (char ch : line.toCharArray()) {
                        if (ch == '^') {
                            productNames.add(name);
                            break;
                        }
                        name += ch;
                    }
                }
            }
        }
        return productNames;
    }

    @Override
    public List<String> listProductNamesAvailable() throws IOException {
        List<String> productNames = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathGeneralFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.endsWith("true")) {
                    String name = "";
                    for (char ch : line.toCharArray()) {
                        if (ch == '^') {
                            productNames.add(name);
                            break;
                        }
                        name += ch;
                    }
                }
            }
        }
        return productNames;
    }

    @Override
    public String listProductInfoProductsFile(Product product) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathGeneralFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.startsWith(product.getName())) {
                    return line;
                }
            }
            throw new FileNotFoundException();
        }
    }

    @Override
    public String listProductInfoSearchByCategory(Product product) throws IOException {
        if (!Check.categoryExists(product.getCategory())) {
            throw new FileNotFoundException();
        }
        //TODO findPath()
        File directory = new File(path + product.getCategory());
        var files = directory.listFiles();
        for (File file: files) {
            if (file.getName().equalsIgnoreCase(product.getName() + ".cafe")) {
                return readFile(file);
            }
        }
        throw new FileNotFoundException();
    }

    private static String readFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.readLine();
        }
    }
}