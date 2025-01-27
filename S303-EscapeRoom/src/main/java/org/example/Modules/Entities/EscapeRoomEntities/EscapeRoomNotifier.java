package org.example.Modules.Entities.EscapeRoomEntities;

import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.observers.Observer;
import org.example.observers.Subject;

import java.util.ArrayList;
import java.util.List;

public class EscapeRoomNotifier implements Subject {
    private final Repository repository = new RepositoryImpl();
    private final List<Observer> observers;

    public EscapeRoomNotifier() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }


    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }


}
