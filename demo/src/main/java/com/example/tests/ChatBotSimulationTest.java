package com.example.tests;
import com.example.validoutput.ValidBotsList;
import com.example.validoutput.ValidHelloWorld;
import com.example.ChatBotSimulation;
import com.example.TestRunner;
import com.example.validoutput.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.validateMockitoUsage;

import org.junit.jupiter.api.Test;

public class ChatBotSimulationTest extends TestRunner{

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private URLClassLoader urlClassLoader;
    private Class<?> clazz;
    private final String output;

    public ChatBotSimulationTest(URL classesURL) throws ClassNotFoundException{
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});        
        clazz = urlClassLoader.loadClass("ChatBotSimulation");
        output = setUpOutput();
    }

    public String setUpOutput(){
        setUp();
        try {
            Method method = clazz.getDeclaredMethod("main", String[].class);
            method.invoke(null, new String[1]);            
            return outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
        finally{
            tearDown();
        }
        
        
    }
    private boolean containsAllBots(String input) {
        if(input == null){
            return false;
        }
        input = input.replaceAll("\\s+", "");
        List<String> expectedBots = Arrays.asList("ChatGPT-3.5", "LLaMa", "Mistral7B", "Bard", "Claude", "Solar");
        Set<String> foundBots = new HashSet<>();
        String botPattern = "BotNumber:\\d+ChatBotName:([\\w\\-.]+)NumberMessagesUsed:\\d+";
        Pattern pattern = Pattern.compile(botPattern);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String botName = matcher.group(1);
            foundBots.add(botName);
        }
        return foundBots.containsAll(expectedBots);
    }

    @Test
    public void testHelloWorld(){
        try {
            setStrategy(new ValidHelloWorld());
            // Assertions.assertTrue(output.contains("Hello World!"));
            Assertions.assertTrue(validOutputStrategy.isValidOutput(output));
            signal(1, "testHellowWorld");
        } catch (AssertionError e) {
            signal(0, "testHelloWorld");
        }
       
        // setUp();
        // try {
        //     Method method = clazz.getDeclaredMethod("main", String[].class);
        //     Assertions.assertAll(
        //         ()->{
        //             Assertions.assertTrue(Modifier.isStatic(method.getModifiers()));
        //         },
        //         () ->{
        //             Assertions.assertTrue(Modifier.isPublic(method.getModifiers()));
        //         },
        //         () -> {
        //             method.invoke(null, new String[1]);
        //             Assertions.assertTrue(output.contains("Hello World!"));
        //             signal(1, "testHelloWorld");
        //         }
        //     );
        // } catch (Throwable e) {
        //     signal(0, "testHelloWorld");
        //     e.printStackTrace();
        // }
        // finally{
        //     tearDown();
        // }
    }

    @Test
    public void testChatBotList(){
        try {
            setStrategy(new ValidBotsList());
            Assertions.assertTrue(containsAllBots(output));
            signal(5, "testChatBotList");
        } catch (AssertionError e) {
            signal(0, "testChatBotList");
        }
        // setUp();
        // try {
        //     Method method = clazz.getDeclaredMethod("main", String[].class);
        //     Assertions.assertAll(
        //         ()->{
        //             Assertions.assertTrue(Modifier.isStatic(method.getModifiers()));
        //         },
        //         () ->{
        //             Assertions.assertTrue(Modifier.isPublic(method.getModifiers()));
        //         },
        //         () -> {
        //             tearDown();
        //             method.invoke(null, (Object)new String[0]);
                    
        //             System.out.println(1);
        //             System.err.println(outputStream.toString());
        //             Assertions.assertTrue(containsAllBots(output));
        //             signal(1, "testChatBotList");
        //         }
        //     );
        // } catch (Throwable e) {
        //     System.err.println("1");
        //     signal(0, "testChatBotList");
        //     e.printStackTrace();
        // }
        // finally{
        //     tearDown();
        // }
    }

    @Test
    public void testChatBotInteractions(){
            try {
                setStrategy(new ValidInteractions());
                Assertions.assertTrue(validOutputStrategy.isValidOutput(output));
                signal(4, "testChatBotInteractions");
            } catch (AssertionError e) {
                e.printStackTrace();
                signal(0, "testChatBotInteractions");
            }
    }

    @Test
    public void testFinalChatBotList(){
        try {
            setStrategy(new ValidBotsList());
            Assertions.assertTrue(validOutputStrategy.isValidOutput(output));
            signal(2, "testFinalChatBotList");
        } catch (Exception e) {
            signal(0, "testFinalChatbotList");
        }
    }

    
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Capture the System.out output
    }

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
    public void testInteractionAndOutput() throws Exception {
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

