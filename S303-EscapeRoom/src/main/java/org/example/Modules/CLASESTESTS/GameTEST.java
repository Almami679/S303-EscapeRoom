/*package org.example.Modules.CLASESTESTS;

import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.Communicates.Certificate;
import org.example.Modules.Communicates.CommunicateType;

import java.util.ArrayList;

import static org.example.Main.MainSQLTest.mainFactoryCommunicate;

public class GameTEST {

    private String escapeRoom;
    private ArrayList<PlayerTEST> players;
    private boolean finish;

    public GameTEST(String escapeRoom, ArrayList<PlayerTEST> players) {
        this.escapeRoom = escapeRoom;
        this.players = players;
        this.finish = false;
    }

    public String getEscapeRoom() {
        return escapeRoom;
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

    private void createGameCertificate(PlayerTEST player) {
        if (this.finish) {
            Certificate certificate = (Certificate) mainFactoryCommunicate.createCommunicate
                                                                (CommunicateType.CERTIFICATE,player);
            System.out.println(certificate.getText());
            certificate.send();

        }
    }
}*/
