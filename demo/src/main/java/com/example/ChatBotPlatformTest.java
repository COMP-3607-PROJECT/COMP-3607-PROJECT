package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

class ChatBotPlatformTest extends TestRunner {

    private URLClassLoader urlClassLoader;
    private Class<?> clazz;
    private Object obj;

    public ChatBotPlatformTest(URL classesURL) {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});
        try {
            clazz = urlClassLoader.loadClass("ChatBotPlatform");
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isPublic(Method method){
        int mod = method.getModifiers();
        return Modifier.isPublic(mod) || (!Modifier.isPrivate(mod) && !Modifier.isPublic(mod) && !Modifier.isProtected(mod)); 
        //Additionally checks if the method is package protected;
    }

    private boolean testResponse(String output) {
        List<String> allowedModels = Arrays.asList("LLaMa", "Mistral7B", "Bard", "Claude", "Solar", "ChatGPT-3.5");
        output = output.trim().replaceAll("\\s+", "");
    
        String modelResponseRegex = "^\\(Message#(1|2|3|4|5|6|7|8|9|10)\\)Responsefrom(LLaMa|Mistral7B|Bard|Claude|Solar|ChatGPT-3.5)>>generatedTextHere$";
        String botErrorRegex = "^IncorrectBotNumber\\((\\d+)\\)Selected\\.Tryagain$";
    
        Pattern modelPattern = Pattern.compile(modelResponseRegex);
        Matcher modelMatcher = modelPattern.matcher(output);
        if (modelMatcher.matches()) {
            String model = modelMatcher.group(2);
            return allowedModels.contains(model);
        }
    
        Pattern botErrorPattern = Pattern.compile(botErrorRegex);
        Matcher botErrorMatcher = botErrorPattern.matcher(output);
        if (botErrorMatcher.matches()) {
            String number = botErrorMatcher.group(1);
            try {
                int botNumber = Integer.parseInt(number);
                return botNumber > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        return false;
    }


    @Test
    void testBotsField() {
        try {
            Assertions.assertAll("bots Field Tests",
                () -> {
                    Field field = clazz.getDeclaredField("bots");
                    Assertions.assertEquals(ArrayList.class, field.getType(), "bots should be of type ArrayList");
                },
                () -> {
                    Field field = clazz.getDeclaredField("bots");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "bots should be private");
                }
            );
            signal(2, "testBotsField");
        } catch (Exception e) {
            signal(0, "testBotsField");
            e.printStackTrace();
        }
        catch(AssertionError e){
            signal(0, "testBotsField");
        }
    }

    @Test
    void testAddChatBot() {
        try {
            Method method = clazz.getDeclaredMethod("addChatBot", int.class);
            method.setAccessible(true);
            Assertions.assertAll(
                () -> {
                    Assertions.assertTrue(method.getReturnType() == boolean.class);
                },
                () -> {
                    Assertions.assertTrue(isPublic(method));
                },
                () -> {
                    Object platformInstance = clazz.getDeclaredConstructor().newInstance(); 
                    System.out.println(platformInstance.getClass().getSimpleName());
                    
                    boolean added = false;
                    try {
                        added = (Boolean)method.invoke(platformInstance, 1);  
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Assertions.assertTrue(added);
                    System.out.println(added);
                    System.out.println("1");
                    try {
                        Assertions.assertTrue(added);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    
                    
                },
                () -> {
                    System.out.println("1");
                    try {
                        Class<?> chatBotClazz = urlClassLoader.loadClass("ChatBot");
                        Object mock = mock(chatBotClazz);
                        Object platformInstance = clazz.getDeclaredConstructor().newInstance(); 
                    for(int i = 0; i < 10; i++)
                        method.invoke(platformInstance, 1);
                    boolean added = (Boolean)method.invoke(platformInstance, 1);
                    System.out.println(added);
                    Assertions.assertFalse(added);
                    
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInteractWithBot(){
        try {
            Method method = clazz.getDeclaredMethod("interactWithBot", int.class, String.class);
            Assertions.assertAll(
                () -> {
                    Assertions.assertTrue(method.getReturnType() == String.class);
                },
                () -> {
                    Assertions.assertTrue(isPublic(method));
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    method.setAccessible(true);
                    String output = (String)method.invoke(obj, 1, "1");
                    Assertions.assertTrue(testResponse(output));
                    
                }
            );

            signal(6, "testInteractWithBot");
        } catch (AssertionError e) {
            signal(0, "testInteractWithBot");
        }   
        catch(Exception e){
            signal(0, "testInteractWithBot");
        }
    }

    // @Test
    // void testAddChatBotWhenLimitReached() {
    //     boolean added = platform.addChatBot(123);
    //     Assertions.assertFalse(added, "ChatBot should not be added when the limit is reached");
    // }

    // @Test
    // void testGetBots() {
    //     platform.addChatBot(123);
    //     platform.addChatBot(456);
    //     ArrayList<ChatBot> bots = platform.getBots();
    //     Assertions.assertEquals(2, bots.size(), "There should be 2 bots in the platform");
    // }

    // @Test
    // void testGetChatBotList() {
    //     platform.addChatBot(123);
    //     platform.addChatBot(456);
        
    //     String expectedOutput = "------------------\nYour ChatBots\n";
    //     expectedOutput += "Bot Number: 0 " + platform.getBots().get(0).toString() + "\n";
    //     expectedOutput += "Bot Number: 1 " + platform.getBots().get(1).toString() + "\n";
    //     expectedOutput += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\n";
    //     expectedOutput += "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n";  
    //     expectedOutput += "------------------";
        
    //     String actualOutput = platform.getChatBotList();
    //     Assertions.assertEquals(expectedOutput, actualOutput, "The chat bot list should be formatted correctly.");
    // }

    // @Test
    // void testInteractWithBot() {
    //     platform.addChatBot(123);
    //     String response = platform.interactWithBot(0, "Hello");
    //     Assertions.assertNotNull(response, "Response from bot should not be null");
    // }

    // @Test
    // void testInteractWithBotInvalidBotNumber() {
    //     platform.addChatBot(123);
    //     String response = platform.interactWithBot(999, "Hello");
    //     Assertions.assertEquals("Incorrect Bot Number (999) Selected. Try again", response, "Response should indicate an incorrect bot number");
    // }

    // @Test
    // void testLimitReached() {
    //     Assertions.assertTrue(ChatBot.limitReached(), "The limit should be correctly checked");
    // }
}
