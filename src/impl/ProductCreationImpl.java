package impl;

import entity.Product;
import service.ProductCreationService;

import java.io.*;

public class ProductCreationImpl implements ProductCreationService {

    @Override
    public void createProduct() throws IOException {

        String name = Concole.getConsoleInput("Enter the product's name");
        Double price = Double.valueOf(
                Concole.getConsoleInput("Enter the product's price")
        );
        String description = Concole.getConsoleInput("Enter the product's description");
        String category = Concole.getConsoleInput("Enter the product's category");
        Boolean isAvailable = Boolean.valueOf(
                Concole.getConsoleInput("Enter the product's isAvailable")
        );

        if (Check.isUniqueUnderCategory(category, name)) {
            Product product = new Product(name, price, description, category, isAvailable);
            String path = ProductFile.createProductFile(product);
            ProductFile.addProductToProductsFile(product, path);

            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(product);
            oos.flush();
            oos.close();
        }
    }
}
