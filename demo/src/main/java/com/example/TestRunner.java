package com.example;
import com.example.validoutput.ValidOutputStrategy;
public class TestRunner implements TestSubject {
    private TestResultObserver testResultObserver;
    protected ValidOutputStrategy validOutputStrategy;

    public void attach(TestResultObserver t){
        if( testResultObserver == null)
            testResultObserver = t;

    }

    public void setStrategy(ValidOutputStrategy strategy){
        validOutputStrategy = strategy;
    }

    public void detach(TestResultObserver t){
        if(testResultObserver == t)
            testResultObserver = null;
    }

    public void signal(int marks, String feedback){
        testResultObserver.update(marks, feedback, this.getClass().getSimpleName());
    }
}
