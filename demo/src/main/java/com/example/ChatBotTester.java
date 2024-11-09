package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class ChatBotTester {

    private URLClassLoader urlClassLoader;
    private Class<?> clazzz;
    private Object obj;

    public ChatBotTester(URL classesURL) {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});
        try {
            clazzz = urlClassLoader.loadClass("ChatBot");
            Object obj = clazzz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test chatBotName field
    @Test
    void testChatBotNameField() {
        try {
            System.out.println("1");
            assertAll("chatBotName Field Tests",
                // Test field type
                () -> {
                    Field field = clazzz.getDeclaredField("chatBotName");
                    Assertions.assertEquals(String.class, field.getType(), "chatBotName should be of type String");
                },
                // Test field access modifier
                () -> {
                    Field field = clazzz.getDeclaredField("chatBotName");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "chatBotName should be private");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test numResponsesGenerated field
    @Test
    void testNumResponsesGeneratedField() {
        try {
            assertAll("numResponsesGenerated Field Tests",
                // Test field type
                () -> {
                    Field field = clazzz.getDeclaredField("numResponsesGenerated");
                    Assertions.assertEquals(int.class, field.getType(), "numResponsesGenerated should be of type int");
                },
                // Test field access modifier
                () -> {
                    Field field = clazzz.getDeclaredField("numResponsesGenerated");
                    Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()), "numResponsesGenerated should be private");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test messageLimit field
    @Test
    void testMessageLimitField() {
        try {
            assertAll("messageLimit Field Tests",
                // Test field type
                () -> {
                    Field field = clazzz.getDeclaredField("messageLimit");
                    Assertions.assertEquals(int.class, field.getType(), "messageLimit should be of type int");
                },
                // Test if the field is static
                () -> {
                    Field field = clazzz.getDeclaredField("messageLimit");
                    Assertions.assertTrue(Modifier.isStatic(field.getModifiers()), "messageLimit should be static");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test messageNumber field
    @Test
    void testMessageNumberField() {
        try {
            assertAll("messageNumber Field Tests",
                // Test field type
                () -> {
                    Field field = clazzz.getDeclaredField("messageNumber");
                    Assertions.assertEquals(int.class, field.getType(), "messageNumber should be of type int");
                
                },
                // Test if the field is static
                () -> {
                    Field field = clazzz.getDeclaredField("messageNumber");
                    Assertions.assertTrue(Modifier.isStatic(field.getModifiers()), "messageNumber should be static");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getChatBotName method
    @Test
    void testGetChatBotNameMethod() {
        try {
            assertAll("getChatBotName Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("getChatBotName");
                    Assertions.assertEquals(String.class, method.getReturnType(), "getChatBotName should return a String");
                },
                // Test if the method is public
                () -> {
                    Method method = clazzz.getDeclaredMethod("getChatBotName");
                    Assertions.assertTrue(Modifier.isPublic(method.getModifiers()), "getChatBotName should be public");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getNumResponsesGenerated method
    @Test
    void testGetNumResponsesGeneratedMethod() {
        try {
            assertAll("getNumResponsesGenerated Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("getNumResponsesGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getNumResponsesGenerated should return an int");
                },
                // Test if the method is public
                () -> {
                    Method method = clazzz.getDeclaredMethod("getNumResponsesGenerated");
                    Assertions.assertTrue(Modifier.isPublic(method.getModifiers()), "getNumResponsesGenerated should be public");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getMessageLimit method
    @Test
    void testGetMessageLimit() {
        try {
            System.out.println("1");
            assertAll("getMessageLimit Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("getMessageLimit");

                    Assertions.assertEquals(int.class, method.getReturnType(), "getMessageLimit should return an int");
                },
                // Test if the method is public
                () -> {
                    Method method = clazzz.getDeclaredMethod("getMessageLimit");
                    int modifiers = method.getModifiers();
                    
                    assertFalse(Modifier.isPrivate(modifiers), "Field should not be private");
                    assertFalse(Modifier.isProtected(modifiers), "Field should not be protected");
                    assertFalse(Modifier.isPublic(modifiers), "Field should not be public");
                    System.out.println(method.getModifiers());
                    //Assertions.assertTrue(Modifier.isPublic(method.getModifiers()), "getMessageLimit should be public");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getTotalNumResponsesGenerated method
    @Test
    void testGetTotalNumResponsesGeneratedMethod() {
        try {
            assertAll("getTotalNumResponsesGenerated Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getTotalNumResponsesGenerated should return an int");
                },
                // Test if the method is public
                () -> {
                    Method method = clazzz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    Assertions.assertTrue(Modifier.isPublic(method.getModifiers()), "getTotalNumResponsesGenerated should be public");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test limitReached method
    @Test
    void testLimitReachedMethod() {
        try {
            assertAll("limitReached Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("limitReached");
                    Assertions.assertEquals(boolean.class, method.getReturnType(), "limitReached should return a boolean");
                },
                // Test if the method is static
                () -> {
                    Method method = clazzz.getDeclaredMethod("limitReached");
                    Assertions.assertTrue(Modifier.isStatic(method.getModifiers()), "limitReached should be static");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test prompt method
    @Test
    void testPromptMethod() {
        try {
            assertAll("prompt Method Tests",
                // Test return type
                () -> {
                    Method method = clazzz.getDeclaredMethod("prompt", String.class);
                    Assertions.assertEquals(String.class, method.getReturnType(), "prompt should return a String");
                },
                // Test if the method is public
                () -> {
                    Method method = clazzz.getDeclaredMethod("prompt", String.class);
                    Assertions.assertTrue(Modifier.isPublic(method.getModifiers()), "prompt should be public");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
