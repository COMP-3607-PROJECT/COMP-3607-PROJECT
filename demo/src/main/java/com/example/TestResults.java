package com.example;

import java.util.*;

public class TestResults implements TestResultObserver {
    private int marks;
    private List<String> feedback;

    public TestResults(int marks){
        this.marks = marks;
        feedback = new ArrayList<String>();
    }

    public void update(int marks, String feedback){
        this.marks -= marks;
        this.feedback.add(feedback);
    }

    public int getMarks(){
        return marks;
    }

    public List<String> getFeedback(){
        return feedback;
    }
}
