package dto;

import entity.Product;

import java.io.*;

public class ProductDTO {

//    private static void serialize() throws IOException {
//        try (FileOutputStream fos = new FileOutputStream("john.employee");
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//
//            Product john = new Employee("John", 35, "qwerty123456");
//
//            oos.writeObject(john);
//            oos.flush();
//
//        }
//    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("john.employee");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Product product = (Product) ois.readObject();
            System.out.println(product);
        }
    }

}
