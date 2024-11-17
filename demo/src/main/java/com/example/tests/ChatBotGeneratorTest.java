package com.example.tests;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.TestRunner;

public class ChatBotGeneratorTest  extends TestRunner {
    private final URLClassLoader urlClassLoader;
    private final Class<?> clazz;

    public ChatBotGeneratorTest(URL classesURL) throws ClassNotFoundException {
        urlClassLoader = new URLClassLoader(new URL[]{classesURL});
        clazz = urlClassLoader.loadClass("ChatBotGenerator");    
    }

    @Test
    public void testGenerateChatBotLLM(){
        try {
            Method method = clazz.getDeclaredMethod("generateChatBotLLM", int.class);
            method.setAccessible(true);
            Object generator = clazz.getDeclaredConstructor().newInstance();
            try{
        Assertions.assertAll(
            () -> {                
                String name = (String)method.invoke(null, 1);
                Assertions.assertTrue(name.equals("LLaMa"));},
            () -> {
                String name = (String)method.invoke(generator, 2);
                Assertions.assertTrue(name.equals("Mistral7B"));
                System.out.println("1");
            },
            () -> {
                String name = (String)method.invoke(generator, 3);
                Assertions.assertTrue(name.equals("Bard"));
            },
            () -> {
                String name = (String)method.invoke(generator,4);
                Assertions.assertTrue(name.equals("Claude"));
            },
            () -> {
                String name = (String)method.invoke(generator, 5);
                Assertions.assertTrue(name.equals("Solar"));
            },
            () -> {
                String name = (String)method.invoke(generator, 6);
                Assertions.assertTrue(name.equals("ChatGPT-3.5"));
            },
            () -> {
                String name = (String)method.invoke(generator, 0);
                Assertions.assertTrue(name.equals("ChatGPT-3.5"));
            },
            () -> Assertions.assertTrue(Modifier.isStatic(method.getModifiers())),
            () -> Assertions.assertTrue(method.getReturnType() == String.class)
        );
        signal(7, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
        catch(AssertionError e){
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        } catch (Exception e) {
            signal(0, Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        
      
           
    }
}
