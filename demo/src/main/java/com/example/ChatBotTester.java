package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

class ChatBotTester implements TestSubject {
    private TestResultObserver testResultObserver;
    private URLClassLoader urlClassLoader;
    private Class<?> clazzz;
    private Object obj;

    public ChatBotTester(URL classesURL) {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});
        try {
            clazzz = urlClassLoader.loadClass("ChatBot");
            obj = clazzz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @Override
    public void attach(TestResultObserver t) {
        testResultObserver = t;
    }
    public void detach(TestResultObserver t){
            if(testResultObserver == t)
                testResultObserver = null;
    }
    public void signal(int marks, String feedback){
        testResultObserver.update(marks, feedback);
    }
    
    // Test chatBotName field
    @Test
    void testChatBotName() {
        try {
            assertAll("chatBotName Field Tests",
                () -> {
                    Field field = clazzz.getDeclaredField("chatBotName");
                    Assertions.assertEquals(String.class, field.getType(), "chatBotName should be of type String");
                },
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
    void testNumResponsesGenerated() {
        try {
            assertAll("numResponsesGenerated Field Tests",
                () -> {
                    Field field = clazzz.getDeclaredField("numResponsesGenerated");
                    Assertions.assertEquals(int.class, field.getType(), "numResponsesGenerated should be of type int");
                },
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
    void testMessageLimit() {
        try {
            assertAll("messageLimit Field Tests",
                () -> {
                    Field field = clazzz.getDeclaredField("messageLimit");
                    Assertions.assertEquals(int.class, field.getType(), "messageLimit should be of type int");
                },
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
    void testMessageNumber() {
        try {
            assertAll("messageNumber Field Tests",
                () -> {
                    Field field = clazzz.getDeclaredField("messageNumber");
                    Assertions.assertEquals(int.class, field.getType(), "messageNumber should be of type int");
                },
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
    void testGetChatBotName() {
        try {
            assertAll("getChatBotName Method Tests",
                () -> {
                    Method method = clazzz.getDeclaredMethod("getChatBotName");
                    Assertions.assertEquals(String.class, method.getReturnType(), "getChatBotName should return a String");
                },
                () -> {
                    Method method = clazzz.getDeclaredMethod("getChatBotName");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getChatBotName should be public or package-private"
                    );
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test getNumResponsesGenerated method
    @Test
    void testGetNumResponsesGenerated() {
        try {
            assertAll("getNumResponsesGenerated Method Tests",
                () -> {
                    Method method = clazzz.getDeclaredMethod("getNumResponsesGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getNumResponsesGenerated should return an int");
                },
                () -> {
                    Method method = clazzz.getDeclaredMethod("getNumResponsesGenerated");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getNumResponsesGenerated should be public or package-private"
                    );
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
            assertAll("getMessageLimit Method Tests",
                () -> {
                    Method method = clazzz.getDeclaredMethod("getMessageLimit");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getMessageLimit should return an int");
                },
                () -> {
                    Method method = clazzz.getDeclaredMethod("getMessageLimit");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getMessageLimit should be public or package-private"
                    );
                }
            );
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
                    Method method = clazzz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    Assertions.assertEquals(int.class, method.getReturnType(), "getTotalNumResponsesGenerated should return an int");
                },
                () -> {
                    Method method = clazzz.getDeclaredMethod("getTotalNumResponsesGenerated");
                    int modifiers = method.getModifiers();
                    Assertions.assertTrue(
                        Modifier.isPublic(modifiers) || 
                        (!Modifier.isPrivate(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers)),
                        "getTotalNumResponsesGenerated should be public or package-private"
                    );
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test limitReached method
    @Test
    void testLimitReached() {
        try {
            assertAll("limitReached Method Tests",
                () -> {
                    Method method = clazzz.getDeclaredMethod("limitReached");
                    Assertions.assertEquals(boolean.class, method.getReturnType(), "limitReached should return a boolean");
                },
                () -> {
                    Method method = clazzz.getDeclaredMethod("limitReached");
                    Assertions.assertTrue(Modifier.isStatic(method.getModifiers()), "limitReached should be static");
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
