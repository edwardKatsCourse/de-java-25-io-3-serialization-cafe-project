package impl;

import entity.Product;
import service.ProductDeletingService;

import java.io.*;
import java.nio.file.Files;

public class ProductDeletingImpl implements ProductDeletingService {
    static String path = "cafe" + File.separator + "products" + File.separator;
    static final String pathGeneralFile = "cafe" + File.separator + "products.cafe";

    @Override
    public void deleteProductByFullPath(Product product) throws IOException {
        File file = new File(pathGeneralFile);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isBlank() && line.startsWith(product.getName())) {
                    removeLineFromFile(line, file);
                    new File(path
                            + product.getCategory() + File.separator
                            + product.getName() + ".cafe").delete();
                    break;
                }
            }
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
        File file = new File(path
                + product.getCategory() + File.separator
                + product.getName() + ".cafe");
        if (file.exists()) {
            file.delete();
        }
        removeLineFromFile(product.getName(), new File(pathGeneralFile));
    }


}
