/**
 * FileReader is responsible for reading the contents of a text file.
 * It provides a method to read a file line by line and output the contents to the console.
 *
 * The file is read using a BufferedReader, which allows for efficient reading of large files.
 *
 * Author: Giovanni L. Rozza
 * Date: January 30, 2025
 *
 * This class contains the following methods:
 *
 * - read(Path path): Reads the content of a file specified by the provided path and prints it line by line,
 * using UTF-8 character set.
 *
 * The read method utilizes a try-with-resources statement to ensure that the BufferedReader is closed
 * after the file is read, even if an error occurs.
 */
package br.edu.utfpr.sistemarquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public void read(Path path) throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
