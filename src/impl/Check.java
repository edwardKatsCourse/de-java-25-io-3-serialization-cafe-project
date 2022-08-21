package impl;

import java.io.File;

public class Check {
    public static boolean isUniqueUnderCategory(String category, String name) {

        if (!categoryExists(category)) {
            return true;
        }

        File directory = new File("cafe" + File.separator +
                "products" + File.separator  + category);

        var allFiles = directory.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                if (file.getName().equalsIgnoreCase(name + ".cafe")) {
                    return false;
                }
            }
        }

        return true;
    }


    public static boolean categoryExists(String category) {
        File directory = new File(
                "cafe" + File.separator +
                        "products" + File.separator
                        + category
        );

        if (directory.exists()) {
            return true;
        }

        return false;
    }
}
