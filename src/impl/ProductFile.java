package impl;

import entity.Product;

import java.io.*;

public class ProductFile {

    public static String createProductFile(Product product) {

        String path = "cafe" + File.separator
                + "products" + File.separator
                + product.getCategory();

        if ( !Check.categoryExists(product.getCategory())) {
            File directory = new File(path);
            directory.mkdirs();

        }
            File productFile = new File(path + File.separator
                    + product.getName() + ".cafe");

        try(FileWriter fileWriter = new FileWriter(productFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

        // name^price^description^category^isAvailable
            StringBuilder builder = new StringBuilder(product.getName());
            builder.append("^");
            builder.append(product.getPrice());
            builder.append("^");
            builder.append(product.getDescription());
            builder.append("^");
            builder.append(product.getCategory());
            builder.append("^");
            builder.append(product.getIsAvailable());

            bufferedWriter.write(builder.toString());
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return productFile.getPath();
    }

    public static void addProductToProductsFile(Product product, String path) {
        File productsFile = new File("cafe" + File.separator + "products.cafe");

        try(FileWriter fileWriter = new FileWriter(productsFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            // product_name^/path/to/product^is_available
            StringBuilder builder = new StringBuilder(product.getName());
            builder.append("^/");
            builder.append(path);
            builder.append("/isAvailable^");
            builder.append(product.getIsAvailable());

            bufferedWriter.write(builder.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
