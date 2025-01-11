/*package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Certificate;
import org.example.Modules.Communicates.CommunicateType;

import java.util.ArrayList;

import static org.example.Main.MainSQLTest.mainFactoryCommunicate;

public class GameTEST {

    private String escapeRoom;
    private ArrayList<PlayerTEST> players;
    private boolean finish;
    private Certificate gameCertificate;

    public GameTEST(String escapeRoom, ArrayList<PlayerTEST> players) {
        this.escapeRoom = escapeRoom;
        this.players = players;
        this.finish = false;
        this.gameCertificate = null;
    }

    public String getEscapeRoom() {
        return escapeRoom;
    }

    public void setGameCertificate(Certificate gameCertificate) {
        this.gameCertificate = gameCertificate;
    }

    public Certificate getGameCertificate() {
        return gameCertificate;
    }

    public void setEscapeRoom(String escapeRoom) {
        this.escapeRoom = escapeRoom;
    }

    public ArrayList<PlayerTEST> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerTEST> players) {
        this.players = players;
    }

    public void finishGame(){
        this.finish = true;
        this.players.forEach(player -> {
            player.addGame(this);
            createGameCertificate(player);
        });

    }
///ARREGLAR LA PARTE DE CERTIFICADOS A LA HORA DE CREARLOS ES UN POCO MEH
    private Certificate createGameCertificate(PlayerTEST player) {
        if (this.finish) {
            if(this.gameCertificate == null) {
                this.gameCertificate = (Certificate) mainFactoryCommunicate.createCommunicate
                        (CommunicateType.CERTIFICATE, player);
            }
        } else {
            LogManager.getLogger(Certificate.class).error("Certificate not created, because this game status["
                    + this.finish +"]");
        }
        return this.gameCertificate;
    }
}*/
