//816035438
package com.example;
public class ChatBotSimulation{
    public static void main(String [] args){
        System.out.println("Hello World!");
        
        ChatBotPlatform Platform = new ChatBotPlatform();
        for(int i = 1; i <= 6; i++)
            Platform.addChatBot(i);
            
        java.util.Random r = new java.util.Random();
        
        for(int i = 1; i<= 4; i++)
            Platform.addChatBot(r.nextInt(6));
            
        System.out.println(Platform.getChatBotList());
        String[] prompts = {
        "Hi",
        "Hello",
        "Hey"};
    
    
        for(int i = 1; i <= 15; i++){
            System.out.println(Platform.interactWithBot(r.nextInt((Platform.getBots()).size() + 1) , prompts[r.nextInt(prompts.length)]));
        }
        System.out.println(Platform.getChatBotList());
    }
}