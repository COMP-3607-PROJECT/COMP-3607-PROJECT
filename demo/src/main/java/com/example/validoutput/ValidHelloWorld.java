package com.example.validoutput;

public class ValidHelloWorld implements ValidOutputStrategy {
    public boolean isValidOutput(String output){
        return output.startsWith("Hello World!");
    }
    
}
