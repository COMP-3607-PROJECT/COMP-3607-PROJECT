package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {

    /**
     * Unzips a ZIP file to the specified output directory.
     *
     * @param zipFilePath the path to the ZIP file
     * @param outputDirPath the directory where the ZIP content should be extracted
     * @throws IOException if an I/O error occurs
     */
    public static void unzip(String zipFilePath, String outputDirPath) throws IOException {
        Path outputDir = Paths.get(outputDirPath);
        
        // Ensure the output directory exists
        if (Files.notExists(outputDir)) {
            Files.createDirectories(outputDir);
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            
            while ((entry = zis.getNextEntry()) != null) {
                Path filePath = outputDir.resolve(entry.getName());

                // If it's a directory, create it
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    // Ensure parent directories exist
                    Files.createDirectories(filePath.getParent());
                    
                    // Extract the file
                    try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\craft\\Downloads\\Jaheim_Caesar_816035438_A1.zip";
        String outputDirPath = "C:\\Users\\craft\\Downloads\\New folder (5)";

        try {
            unzip(zipFilePath, outputDirPath);
            System.out.println("Unzip complete. Files are extracted to: " + outputDirPath);
        } catch (IOException e) {
            System.err.println("Error occurred during unzip: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

