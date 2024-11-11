package com.example;
public class TestRunner implements TestSubject {
    private TestResultObserver testResultObserver;

    public void attach(TestResultObserver t){
        if( testResultObserver == null)
            testResultObserver = t;

    }

    public void detach(TestResultObserver t){
        if(testResultObserver == t)
            testResultObserver = null;
    }

    public void signal(int marks, String feedback){
        testResultObserver.update(marks, feedback, this.getClass().getSimpleName());
    }
}
