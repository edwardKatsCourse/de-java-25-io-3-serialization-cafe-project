import entity.Product;
import impl.ProductCreationImpl;
import impl.ProductDeletingImpl;
import impl.ProductReadingImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ProductCreationImpl implCreation = new ProductCreationImpl();
        ProductReadingImpl implReading = new ProductReadingImpl();
        ProductDeletingImpl implDeleting = new ProductDeletingImpl();
       // implCreation.createProduct();

//        System.out.println("Available products:");
//        implReading.listProductNamesAvailable();
//        System.out.println("\nAll products:");
//        implReading.listProductNames();

       // implReading.listProductInfoProductsFile("milk");

        //implReading.listProductInfoSearchByCategory(new Product("coffee", 2.5, "justCoffee", "drinks", true));
       // implReading.listProductInfoSearchByCategory(new Product("coffee", 2.5, "justCoffee", "dr", true));
        //implReading.listProductInfoSearchByCategory(new Product("cof", 2.5, "justCoffee", "drinks", true));

        //impl.deleteProductByLookup(new Product("coffee", 2.5, "justCoffee", "drinks", true));
        implDeleting.deleteProductByFullPath(new Product("coffee", 2.5, "justCoffee", "drinks", true));
    }
}