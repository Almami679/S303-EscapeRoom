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
    private Timestamp created_at;

    public Communicate(Player player) {
        super();
        this.player = player;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public Communicate(int id, int playerId, Timestamp created_at) throws SQLException {
        super(id, 0);
        this.player = (Player) repositoryImpl.getById(playerId, EntityAttributes.player);
        this.created_at = created_at;
    }

    public Player getPlayer() {
        return player;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public Communicate setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public String toString() {
        return "Communicate{" +
                "id= " + super.getId() +
                "player=" + player +
                ", type=" + type +
                ", created_at=" + created_at +
                '}';
    }
}
