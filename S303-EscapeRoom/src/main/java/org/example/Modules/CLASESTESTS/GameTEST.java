package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Certificate;
import org.example.Modules.Communicates.CommunicateType;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.example.Main.MainSQLTest.mainFactoryCommunicate;

public class GameTEST {

    private int id;
    private EscapeRoomTEST escapeRoom;
    private Timestamp gameDate;
    private ArrayList<PlayerTEST> players;
    private int deleted;
    private int finish;

    public GameTEST(EscapeRoomTEST escapeRoom, Timestamp date, ArrayList<PlayerTEST> players, int deleted) {
        this.escapeRoom = escapeRoom;
        this.gameDate = date;
        this.players = players;
        this.deleted = deleted;
        this.finish = 0;
    }

    public int getId() {
        return id;
    }

    public EscapeRoomTEST getEscapeRoom() {
        return escapeRoom;
    }

    public Timestamp getGameDate() {
        return this.gameDate;
    }

    public int getDeleted() {
        return this.deleted;
    }

    public int getStatus() {
        return this.finish;
    }


    public void setEscapeRoom(EscapeRoomTEST escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public ArrayList<PlayerTEST> getPlayers() {
        return players;
    }

    public void finishGame(){
        this.finish = 1;
        LogManager.getLogger(GameTEST.class).info("GameId: " + this.id +
                " has finish.");
        this.players.forEach(player -> {
            player.addGame(this);
            LogManager.getLogger(GameTEST.class).info("Finish Game with Id: " + this.id +
                    " added to player " + player.getName());
        });

    }

    public boolean checkGameStatus() {
        boolean output = false;
        if (this.finish == 1) {
            output = true;
        }
        return output;
    }

}
