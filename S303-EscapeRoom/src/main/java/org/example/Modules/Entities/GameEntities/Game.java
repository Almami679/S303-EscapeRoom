package org.example.Modules.Entities.GameEntities;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;


public class Game extends Entity {

    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    private Timestamp createdAt;
    private Timestamp updateAt;
    private Timestamp finishedAt;
    private EscapeRoom escapeRoom;
    private ArrayList<Player> players;
    private Certificate gameCertificate;
    private int finish;


    public Game(
            EscapeRoom escapeRoom,
            ArrayList<Player> players
    ) {
        this.escapeRoom = escapeRoom;
        this.players = players;
        this.finish = 0;
        this.gameCertificate = null;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updateAt = Timestamp.from(Instant.now());
        this.finishedAt = null;
    }

    public Game (
            int id,
            int escapeRoomId,
            Timestamp createdAt,
            Timestamp updateAt,
            int finished,
            int deleted
    ) throws SQLException {
        super(id, deleted);
        this.escapeRoom = (EscapeRoom) repositoryImpl.getById(escapeRoomId, EntityAttributes.escaperoom);
        this.finish = finished;
        this.createdAt = createdAt;
        this.updateAt = updateAt;

        ///Añadir lista de jugadores cuando tengamos el SQL de gameHasPLayers
    }

    public EscapeRoom getEscapeRoom() {
        return escapeRoom;
    }

    public Timestamp getFinishedAt() {
        return this.finishedAt;
    }

    public void setGameCertificate(Certificate gameCertificate) {
        this.gameCertificate = gameCertificate;
    }

    public Certificate getGameCertificate() {
        return gameCertificate;
    }

    public void setEscapeRoom(EscapeRoom escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void finishGame(){
        this.finish = 1;
        this.finishedAt = new Timestamp(System.currentTimeMillis());
        LogManager.getLogger(Game.class).info("GameId: " + super.getId() +
                " has finish.");
        this.players.forEach(player -> {
            player.addGame(this);
            LogManager.getLogger(Game.class).info("Finish Game with Id: " + super.getId() +
                    " added to player " + player.getName());
        });
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.escapeRoom.getId()+"";
        values.add(value);
        value = this.finish +"";
        values.add(value);
        value = super.getDeleted()+"";
        values.add(value);
        values.add(this.createdAt.toString());
        values.add(this.updateAt.toString());
        return values;
    }
}
