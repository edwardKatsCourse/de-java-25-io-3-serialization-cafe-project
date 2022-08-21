import entity.Product;
import impl.ProductServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ProductServiceImpl impl = new ProductServiceImpl();
       // impl.createProduct();

//        System.out.println("Available products:");
//        impl.listProductNamesAvailable();
//        System.out.println("\nAll products:");
//        impl.listProductNames();

       // impl.listProductInfoProductsFile("milk");

        //impl.listProductInfoSearchByCategory(new Product("coffee", 2.5, "justCoffee", "drinks", true));
       // impl.listProductInfoSearchByCategory(new Product("coffee", 2.5, "justCoffee", "dr", true));
        //impl.listProductInfoSearchByCategory(new Product("cof", 2.5, "justCoffee", "drinks", true));

        //impl.deleteProductByLookup(new Product("coffee", 2.5, "justCoffee", "drinks", true));
        impl.deleteProductByFullPath(new Product("coffee", 2.5, "justCoffee", "drinks", true));
    }
}