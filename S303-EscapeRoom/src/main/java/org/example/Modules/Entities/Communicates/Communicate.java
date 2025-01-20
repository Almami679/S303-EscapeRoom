package org.example.Modules.Entities.Communicates;

import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Entities.Entity;

import java.sql.Timestamp;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class Communicate extends Entity {

    private PlayerTEST player;
    private CommunicateType type;
    private Timestamp created_at;

    public Communicate(PlayerTEST player) {
        super(0);
        this.player = player;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp created_at) {
        super(id, 0);
        this.player = getPlayerById(playerId);
        this.created_at = created_at;
    }

    public PlayerTEST getPlayer() {
        return player;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Communicate setPlayer(PlayerTEST player) {
        this.player = player;
        return this;
    }
}
