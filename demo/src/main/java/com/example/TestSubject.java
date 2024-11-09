package com.example;

public interface TestSubject {
    void signal();
    void attach(TestResultObserver t);
    void detach(TestResultObserver t);
}
