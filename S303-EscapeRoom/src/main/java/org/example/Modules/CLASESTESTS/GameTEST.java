package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Certificate;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Repository.Common.EscapeRoomTEST;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.example.Main.MainSQLTest.mainFactoryCommunicate;
//import static org.example.Repository.SqlEscapeRoomRepository.getEscapeRoomById;

public class GameTEST {

    private int id;
    private EscapeRoomTEST escapeRoom;
    private Timestamp gameDate;
    private ArrayList<PlayerTEST> players;
    private int deleted;
    private boolean finish;
    private Certificate gameCertificate;

    public GameTEST(EscapeRoomTEST escapeRoom, Timestamp date, ArrayList<PlayerTEST> players, int deleted) {
        this.escapeRoom = escapeRoom;
        this.gameDate = date;
        this.players = players;
        this.deleted = deleted;
        this.finish = false;
        this.gameCertificate = null;
    }

    public GameTEST (Timestamp date, int escapeRoomId, int finished, int deleted) {
        this.gameDate = date;
        //this.escapeRoom = getEscapeRoomById(escapeRoomId);
        this.finish = checkStatus(finished);
        this.deleted = deleted;

        ///Añadir lista de jugadores cuando tengamos el SQL de gameHasPLayers
    }

    public int getId() {
        return id;
    }

    private boolean checkStatus(int status) {
        boolean output = status != 0;
        return output;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setGameCertificate(Certificate gameCertificate) {
        this.gameCertificate = gameCertificate;
    }

    public Certificate getGameCertificate() {
        return gameCertificate;
    }

    public void setEscapeRoom(EscapeRoomTEST escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public ArrayList<PlayerTEST> getPlayers() {
        return players;
    }

    public void finishGame(){
        this.finish = true;
        LogManager.getLogger(GameTEST.class).info("GameId: " + this.id +
                " has finish.");
        this.players.forEach(player -> {
            player.addGame(this);
            LogManager.getLogger(GameTEST.class).info("Finish Game with Id: " + this.id +
                    " added to player " + player.getName());
        });

    }
}
