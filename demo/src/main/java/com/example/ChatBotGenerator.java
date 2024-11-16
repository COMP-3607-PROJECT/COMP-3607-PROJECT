//816035438
package com.example;
public class ChatBotGenerator{
    static String generateChatBotLLM(int LLMCodeNumber){
        switch(LLMCodeNumber){
            case 1: return "LLaMa";
                    
            case 2: return "Mistral7B";
                    
            case 3: return "Bard";
                    
            case 4: return "Claude";
                    
            case 5: return "Solar";
                    
            default: return "ChatGPT-3.5";
        }
        
    }

}