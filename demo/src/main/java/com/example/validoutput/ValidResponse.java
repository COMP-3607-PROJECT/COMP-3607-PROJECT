package com.example.validoutput;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidResponse implements ValidOutputStrategy {
    public boolean isValidOutput(String output){
        List<String> allowedModels = Arrays.asList("LLaMa", "Mistral7B", "Bard", "Claude", "Solar", "ChatGPT-3.5");
        output = output.replaceAll("\\s+", "");
    
        String modelResponseRegex = "^\\(Message#(1|2|3|4|5|6|7|8|9|10)\\)Responsefrom(LLaMa|Mistral7B|Bard|Claude|Solar|ChatGPT-3.5)>>generatedTextHere$";
        String botErrorRegex = "^IncorrectBotNumber\\((\\d+)\\)Selected\\.Tryagain$";
    
        Pattern modelPattern = Pattern.compile(modelResponseRegex);
        Matcher modelMatcher = modelPattern.matcher(output);
        if (modelMatcher.matches()) {
            String model = modelMatcher.group(2);
            return allowedModels.contains(model);
        }
    
        Pattern botErrorPattern = Pattern.compile(botErrorRegex);
        Matcher botErrorMatcher = botErrorPattern.matcher(output);
        if (botErrorMatcher.matches()) {
            String number = botErrorMatcher.group(1);
            try {
                int botNumber = Integer.parseInt(number);
                return botNumber > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        return false;
    }
}
