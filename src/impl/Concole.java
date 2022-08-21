package impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Concole {
    public static String getConsoleInput(String caption) throws IOException {
        System.out.println(caption);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        StringBuilder builder = new StringBuilder();

        while (true) {
            line = bufferedReader.readLine();

            if (line == null || line.isBlank()) {
                break;
            }

            builder.append(line);
        }

        return builder.toString();

    }
}
