package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.GameService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Certificate extends Communicate implements CommunicationInterface {

    private static GameService gameService = new GameService();

    Logger logger = LogManager.getLogger(Certificate.class);
    private String text;
    private int gameId;


    public Certificate(int playerId , String text, int gameId) {
        super(playerId);
        this.gameId = gameId;
        this.text = text;
    }
    public Certificate(int id,
                       int gameId,
                       int playerId,
                       String text,
                       Timestamp created_at) throws SQLException {
        super(id, playerId, created_at);
        this.gameId = gameId;
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public Game getGame() {
        return gameService.getGameById(gameId);
    }

    public Timestamp getGameDate() {
        return this.getGame().getGameDate();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setGame(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id= " + super.getId() +
                "Player= " + super.getPlayer().getName() +
                "text='" + text + '\'' +
                ", gameId=" + gameId +
                '}';
    }

    @Override
    public void send() {
        logger.info("sending Certificate to " + super.getPlayer().getEmail() + "\n" +
                "Game[" + this.gameId + "]\nFinished at[" +
                this.getGameDate() + "]");

    }
    @Override
     public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = super.getId() + "";
        values.add(value);
        values.add(this.text);
        values.add(super.getCreatedAt().toString());
        value = super.getPlayer().getId() + "";
        values.add(value);
        return values;
     }
}
