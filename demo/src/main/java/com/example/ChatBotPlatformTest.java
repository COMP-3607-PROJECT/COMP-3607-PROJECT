package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ChatBotPlatformTest {
    
    static boolean testBots(){
        Class<?> clazz = ChatBotPlatform.class;

        try {
            Field botsField = clazz.getDeclaredField("bots");
            int modifiers = botsField.getModifiers();
            if(botsField.getType() == java.util.ArrayList.class && Modifier.isPrivate(modifiers))
                System.out.println("Wow");
            return true;

        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    static boolean testChatBotPlatformConstructor() throws Exception{
        try {
            ChatBotPlatform cb = new ChatBotPlatform();
            Field botsField = ChatBotPlatform.class.getDeclaredField("bots");
            botsField.setAccessible(true);
            Object bots = botsField.get(cb);
            if(bots instanceof ArrayList && ((ArrayList<?>) bots).isEmpty())
            System.out.println("2");
        } catch (NoSuchFieldException e) {
            System.out.println("4");
        }
        return true;
    }

    static boolean testAddChatBot(){
            ChatBotPlatform CBP = new ChatBotPlatform();

            try {
                Method addChatBot = ChatBotPlatform.class.getDeclaredMethod("addChatBot", int.class);
                boolean returntype = addChatBot.getReturnType() == boolean.class;
                
            } catch (NoSuchMethodException e) {
            }
            return true;
    }

}
