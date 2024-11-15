package com.example;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChatBotSimulationTest extends TestRunner{

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private URLClassLoader urlClassLoader;
    private Class<?> clazz;

    public ChatBotSimulationTest(URL classesURL) throws ClassNotFoundException{
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});        
        clazz = urlClassLoader.loadClass("ChatBot");
    }

    @Test
    void testHelloWorld(){
        try {
            
        } catch (Throwable e) {
        }
    }

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Capture the System.out output
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restore the original System.out
    }

    // @Test
    // void testPlatformInitialization() throws Exception {
    //     // Run the main method of ChatBotSimulation
    //     outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));
    //     ChatBotSimulation.main(new String[]{});

    //     // Use reflection to check if the ChatBotPlatform instance was created
    //     Field[] fields = ChatBotSimulation.class.getDeclaredFields();
    //     Object platform = null;

    //     for (Field field : fields) {
    //         if (field.getType().equals(ChatBotPlatform.class)) {
    //             field.setAccessible(true);
    //             platform = field.get(null); // Retrieve the value of the static field
    //             break;
    //         }
    //     }

    //     assertNotNull(platform, "ChatBotPlatform instance should be initialized.");
    //     assertTrue(platform instanceof ChatBotPlatform, "The object should be an instance of ChatBotPlatform.");
    //     System.setOut(originalOut);
    // }

    // @Test
    // public void testChatBotPlatformInstance() throws Exception {
    //     ChatBotSimulation simulation = new ChatBotSimulation();
    //     simulation.main(null);
    //     Field fields[] = ChatBotSimulation.class.getDeclaredFields();
    //     System.out.println(1);
    //     for(Field fieldd: fields){
    //         System.out.println(fieldd);
    //     }
    //     Field field = ChatBotSimulation.class.getDeclaredField("Platform");
    //     field.setAccessible(true);
    //     ChatBotPlatform platform = (ChatBotPlatform) field.get(simulation);
    //     assertNotNull(platform);
    // }

    @Test
    public void testChatBotPlatformInstantiation() throws Exception {
        // Run the main method of ChatBotSimulation
        ChatBotSimulation.main(null);

        // Get the declared fields of the ChatBotSimulation class
        Class<?> clazz = ChatBotSimulation.class;
        Method mainMethod = clazz.getDeclaredMethod("main", String[].class);

        // Check if the main method has any fields instantiated of type ChatBotPlatform
        Field[] fields = clazz.getDeclaredFields();
        System.out.println(fields);
        for(Field field: fields){
            System.out.println(field);
        }
        boolean platformExists = Arrays.stream(fields)
                .anyMatch(field -> field.getType().getSimpleName().equals("ChatBotPlatform"));

        // Assert that a ChatBotPlatform was instantiated
        assertTrue(platformExists, "ChatBotPlatform should be instantiated in main method");
    }

    @Test
    void testInteractionAndOutput() throws Exception {
        // Capture the output of the main method
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ChatBotSimulation.main(new String[]{});
        String output = outputStream.toString();

        // Check that "Hello World!" is printed
        assertTrue(output.contains("Hello World!"), "Output should contain 'Hello World!'");

        // Check that the platform's chatbot list is printed
        assertTrue(output.contains("Your ChatBots"), "Output should contain the list of chatbots.");

        // Ensure the interaction output is printed (this could vary based on random values)
        assertTrue(output.contains("Hi") || output.contains("Hello") || output.contains("Hey"),
                "Output should contain chatbot interactions such as 'Hi', 'Hello', or 'Hey'");

        // Check that the output contains the final chatbot list
        assertTrue(output.contains("Total Messages Used"), "Output should contain the final chatbot list statistics.");
        System.setOut(originalOut);
    }
}

