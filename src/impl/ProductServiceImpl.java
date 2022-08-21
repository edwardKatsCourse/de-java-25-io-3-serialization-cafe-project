package impl;

import entity.Product;
import service.ProductService;

import java.io.*;
import java.nio.file.Files;

public class ProductServiceImpl implements ProductService {
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
        }

    }

    @Override
    public void listProductNames() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cafe" + File.separator + "products.cafe"))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank()) {
                    for (char ch : line.toCharArray()) {
                        if (ch == '^') {
                            System.out.println();
                            break;
                        }
                        System.out.print(ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void listProductNamesAvailable() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cafe" + File.separator + "products.cafe"))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.endsWith("true")) {
                    for (char ch : line.toCharArray()) {
                        if (ch == '^') {
                            System.out.println();
                            break;
                        }
                        System.out.print(ch);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listProductInfoProductsFile(Product product) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cafe" + File.separator + "products.cafe"))) {
            boolean found = false;
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.startsWith(product.getName())) {
                    System.out.println(line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new FileNotFoundException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listProductInfoSearchByCategory(Product product) throws FileNotFoundException {
        if (!Check.categoryExists(product.getCategory())) {
            throw new FileNotFoundException();
        }
        File directory = new File("cafe" + File.separator
                + "products" + File.separator
                + product.getCategory());
        var files = directory.listFiles();
        boolean fileFound = false;
        for (File file: files) {
            if (file.getName().equalsIgnoreCase(product.getName() + ".cafe")) {
                readFile(file);
                fileFound = true;
                break;
            }
        }
        if (!fileFound) {
            throw new FileNotFoundException();
        }
    }

    private static void readFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProductByFullPath(Product product) {
        File file = new File("cafe" + File.separator + "products.cafe");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.startsWith(product.getName())) {
                    removeLineFromFile(line, file);
                    new File("cafe" + File.separator
                            + "products" + File.separator
                            + product.getCategory() + File.separator
                            + product.getName() + ".cafe").delete();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void removeLineFromFile(String lineContent, File file) throws IOException {
            File temp = new File("_temp_");
            PrintWriter out = new PrintWriter(new FileWriter(temp));
            Files.lines(file.toPath())
                    .filter(line -> !line.contains(lineContent))
                    .forEach(out::println);
            out.flush();
            out.close();
            temp.renameTo(file);
    }


    @Override
    public void deleteProductByLookup(Product product) throws IOException {
        File file = new File("cafe" + File.separator
                + "products" + File.separator
                + product.getCategory() + File.separator
                + product.getName() + ".cafe");
        if (file.exists()) {
            file.delete();
        }
        removeLineFromFile(product.getName(), new File("cafe" + File.separator + "products.cafe"));
    }

    @Override
    public void updateProductInfo() {

    }
}
