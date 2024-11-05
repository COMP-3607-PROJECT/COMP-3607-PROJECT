package com.example;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ChatBotTester {

    private boolean compileJavaFile(String sourceFilePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        
        // Check if JavaCompiler is available
        if (compiler == null) {
            System.out.println("JavaCompiler not available. Ensure the program runs on a JDK, not a JRE.");
            return false;
        }
    
        // Specify options for Java 8 compatibility (-source 1.8 -target 1.8)
        String[] options = new String[]{"-source", "1.8", "-target", "1.8", sourceFilePath};
    
        // Compile the file
        int result = compiler.run(null, null, null, options);
        return result == 0; // 0 indicates successful compilation
    }
    

    private Class<?> loadChatBotClass(String directoryPath, String className) throws Exception {
        File directory = new File(directoryPath);
        URL[] urls = { directory.toURI().toURL() };
        URLClassLoader classLoader = new URLClassLoader(urls);

        return classLoader.loadClass(className);
    }

    public void checkChatBotNameField() {
        try {
            String sourceFilePath = "C:\\Users\\craft\\Downloads\\New folder (5)\\Jaheim_Caesar_816035438_A1\\ChatBot.java"; // Update with actual path
            String directoryPath = "C:\\Users\\craft\\Downloads\\New folder (5)\\Jaheim_Caesar_816035438_A1"; // Update with actual path
            
            // Step 1: Compile ChatBot.java if necessary
            if (!compileJavaFile(sourceFilePath)) {
                System.out.println("Failed to compile ChatBot.java");
                return;
            }

            // Step 2: Load the ChatBot class
            Class<?> chatBotClass = loadChatBotClass(directoryPath, "ChatBot");

            // Step 3: Check for the existence and properties of "chatBotName"
            Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");

            boolean isPrivate = Modifier.isPrivate(chatBotNameField.getModifiers());
            boolean isStringType = chatBotNameField.getType().equals(String.class);

            if (isPrivate && isStringType) {
                System.out.println("The field 'chatBotName' exists, is private, and of type String.");
            } else {
                System.out.println("The field 'chatBotName' does not meet the required specifications.");
            }
        } catch (NoSuchFieldException e) {
            System.out.println("The field 'chatBotName' does not exist in the ChatBot class.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatBotTester tester = new ChatBotTester();
        tester.checkChatBotNameField();
    }
}
