import com.company.model.Product;
import com.company.repository.ProductPersistenceRepository;
import com.company.repository.impl.ProductPersistenceRepositoryImpl;
import com.company.service.*;
import com.company.service.impl.*;
import com.company.utils.ConsoleUtils;

import java.io.IOException;

public class Main {

    private static final ProductCreationService productCreationService = new ProductCreationServiceImpl();
    private static final ProductReadingService productReadingService = new ProductReadingServiceImpl();
    private static final ProductDeletingService productDeletingService = new ProductDeletingServiceImpl();

    private static final ProductPersistenceRepository persistenceRepository = new ProductPersistenceRepositoryImpl();
    public static void main(String[] args) {

        productCreationService.createProduct(readProduct());

        System.out.println(persistenceRepository.getProductList());
        System.out.println(persistenceRepository.getProductMap());
    }

    private static Product readProduct() {
        String name = ConsoleUtils.getConsoleInput("Enter the product's name");
        double price = Double.parseDouble(
                ConsoleUtils.getConsoleInput("Enter the product's price")
        );
        String description = ConsoleUtils.getConsoleInput("Enter the product's description");
        String category = ConsoleUtils.getConsoleInput("Enter the product's category");
        boolean isAvailable = Boolean.parseBoolean(
                ConsoleUtils.getConsoleInput("Enter the product's isAvailable")
        );

        return new Product(name, price, description, category, isAvailable);
    }
}