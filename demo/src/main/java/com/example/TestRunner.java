package com.example;
import java.util.List;
public class TestRunner implements TestSubject {
    private List<TestResultObserver> observers;

    public void attach(TestResultObserver t){
        observers.add(t);

    }

    public void detach(TestResultObserver t){
        observers.remove(t);
    }

    public void signal(int marks, String feedback){

    }
}
