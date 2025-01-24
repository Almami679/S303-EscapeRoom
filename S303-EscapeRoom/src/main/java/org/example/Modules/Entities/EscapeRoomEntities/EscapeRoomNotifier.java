package org.example.Modules.Entities.EscapeRoomEntities;

import org.example.observers.Observer;
import org.example.observers.Subject;

import java.util.ArrayList;
import java.util.List;

public class EscapeRoomNotifier implements Subject {
    private final List<Observer> observers;

    public EscapeRoomNotifier() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return observers;  // Devuelve la lista de observadores
    }
}
