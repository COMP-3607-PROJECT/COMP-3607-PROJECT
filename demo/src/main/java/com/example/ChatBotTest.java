package com.example;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ChatBotTest {
    
    boolean testChatBotName(){
        Class<?> clazz = ChatBot.class;
        try {
            Field nameField = clazz.getDeclaredField("chatBotName");
            int modifiers = nameField.getModifiers();

            if (Modifier.isPrivate(modifiers)) { System.out.println("Field 'name' is private."); } else { System.out.println("Field 'name' is not private."); }
            return true;
        } catch (NoSuchFieldException e) {
            System.out.println("Dont exist");
            return false;
        }
    }

    boolean testNumResponsesGenerated(){
        Class<?> clazz = ChatBot.class;
        try {
            Field responsesGeneratedField = clazz.getDeclaredField("numResponsesGenerated");
            int modifiers = responsesGeneratedField.getModifiers();

            if(Modifier.isPrivate(modifiers)){
                System.out.println("Is Private");
            }
            return true;
        } catch (NoSuchFieldException e) {
            System.out.println("Fail");
            return false;
        }
    }

    boolean testMessageLimit(){
        Class<?> clazz = ChatBot.class;
        try {
            Field messageLimitField = clazz.getDeclaredField("messageLimit");
            int modifiers = messageLimitField.getModifiers();
            if(Modifier.isPrivate(modifiers))
                System.out.println("Is Private");
            if(Modifier.isStatic(modifiers))
            System.out.println("Is Static");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
