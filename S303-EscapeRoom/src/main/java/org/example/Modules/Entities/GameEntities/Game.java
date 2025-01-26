package org.example.Modules.Entities.GameEntities;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.example.Services.GameServices.GameService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Game extends Entity {

    private static EscapeRoomService escaperoomService = new EscapeRoomService();
    private static GameService gameService = new GameService();

    private int escapeRoomId;
    private Timestamp gameDate;
    private int finish;
    private Certificate gameCertificate;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Timestamp finishedAt;

    public Game(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
        this.finish = 0;
        this.gameCertificate = null;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updateAt = new Timestamp(System.currentTimeMillis());
        this.finishedAt = new Timestamp(System.currentTimeMillis());
    }

    public Game (int id, int escapeRoomId, int finished, int deleted, Timestamp createdAt, Timestamp updateAt) throws SQLException {
        super(id, deleted);
        this.escapeRoomId = escapeRoomId;
        this.finish = finished;
        this.createdAt = createdAt;
        this.updateAt = updateAt;

    }

    public EscapeRoom getEscapeRoom() {
        return escaperoomService.getEscapeRoomById(escapeRoomId);
    }

    public Timestamp getGameDate() {
        return this.gameDate;
    }

    public Timestamp getFinishedAt() {
        return this.finishedAt;
    }

    public int getId(){
        return super.getId();
    }

    public void setGameCertificate(Certificate gameCertificate) {
        this.gameCertificate = gameCertificate;
    }

    public Certificate getGameCertificate() {
        return gameCertificate;
    }

    public void setEscapeRoom(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> playersObj = gameService.getAllPlayersInGame(super.getId());
        return playersObj;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public void setFinishedAt(Timestamp finishedAt) {
        this.finishedAt = finishedAt;
    }

    public void finishGame(){
        this.finish = 1;
        this.finishedAt = new Timestamp(System.currentTimeMillis());
        LogManager.getLogger(Game.class).info("GameId: " + super.getId() +
                " has finish.");
        ArrayList<Player> players = getPlayers();
        players.forEach(player -> {
            player.addGame(super.getId());
            LogManager.getLogger(Game.class).info("Finish Game with Id: " + super.getId() +
                    " added to player " + player.getName());
        });
    }

    @Override
    public String toString() {
        return "Game{" +
                "escapeRoomId=" + escapeRoomId +
                ", gameDate=" + gameDate +
                ", finish=" + finish +
                ", gameCertificate=" + gameCertificate +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", finishedAt=" + finishedAt +
                '}';
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = this.escapeRoomId+"";
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
