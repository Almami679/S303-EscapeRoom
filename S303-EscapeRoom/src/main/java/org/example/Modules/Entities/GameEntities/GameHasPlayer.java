package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;

import java.util.ArrayList;

public class GameHasPlayer extends Entity {
    private int idPlayer;
    private int idGame;

    public GameHasPlayer(int idPlayer, int idGame) {
        this.idPlayer = idPlayer;
        this.idGame = idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = this.idGame + "";
        values.add(value);
        value = this.idPlayer + "";
        values.add(value);
        return values;
    }
}
