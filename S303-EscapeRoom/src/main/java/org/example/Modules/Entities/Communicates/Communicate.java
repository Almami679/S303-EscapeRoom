package org.example.Modules.Entities.Communicates;

import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;

import java.sql.Timestamp;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class Communicate extends Entity {

    private int id;
    private PlayerTEST player;
    private CommunicateType type;
    private Timestamp created_at;

    public Communicate(PlayerTEST player) {
        this.player = player;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp created_at) {
        this.player = getPlayerById(playerId);
        this.id = id;
        this.created_at = created_at;
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
