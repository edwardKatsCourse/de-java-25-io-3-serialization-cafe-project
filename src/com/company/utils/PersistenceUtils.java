package com.company.utils;

import com.company.model.Product;

import java.io.*;

public class PersistenceUtils {

    public static <T extends Serializable> void saveObject(String path, T object) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {

            oos.writeObject(object);
            oos.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product readProduct(String path) {
        try(var ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Product) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String normalizedProductPath(Product product) {
        return Constants.BASE_FOLDER +
                File.separator +
                product.getCategory().toLowerCase() +
                File.separator +
                product.getName().toLowerCase() +
                Constants.PRODUCT_FILENAME_SUFFIX;
    }

}
