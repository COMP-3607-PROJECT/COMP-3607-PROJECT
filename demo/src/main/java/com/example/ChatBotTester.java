package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ChatBotTester {

    public static void main(String[] args) {
        System.out.println("Testing Fields:");
        testChatBotNameField();
        testNumResponsesGeneratedField();
        testMessageLimitField();
        testMessageNumberField();

        System.out.println("\nTesting Methods:");
        testConstructor();
        testGetChatBotNameMethod();
        testGetNumResponsesGeneratedMethod();
        testGetMessageLimitMethod();
        testGetTotalNumResponsesGeneratedMethod();
        testLimitReachedMethod();
        testPromptMethod();
    }

    // Field tests
    static void testChatBotNameField() {
        try {
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            assert field.getType() == String.class : "chatBotName should be of type String";
            assert Modifier.isPrivate(field.getModifiers()) : "chatBotName should be private";
            System.out.println("chatBotName field - Type: String, Access: Private, Pass");
        } catch (NoSuchFieldException e) {
            System.out.println("Field chatBotName does not exist.");
        }
    }

    static void testNumResponsesGeneratedField() {
        try {
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            assert field.getType() == int.class : "numResponsesGenerated should be of type int";
            assert Modifier.isPrivate(field.getModifiers()) : "numResponsesGenerated should be private";
            System.out.println("numResponsesGenerated field - Type: int, Access: Private, Pass");
        } catch (NoSuchFieldException e) {
            System.out.println("Field numResponsesGenerated does not exist.");
        }
    }

    static void testMessageLimitField() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assert field.getType() == int.class : "messageLimit should be of type int";
            assert Modifier.isStatic(field.getModifiers()) : "messageLimit should be static";
            System.out.println("messageLimit field - Type: int, Access: Static, Pass");
        } catch (NoSuchFieldException e) {
            System.out.println("Field messageLimit does not exist.");
        }
    }

    static void testMessageNumberField() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assert field.getType() == int.class : "messageNumber should be of type int";
            assert Modifier.isStatic(field.getModifiers()) : "messageNumber should be static";
            System.out.println("messageNumber field - Type: int, Access: Static, Pass");
        } catch (NoSuchFieldException e) {
            System.out.println("Field messageNumber does not exist.");
        }
    }

    // Method tests
    static void testConstructor() {
        ChatBot cb = new ChatBot();
        assert cb.getChatBotName() != null : "Constructor should initialize chatBotName";
        System.out.println("Constructor test passed - chatBotName initialized");
    }

    static void testGetChatBotNameMethod() {
        try {
            ChatBot cb = new ChatBot();
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            field.setAccessible(true);
            String expectedValue = (String) field.get(cb);
            Method method = ChatBot.class.getDeclaredMethod("getChatBotName");
            String actualValue = (String) method.invoke(cb);
            assert expectedValue.equals(actualValue) : "getChatBotName should return the value of chatBotName";
            System.out.println("getChatBotName method - Correctly returns field value, Pass");
        } catch (Exception e) {
            System.out.println("Error in getChatBotName test: " + e.getMessage());
        }
    }

    static void testGetNumResponsesGeneratedMethod() {
        try {
            ChatBot cb = new ChatBot();
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            field.setAccessible(true);
            int expectedValue = (int) field.get(cb);
            Method method = ChatBot.class.getDeclaredMethod("getNumResponsesGenerated");
            int actualValue = (int) method.invoke(cb);
            assert expectedValue == actualValue : "getNumResponsesGenerated should return the value of numResponsesGenerated";
            System.out.println("getNumResponsesGenerated method - Correctly returns field value, Pass");
        } catch (Exception e) {
            System.out.println("Error in getNumResponsesGenerated test: " + e.getMessage());
        }
    }

    static void testGetMessageLimitMethod() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            field.setAccessible(true);
            int expectedValue = (int) field.get(null);  // Static fields use null as the instance
            Method method = ChatBot.class.getDeclaredMethod("getMessageLimit");
            int actualValue = (int) method.invoke(null);
            assert expectedValue == actualValue : "getMessageLimit should return the value of messageLimit";
            System.out.println("getMessageLimit method - Correctly returns field value, Pass");
        } catch (Exception e) {
            System.out.println("Error in getMessageLimit test: " + e.getMessage());
        }
    }

    static void testGetTotalNumResponsesGeneratedMethod() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            field.setAccessible(true);
            int expectedValue = (int) field.get(null);  // Static fields use null as the instance
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumResponsesGenerated");
            int actualValue = (int) method.invoke(null);
            assert expectedValue == actualValue : "getTotalNumResponsesGenerated should return the value of messageNumber";
            System.out.println("getTotalNumResponsesGenerated method - Correctly returns field value, Pass");
        } catch (Exception e) {
            System.out.println("Error in getTotalNumResponsesGenerated test: " + e.getMessage());
        }
    }

    static void testLimitReachedMethod() {
        try {
            Method method = ChatBot.class.getDeclaredMethod("limitReached");
            boolean actualValue = (boolean) method.invoke(null);
            System.out.println("limitReached method - Correctly checks limit, Pass: " + actualValue);
        } catch (Exception e) {
            System.out.println("Error in limitReached test: " + e.getMessage());
        }
    }

    static void testPromptMethod() {
        try {
            ChatBot cb = new ChatBot();
            String response = cb.prompt("Hello");
            assert response != null : "prompt should return a non-null response";
            System.out.println("prompt method test passed - Returned response: " + response);
        } catch (Exception e) {
            System.out.println("Error testing prompt method: " + e.getMessage());
        }
    }
}
