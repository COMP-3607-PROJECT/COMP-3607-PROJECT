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

}

