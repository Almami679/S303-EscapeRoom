package org.example.Modules.Communicates;

import org.example.Modules.CLASESTESTS.PlayerTEST;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class Communicate {

    private int id;
    private PlayerTEST player;
    private CommunicateType type;

    public Communicate(PlayerTEST player) {
        this.player = player;
    }

    public Communicate(int id, int playerId) {
        this.player = getPlayerById(playerId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Communicate setId(int id) {
        this.id = id;
        return this;
    }

    public PlayerTEST getPlayer() {
        return player;
    }

    public Communicate setPlayer(PlayerTEST player) {
        this.player = player;
        return this;
    }
}
