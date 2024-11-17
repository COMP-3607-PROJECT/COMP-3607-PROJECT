package com.example.validoutput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidInteractions implements ValidOutputStrategy{
    public boolean isValidOutput(String output) {
        output = output.replaceAll("\\s+", "");
    
        String modelResponseRegex = "\\(Message#(\\d+)\\)Responsefrom(LLaMa|Mistral7B|Bard|Claude|Solar|ChatGPT-3.5)>>generatedTextHere";
        String botErrorRegex = "IncorrectBotNumber\\(\\d+\\)Selected\\.Tryagain";
        String dailyLimitRegex = "DailyLimitReached.Wait24hourstoresumechatbotusage";
    
        Pattern modelPattern = Pattern.compile(modelResponseRegex);
        Pattern botErrorPattern = Pattern.compile(botErrorRegex);
        Pattern dailyLimitPattern = Pattern.compile(dailyLimitRegex);
    
        int validMessageCount = 0;
        int botErrorCount = 0;
        int dailyLimitCount = 0;
        int totalMessages = 0;
    
        int currentIndex = 0;
    
        while (currentIndex < output.length()) {
            String substring = output.substring(currentIndex);
    
            Matcher modelMatcher = modelPattern.matcher(substring);
            Matcher botErrorMatcher = botErrorPattern.matcher(substring);
            Matcher dailyLimitMatcher = dailyLimitPattern.matcher(substring);
    
            int modelStart = modelMatcher.find() ? modelMatcher.start() : Integer.MAX_VALUE;
            int botErrorStart = botErrorMatcher.find() ? botErrorMatcher.start() : Integer.MAX_VALUE;
            int dailyLimitStart = dailyLimitMatcher.find() ? dailyLimitMatcher.start() : Integer.MAX_VALUE;
    
            int earliestMatchIndex = Math.min(modelStart, Math.min(botErrorStart, dailyLimitStart));
    
            // If no pattern is no matched
            if (earliestMatchIndex == Integer.MAX_VALUE) {
                break;
            }
    
            boolean matched = false;
    
            if (earliestMatchIndex == modelStart) {
                validMessageCount++;
                currentIndex += modelMatcher.end();
                matched = true;
            } else if (earliestMatchIndex == botErrorStart) {
                botErrorCount++;
                currentIndex += botErrorMatcher.end();
                matched = true;
            } else if (earliestMatchIndex == dailyLimitStart) {
                dailyLimitCount++;
                currentIndex += dailyLimitMatcher.end();
                matched = true;
            }
    
            if (!matched) {
                currentIndex++;
            }
    
            totalMessages++;
        }
    
        System.out.println("Valid Messages: " + validMessageCount);
        System.out.println("Bot Errors: " + botErrorCount);
        System.out.println("Daily Limits: " + dailyLimitCount);
    
        return validMessageCount <= 10 && dailyLimitCount <= 5 && totalMessages <= 15;
    }
    
    
}
