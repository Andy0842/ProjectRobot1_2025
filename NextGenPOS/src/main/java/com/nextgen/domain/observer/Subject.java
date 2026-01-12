package com.nextgen.domain.observer;

public interface Subject {
    // Register observers (add subscribers)
    void attach(Observer observer);

    // Remove observer (cancel subscriber)
    void detach(Observer observer);

    // Notify all observers (called when state changes)
    void notifyObservers();
}