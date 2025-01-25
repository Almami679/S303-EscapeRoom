package org.example.observers;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObservers(String message);
    List<Observer> getObservers();

}
