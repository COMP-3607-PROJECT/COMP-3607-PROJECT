package com.example;

import java.lang.reflect.Field;

public class ChatBotPlatformTest {
    
    static boolean testBots(){
        Class<?> clazz = ChatBotPlatform.class;

        try {
            Field botsField = clazz.getDeclaredField("bots");
            int modifiers = botsField.getModifiers();
            if(botsField.getType() == java.util.ArrayList.class)
                System.out.println("Wow");
            return true;

        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
