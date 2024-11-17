package com.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Compiler {
    public static boolean  compile(String folderToCompile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String javaVersion = System.getProperty("java.version");

        if (!javaVersion.startsWith("23")) {
            System.err.println("JDK 23 is required. Current version: " + javaVersion);
            return false;
        }

        if (compiler == null) {
            System.err.println("JDK not found. Make sure you're running this with a JDK, not a JRE.");
            return false;
        }

        String[] fileNamesToCompile = {
            "ChatBot.java",
            "ChatBotGenerator.java",
            "ChatBotPlatform.java",
            "ChatBotSimulation.java"
        };

        List<String> validFiles = new ArrayList<>();
        File folder = new File(folderToCompile);

        if (!folder.isDirectory()) {
            System.err.println("Provided path is not a directory.");
            return false;
        }

        for (String fileName : fileNamesToCompile) {
            File file = new File(folder, fileName);
            if (file.exists() && file.isFile()) {
                validFiles.add(file.getAbsolutePath());
            } else {
                System.err.println("File not found: " + fileName);
            }
        }

        if (validFiles.isEmpty()) {
            System.err.println("No valid files to compile.");
            return false;
        }

        String[] filesArray = validFiles.toArray(new String[0]);
        int compilationResult = compiler.run(null, null, null, filesArray);

        if (compilationResult == 0) {
            return true;
        } else {
            return false;
        }
    }
}
