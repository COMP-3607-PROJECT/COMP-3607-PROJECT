package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

class ChatBotTest extends TestRunner {
    private URLClassLoader urlClassLoader;
    private Class<?> clazz;

    public ChatBotTest(URL classesURL) throws ClassNotFoundException {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});        
        clazz = urlClassLoader.loadClass("ChatBot");
    }

    private boolean isPublic(Method method){
        int mod = method.getModifiers();
        return Modifier.isPublic(mod) || (!Modifier.isPrivate(mod) && !Modifier.isPublic(mod) && !Modifier.isProtected(mod)); 
        //Additionally checks if the method is package protected;
    }

    private boolean testResponse(String output) {
        // List of allowed models
        List<String> allowedModels = Arrays.asList("LLaMa", "Mistral7B", "Bard", "Claude", "Solar", "ChatGPT-3.5");

        // Trim the output string to remove all whitespaces from the beginning and end
        output = output.trim().replaceAll("\\s+", ""); // Remove all internal spaces as well

        // Regular expression to match "(Message#<number>)Response from<model>>generatedTextHere"
        String regex = "^\\(Message#(1|2|3|4|5|6|7|8|9|10)\\)Responsefrom(LLaMa|Mistral7B|Bard|Claude|Solar|ChatGPT-3.5)>>generatedTextHere$";
        
        // Compile and match against the regex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(output);
        
        if (matcher.matches()) {
            // Extract the model from the matched group
            String model = matcher.group(2);

            // Verify if the model is in the list of allowed models
            return allowedModels.contains(model);
        }
        
        return false; // No match
    }
       
    // Test chatBotName field
    @Test
    void testChatBotName() {
        try {
            assertAll("chatBotName Field Tests",
                () -> {
                    Field field = clazz.getDeclaredField("chatBotName");
                    Assertions.assertEquals(String.class, field.getType(), "chatBotName should be of type String");
                },
                () -> {
                    Field field = clazz.getDeclaredField("chatBotName");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "chatBotName should be private");
                }
            );
            signal(1, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
            e.printStackTrace();
        }
    }

    // Test numResponsesGenerated field
    @Test
    void testNumResponsesGenerated() {
        try {
            assertAll("numResponsesGenerated Field Tests",
                () -> {
                    Field field = clazz.getDeclaredField("numResponsesGenerated");
                    Assertions.assertEquals(int.class, field.getType(), "numResponsesGenerated should be of type int");
                },
                () -> {
                    Field field = clazz.getDeclaredField("numResponsesGenerated");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "numResponsesGenerated should be private");
                }
            );
            signal(1, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test messageLimit field
    @Test
    void testMessageLimit() {
        try {
            assertAll("messageLimit Field Tests",
                () -> {
                    Field field = clazz.getDeclaredField("messageLimit");
                    Assertions.assertEquals(int.class, field.getType(), "messageLimit should be of type int");
                },
                () -> {
                    Field field = clazz.getDeclaredField("messageLimit");
                    Assertions.assertTrue(Modifier.isStatic(field.getModifiers()), "messageLimit should be static");
                },
                () -> {
                    Field field = clazz.getDeclaredField("messageLimit");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "messageLimit should be private");
                }
            );
            signal(3, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test messageNumber field
    @Test
    void testMessageNumber() {
        try {
            assertAll("messageNumber Field Tests",
                () -> {
                    Field field = clazz.getDeclaredField("messageNumber");
                    Assertions.assertEquals(int.class, field.getType(), "messageNumber should be of type int");
                },
                () -> {
                    Field field = clazz.getDeclaredField("messageNumber");
                    Assertions.assertTrue(Modifier.isStatic(field.getModifiers()), "messageNumber should be static");
                }
            );
            signal(2, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test getChatBotName method
    @Test
    void testGetChatBotName() {
        try {
            assertAll("getChatBotName Method Tests",
                () -> {
                    Method method = clazz.getDeclaredMethod("getChatBotName");
                    Assertions.assertEquals(String.class, method.getReturnType(), "getChatBotName should return a String");
                },
                () -> {
                    Method method = clazz.getDeclaredMethod("getChatBotName");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getChatBotName should be public or package-private"
                    );
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    Field field = clazz.getDeclaredField("chatBotName");
                    field.setAccessible(true);
                    Method method = clazz.getDeclaredMethod("getChatBotName");
                    method.setAccessible(true);                    
                    field.set(obj, "LLaMa");
                    Assertions.assertTrue(field.get(obj).equals(method.invoke(obj)));
                }
            );
            signal(1, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test getNumResponsesGenerated method
    @Test
    void testGetNumResponsesGenerated() {
        try {
            assertAll("getNumResponsesGenerated Method Tests",
                () -> {
                    Method method = clazz.getDeclaredMethod("getNumResponsessGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getNumResponsesGenerated should return an int");
                },
                () -> {
                    Method method = clazz.getDeclaredMethod("getNumResponsesGenerated");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getNumResponsesGenerated should be public or package-private"
                    );
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    Method method = clazz.getDeclaredMethod("getNumResponsesGenerated");
                    Field field = clazz.getDeclaredField("numResponsesGenerated");
                    method.setAccessible(true);
                    field.setAccessible(true);
                    Assertions.assertTrue(field.get(obj).equals(method.invoke(obj)));
                }
            );
            signal(1, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test getMessageLimit method
    @Test
    void testGetMessageLimit() {
        try {
            assertAll("getMessageLimit Method Tests",
                () -> {
                    Method method = clazz.getDeclaredMethod("getMessageLimit");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getMessageLimit should return an int");
                },
                () -> {
                    Method method = clazz.getDeclaredMethod("getMessageLimit");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getMessageLimit should be public or package-private"
                    );
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    Method method = clazz.getDeclaredMethod("getMessageLimit");
                    Field field = clazz.getDeclaredField("messageLimit");
                    method.setAccessible(true);
                    field.setAccessible(true);
                    Assertions.assertTrue(field.get(obj).equals(method.invoke(obj)));
                }
            );
            signal(0 ,Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getTotalNumResponsesGenerated method
    @Test
    void testGetTotalNumResponsesGenerated() {
        try {
            assertAll("getTotalNumResponsesGenerated Method Tests",
                () -> {
                    Method method = clazz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getTotalNumResponsesGenerated should return an int");
                },
                () -> {
                    Method method = clazz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getTotalNumResponsesGenerated should be public or package-private"
                    );
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    Method method = clazz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    Field field = clazz.getDeclaredField("messageNumber");
                    field.setAccessible(true);
                    method.setAccessible(true);
                    field.set(obj, 1);
                    Assertions.assertTrue(field.get(obj).equals(method.invoke(obj)));

                }
                
            );
            signal(2, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    // Test limitReached method
    @Test
    void testLimitReached() {
        try {
            assertAll("limitReached Method Tests",
                () -> {
                    Method method = clazz.getDeclaredMethod("limitReached");
                    Assertions.assertEquals(boolean.class, method.getReturnType(), "limitReached should return a boolean");
                },
                () -> {
                    Method method = clazz.getDeclaredMethod("limitReached");
                    Assertions.assertTrue(Modifier.isStatic(method.getModifiers()), "limitReached should be static");
                },
                () -> {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    Method method = clazz.getDeclaredMethod("limitReached");
                    Field field = clazz.getDeclaredField("messageNumber");
                    field.setAccessible(true);
                    method.setAccessible(true);
                    field.set(null, 10);
                    Assertions.assertTrue((boolean)method.invoke(obj));
                }
            );
            signal(3, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    @Test
    void testGetTotalNumMessagesRemaining(){
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Field field = clazz.getDeclaredField("messageNumber");
            field.setAccessible(true);
            Method method = clazz.getDeclaredMethod("getTotalNumMessagesRemaining");
            method.setAccessible(true);
            Assertions.assertTrue(isPublic(method));
            
            Assertions.assertTrue((Modifier.isStatic(method.getModifiers())));
            
            Assertions.assertTrue(method.getReturnType() == int.class);
            //System.out.println((Integer)field.get(null));
            //System.out.println(method.invoke(obj));
            Assertions.assertTrue((Integer)method.invoke(null) == 10);
            //System.out.println("1");
            field.set(obj, 10);
            Assertions.assertTrue((int)method.invoke(obj) == 0);
            field.set(obj, 0);
            signal(3, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        catch(Exception e){
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    @Test
    void testGenerateResponse(){
        try {
            Method method = clazz.getDeclaredMethod("generateResponse");
            Assertions.assertAll(
                () ->{
                    Assertions.assertTrue(method.getReturnType() == String.class);
                },
                () -> {
                    Assertions.assertTrue(Modifier.isPrivate(method.getModifiers()));
                },
                () -> {
                    
                }
            );
            signal(5, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        catch (Exception e ){
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    @Test
    void testPrompt(){
        try {
            Method method = clazz.getDeclaredMethod("prompt", String.class);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            method.setAccessible(true);
            String output = (String)method.invoke(obj, "1");
            System.out.println(output);
            System.out.println(testResponse("(Message# 2)            R        esponse from ChatGPT-3.5   >>generatedTextHere"));
            Assertions.assertTrue(testResponse(output));
            System.out.println("1");
            signal(4, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (AssertionError e) {
            e.printStackTrace();
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        catch (Exception e){
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }
}
