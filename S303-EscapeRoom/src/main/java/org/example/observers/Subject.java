package org.example.observers;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObservers(String message);
}
