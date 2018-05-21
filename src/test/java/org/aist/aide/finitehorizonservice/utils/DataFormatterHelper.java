package org.aist.aide.finitehorizonservice.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFormatterHelper {
    private static void formatNewDataFile() {
        Path workPath = Paths.get(System.getProperty("user.dir"), "/mongo-seed/workspace");
        Path outPath = Paths.get(System.getProperty("user.dir"), "/mongo-seed/out");
        try (BufferedReader workspaceInput = Files.newBufferedReader(workPath, StandardCharsets.UTF_8);
             FileWriter out = new FileWriter(outPath.toFile())) {
            StringBuilder builder = new StringBuilder();
            String[] lines = workspaceInput.lines().toArray(String[]::new);
            for (String line : lines) {
                builder.append("\"");
                builder.append(line);
                builder.append("\",\n");
            }
            out.write(builder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO ex");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        formatNewDataFile();
    }
}
