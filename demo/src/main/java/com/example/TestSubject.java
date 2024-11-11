package com.example;

public interface TestSubject {
    void signal(int marks, String feedback);
    void attach(TestResultObserver t);
    void detach(TestResultObserver t);
}
