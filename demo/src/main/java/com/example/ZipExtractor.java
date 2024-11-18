package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.List;
import java.util.ArrayList;

public class ZipExtractor implements ExplorerContainer {
    private static List<String> studentDirectories;

    public ZipExtractor() {
        studentDirectories = new ArrayList<>();
    }

    public static List<String> getStudentDirectories() {
        return studentDirectories;
    }

    /**
     * Extracts all zipped folders inside the submission.zip and places them
     * directly in the parent directory.
     *
     * @param zipFilePath    The path to the main submission.zip file.
     * @param destinationDir The directory to place extracted contents.
     * @throws IOException
     */
    public static void extractSubmissionZip(String zipFilePath, String destinationDir) throws IOException {
        // Get the parent directory of the zip file
        String parentDir = new File(zipFilePath).getParent();
        
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();
    
                // Only process files ending in .zip
                if (entryName.endsWith(".zip")) {
                    File nestedZipFile = new File(parentDir, new File(entryName).getName());  // Use the parent directory here
                    createParentDirs(nestedZipFile);
                    System.out.println(parentDir);
    
                    // Write the zipped file to the parent directory
                    try (FileOutputStream fos = new FileOutputStream(nestedZipFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                    System.out.println("Extracted zip file: " + nestedZipFile.getName());
    
                    // Unzip the nested zip file into the parent directory
                    String unzippedFolder = parentDir + File.separator + nestedZipFile.getName().replace(".zip", "");
                    unzipFile(nestedZipFile.getPath(), unzippedFolder);
    
                    // Add the directory path to the list
                    studentDirectories.add(unzippedFolder);
    
                    // Optionally delete the extracted zip file to keep only its contents
                    nestedZipFile.delete();
                }
    
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Unzips a zip file into a specified directory.
     *
     * @param zipFilePath    The path to the zip file to unzip.
     * @param destinationDir The directory to extract the zip file contents into.
     * @throws IOException
     */
    public static void unzipFile(String zipFilePath, String destinationDir) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                File file = new File(destinationDir, entry.getName());
    
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // Ensure parent directories exist
                    file.getParentFile().mkdirs();
    
                    // Write the file to disk
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Creates any missing parent directories for a given file.
     *
     * @param file The file for which to create missing parent directories.
     */
    public static void createParentDirs(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }
    }

    public DirectoryIterator createDirectoryIterator() {
        return new DirectoryIterator(getStudentDirectories());

    }

}