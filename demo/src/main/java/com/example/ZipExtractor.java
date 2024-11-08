package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {

    // public static void main(String[] args) {
    // // Path to the main zip file (submission.zip)
    // String mainZipFilePath = "C:\\Users\\darri\\OneDrive - The University of the
    // West Indies, St. Augustine\\Year3\\COMP 3607\\TEST1\\submission.zip";

    // // Get the parent directory of submission.zip
    // File mainZipFile = new File(mainZipFilePath);
    // String parentDir = mainZipFile.getParent();

    // try {
    // // Extract the main zip file (submission.zip)
    // extractSubmissionZip(mainZipFilePath, parentDir);
    // System.out.println("Extraction complete.");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * Extracts the main submission.zip file and handles nested structures.
     * 
     * @param zipFilePath    The path to the main submission.zip file.
     * @param destinationDir The directory to place extracted contents.
     * @throws IOException
     */

    public static void extractSubmissionZip(String zipFilePath, String destinationDir) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();

                if (entry.isDirectory()) {

                    new File(destinationDir, entryName).mkdirs();
                } else if (entryName.startsWith("submission/") && entryName.endsWith(".zip")) {

                    File nestedZipFile = new File(destinationDir, entryName);
                    createParentDirs(nestedZipFile);

                    try (FileOutputStream fos = new FileOutputStream(nestedZipFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                    System.out.println("Extracted nested zip file: " + nestedZipFile.getName());

                    // Extract .java files to a folder named after the student zip file
                    String studentFolderName = nestedZipFile.getName().replace(".zip", "");
                    File studentFolder = new File(destinationDir, studentFolderName);
                    studentFolder.mkdirs();

                    extractJavaFilesFromStudentZip(nestedZipFile.getPath(), studentFolder.getPath());
                }

                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Extracts all .java files from a student's zip file into a specified folder.
     *
     * @param zipFilePath    The path to the student's zip file.
     * @param destinationDir The directory (student folder) to place extracted .java
     *                       files.
     * @throws IOException
     */
    private static void extractJavaFilesFromStudentZip(String zipFilePath, String destinationDir) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String fileName = entry.getName();
                String filePath = destinationDir + File.separator + fileName;

                if (!entry.isDirectory() && fileName.endsWith(".java")) {
                    // Ensure parent directories exist
                    File javaFile = new File(filePath);
                    createParentDirs(javaFile);

                    // Extract the .java file into the student's specific folder
                    try (FileOutputStream fos = new FileOutputStream(javaFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                    System.out.println("Extracted Java file: " + javaFile.getName() + " to " + destinationDir);
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
}
