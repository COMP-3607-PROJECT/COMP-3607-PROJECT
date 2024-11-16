package com.example.validoutput;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidBotsList implements ValidOutputStrategy {
    public boolean isValidOutput(String output){
        if(output == null){
            return false;
        }
        output = output.replaceAll("\\s+", "");
        List<String> expectedBots = Arrays.asList("ChatGPT-3.5", "LLaMa", "Mistral7B", "Bard", "Claude", "Solar");
        Set<String> foundBots = new HashSet<>();
        String botPattern = "BotNumber:\\d+ChatBotName:([\\w\\-.]+)NumberMessagesUsed:\\d+";
        Pattern pattern = Pattern.compile(botPattern);
        Matcher matcher = pattern.matcher(output);
        while (matcher.find()) {
            String botName = matcher.group(1);
            foundBots.add(botName);
        }
        return foundBots.containsAll(expectedBots);
    }
}
