package com.example;

public interface TestSubject {
    void signal(int marks, String feedback, String testName);
    void attach(TestResultObserver t);
    void detach(TestResultObserver t);
}
