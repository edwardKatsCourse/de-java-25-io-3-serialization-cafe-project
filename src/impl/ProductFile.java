package impl;

import entity.Product;

import java.io.*;

public class ProductFile {

    static String path = "cafe" + File.separator + "products" + File.separator;
    static final String pathGeneralFile = "cafe" + File.separator + "products.cafe";

    public static String createProductFile(Product product) throws IOException {

        String categoryDirectory = path + product.getCategory();

        if ( !Check.categoryExists(product.getCategory())) {
            File directory = new File(categoryDirectory);
            directory.mkdirs();

        }
        File productFile = new File(categoryDirectory + File.separator
                    + product.getName() + ".cafe");

        writeProductFile(productFile, product);

        return productFile.getPath();
    }

    public static void writeProductFile(File productFile, Product product) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(productFile))) {

            bufferedWriter.write(String.format("%s^%s^%s^%s^%s",
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getIsAvailable()));
            bufferedWriter.flush();
        }
    }

    public static void addProductToProductsFile(Product product, String pathProductFile) throws IOException {
        File generalFile = new File(pathGeneralFile);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(generalFile, true))) {

            // product_name^/path/to/product^is_available
            bufferedWriter.write(String.format("%s^/%s%s%s",
                    product.getName(),
                    pathProductFile,
                    "/isAvailable^",
                    product.getIsAvailable()));
            bufferedWriter.flush();
        }
    }
}
