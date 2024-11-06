//816035438
package com.example;

public class ChatBot{
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    public ChatBot(){
        setChatBotName(ChatBotGenerator.generateChatBotLLM(6));
    }
    
    public ChatBot(int LLMCode){
        ChatBotGenerator Generator = new ChatBotGenerator();
        setChatBotName(Generator.generateChatBotLLM(LLMCode));
    }
    
    String getChatBotName(){
        return chatBotName;
    }
    
    int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    
    static int getMessageLimit(){
        return messageLimit;
    }
    
    static int getTotalNumResponsesGenerated(){
        return messageNumber;
    }
    
    static int getTotalNumMessagesRemaining(){
        return getMessageLimit() - getTotalNumResponsesGenerated();
    }
    
    void setChatBotName(String Name){
        chatBotName = Name;
    }
    
    static boolean limitReached(){
        if(getTotalNumMessagesRemaining() == 0)
            return true;
        else 
            return false;
    }
    
    private String generateResponse(){
        numResponsesGenerated += 1;
        messageNumber += 1;
        return "(Message# " + getTotalNumResponsesGenerated() + ") Response from " + getChatBotName() + "\t >>generatedTextHere";
    }
    
    String prompt(String requestMessage){
        if(limitReached())
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        return generateResponse();
    }
    
    
    public String toString(){
        return "ChatBot Name: "+ getChatBotName() + "  Number Messages Used: " + getNumResponsesGenerated();
    }
    
   

    
    
}