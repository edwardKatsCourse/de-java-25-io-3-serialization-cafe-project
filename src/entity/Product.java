package entity;

import impl.Check;
import impl.ProductFile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    Double price;
    String description;
    String category;
    Boolean isAvailable;

//    public Product(String name, Double price, String description, String category, Boolean isAvailable) throws IOException {
//
//        if (Check.isUniqueUnderCategory(category, name)) {
//
//            this.name = name;
//            this.price = price;
//            this.description = description;
//            this.category = category;
//            this.isAvailable = isAvailable;
//            String path = ProductFile.createProductFile(this);
//            ProductFile.addProductToProductsFile(this, path);
//        }
//    }
}
