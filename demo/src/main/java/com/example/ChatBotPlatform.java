//816035438
package com.example;
import java.util.ArrayList;

public class ChatBotPlatform{
   private ArrayList<ChatBot> bots;
    
    public ChatBotPlatform(){
        bots = new ArrayList<ChatBot>();
    }
    
    boolean addChatBot(int LLMCode){
        if(ChatBot.limitReached())
            return false;
        else{
            ChatBot bot = new ChatBot(LLMCode);
            bots.add(bot);
            return true;
        }
        
    }
    
    ArrayList<ChatBot> getBots(){
        return bots;
    }
    
    String getChatBotList(){
        String text ="------------------\nYour ChatBots\n";
        int i = 0;
        for(ChatBot Bot : bots){
            text += "Bot Number: " + i++ + " ";
            text += Bot.toString();
            text += "\n";
        }
        text += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\n";
        text += "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n";  
        text += "------------------";
        return text;
    }
    
    String interactWithBot(int botNumber, String message){
        if(botNumber < 0 || botNumber >= getBots().size())
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        return (bots.get(botNumber)).prompt(message);
    }
    
}