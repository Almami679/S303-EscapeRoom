package org.example.Modules.Entities.CommunicatesEntities;

import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Communicate extends Entity {
    private static PlayerService playerService = new PlayerService();
    private int playerId;
    private CommunicateType type;
    private Timestamp created_at;

    public Communicate(int playerId) {
        super();
        this.playerId = playerId;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp created_at) throws SQLException {
        super(id, 0);
        this.playerId = playerId;
        this.created_at = created_at;
    }

    public Player getPlayer() {
        return playerService.getPlayerById(this.playerId);
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public Communicate setPlayer(int playerId) {
        this.playerId = playerId;
        return this;
    }

    @Override
    public String toString() {
        return "Communicate{" +
                "id= " + super.getId() +
                "player=" + this.getPlayer().getName() +
                ", type=" + type +
                ", created_at=" + created_at +
                '}';
    }
}
