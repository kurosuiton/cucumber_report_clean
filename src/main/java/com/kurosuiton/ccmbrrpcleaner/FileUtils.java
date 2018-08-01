package com.kurosuiton.ccmbrrpcleaner;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Artem_Velichkin
 */
class FileUtils {

    static void writeToFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Reader from file
     *
     * @param path path to file
     *
     * @return return String from file
     */
    static String fileReader(String path) {
        final String[] result1 = {""};
        try {
            Files.lines(Paths.get(path), StandardCharsets.UTF_8).forEach(o -> result1[0] += o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result1[0];
    }
}
