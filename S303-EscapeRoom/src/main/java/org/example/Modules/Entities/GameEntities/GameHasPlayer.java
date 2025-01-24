package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;

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
}
