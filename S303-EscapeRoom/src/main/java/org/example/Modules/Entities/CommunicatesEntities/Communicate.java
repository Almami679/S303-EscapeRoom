package org.example.Modules.Entities.CommunicatesEntities;

import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Communicate extends Entity {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();
    private Player player;
    private CommunicateType type;
    private Timestamp createdAt;

    public Communicate(Player player) {
        super();
        this.player = player;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp createdAt) throws SQLException {
        super(id, 0);
        this.player = (Player) repositoryImpl.getById(playerId, EntityAttributes.player);
        this.createdAt = createdAt;
    }

    public Player getPlayer() {
        return player;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Communicate setPlayer(Player player) {
        this.player = player;
        return this;
    }
}
