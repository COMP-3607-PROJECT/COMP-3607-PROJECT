package com.example;

import java.util.ArrayList;
import java.util.List;

public class TestResults implements TestResultObserver {
    private ArrayList<Integer> marks;
    List<String> feedback;
    List<String> testNames;

    public TestResults(){
        this.marks = new ArrayList<Integer>();
        feedback = new ArrayList<String>();
        testNames = new ArrayList<String>();
    }

    public void update(int marks, String feedback, String testName){
        this.marks.add(marks);
        this.feedback.add(feedback);
        testNames.add(testName);
    }

    public ArrayList<Integer> getMarks(){
        return marks;
    }

    public int getTotalMarks(){
        int sum = 0;
        for(int mark: marks){
            sum+=mark;
        }
        return sum;
    }

    public List<String> getFeedback(){
        return feedback;
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < marks.size(); i++){
            
            output += testNames.get(i) + "   " + feedback.get(i) + "   " + marks.get(i) + "\n";
        }
        return output;
    }
}


   