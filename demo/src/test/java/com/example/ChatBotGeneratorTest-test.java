package com.example.tests;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatBotGeneratorTest {

    private final URLClassLoader urlClassLoader;
    private final Class<?> clazz;

    public ChatBotGeneratorTest(URL classesURL) throws ClassNotFoundException {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});
        clazz = urlClassLoader.loadClass("ChatBotGenerator");
    }

    @Test
    public void testGenerateChatBotLLM() {
        try {
            Method method = clazz.getDeclaredMethod("generateChatBotLLM", int.class);
            method.setAccessible(true);
            Object generator = clazz.getDeclaredConstructor().newInstance();

            Assertions.assertAll(
                () -> Assertions.assertEquals("LLaMa", method.invoke(generator, 1)),
                () -> Assertions.assertEquals("Mistral7B", method.invoke(generator, 2)),
                () -> Assertions.assertEquals("Bard", method.invoke(generator, 3)),
                () -> Assertions.assertEquals("Claude", method.invoke(generator, 4)),
                () -> Assertions.assertEquals("Solar", method.invoke(generator, 5)),
                () -> Assertions.assertEquals("ChatGPT-3.5", method.invoke(generator, 6)),
                () -> Assertions.assertEquals("ChatGPT-3.5", method.invoke(generator, 0)),
                () -> Assertions.assertTrue(Modifier.isStatic(method.getModifiers())),
                () -> Assertions.assertEquals(String.class, method.getReturnType())
            );

            TestRunner.signal(7, Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            TestRunner.signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
            Assertions.fail("Test failed: " + e.getMessage());
        }
    }
}
