package com.company.utils;

import java.util.Scanner;

public class ConsoleUtils {
    public static String getConsoleInput(String caption) {
        System.out.println(caption);
        return new Scanner(System.in).nextLine();
    }
}
