package org.example.Modules.Entities.CommunicatesEntities;

import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
import org.example.Modules.Entities.Entity;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class Communicate extends Entity {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    private PlayerTEST player;
    private CommunicateType type;
    private Timestamp created_at;

    public Communicate(PlayerTEST player) {
        super();
        this.player = player;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp created_at) throws SQLException {
        super(id, 0);
        this.player = (PlayerTEST) repositoryImpl.getById(playerId, EntityAttributes.player);
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
