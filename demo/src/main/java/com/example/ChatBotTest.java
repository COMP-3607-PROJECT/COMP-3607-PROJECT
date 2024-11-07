package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import junit.framework.TestCase;
public class ChatBotTest extends TestCase {

    private URL classesURL;

    public ChatBotTest(URL classesURL){
        this.classesURL = classesURL;
    }
    
    boolean testChatBotName(){
        
        try(URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{classesURL})) {
            Class<?> clazz = urlClassLoader.loadClass("com.example.ChatBot");
            Field nameField = clazz.getDeclaredField("chatBotName");
            int modifiers = nameField.getModifiers();
            assertTrue("Yo its me bubby",Modifier.isPrivate(modifiers));
            return true;
        } catch (NoSuchFieldException e) {
            System.out.println("Dont exist");
            return false;
        }
        catch (AssertionError e){
            
        }
        catch(Exception e){

        }
        return false;
    }

    boolean testNumResponsesGenerated(){
        
        try(URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{classesURL})) {
            Class<?> clazz = ChatBot.class;
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
        catch(Exception e){
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

    boolean testMessageNumber() throws Exception{
        Class <?> clazz = ChatBot.class;
        try {
            Field messageNumberField = clazz.getDeclaredField("messageNumber");
            int modifiers = messageNumberField.getModifiers();
            messageNumberField.setAccessible(true);
            boolean correctType = messageNumberField.getType() == int.class;
            if(Modifier.isStatic(modifiers) && Modifier.isPrivate(modifiers) && correctType)
                System.out.println("good " + (int) messageNumberField.get(null));
        } catch (NoSuchFieldException e) {
        }
        return true;
    }

    boolean testDefaultConstructor() throws Exception{
        ChatBot cb = new ChatBot();
        Field chatBotNameField = ChatBot.class.getDeclaredField("chatBotName");

        chatBotNameField.setAccessible(true);

        return chatBotNameField.get(cb) == "ChatGPT-3.5";




    }

    boolean testOverridedConstructor() throws Exception{
        HashMap<Integer, String> names = new HashMap<>();
        names.put(1, "LLaMa");
        names.put(2, "Mistral7B");
        names.put(3, "Bard");
        names.put(4, "Claude");
        names.put(5, "Solar");
        names.put(0, "ChatGPT-3.5");
        Field chatBotNameField = ChatBot.class.getDeclaredField("chatBotName");
        chatBotNameField.setAccessible(true);
        boolean Correct = true;
        for(int i = 0; i < 7; i++){
            ChatBot cb = new ChatBot(i);
            if(i>=1 && i <=5){
                if(chatBotNameField.get(cb) != names.get(i))
                    Correct = false;
            }
            else{
                if(chatBotNameField.get(cb) != names.get(0))
                    Correct = false;
            }
        }
        return Correct;
    }


    boolean testGetChatBotName(){
        Class<?> clazz = ChatBot.class;

        try {
            ChatBot cb = new ChatBot();
            Field chatBotNameField = clazz.getDeclaredField("chatBotName");
            chatBotNameField.setAccessible(true);
            Method getChatBotNameMethod = clazz.getDeclaredMethod("getChatBotName");
            getChatBotNameMethod.setAccessible(true);
            return ((String)getChatBotNameMethod.invoke(cb)).equals((String) chatBotNameField.get(cb));
        } catch (NoSuchFieldException e) {
            return false;
        }
          catch (NoSuchMethodException e){
                return false;
          }
          catch(Exception e){
            return false;
          }
    }

    boolean testGetNumResponsesGenerated(){
        Class<?> clazz = ChatBot.class;
        try {
            ChatBot cb = new ChatBot();
            Field chatBotNameField = clazz.getDeclaredField("numResponsesGenerated");
            chatBotNameField.setAccessible(true);
            Method getChatBotNameMethod = clazz.getDeclaredMethod("getNumResponsesGenerated");
            getChatBotNameMethod.setAccessible(true);
            return ((int)chatBotNameField.get(cb) == (int)getChatBotNameMethod.invoke(cb));
        } catch (NoSuchFieldException e) {
            return false;
        }
          catch (NoSuchMethodException e){
                return false;
          }
          catch(Exception e){
            return false;
          }
    }
}
