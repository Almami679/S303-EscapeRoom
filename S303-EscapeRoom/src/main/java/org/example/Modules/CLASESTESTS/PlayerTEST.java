package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PlayerTEST {
    private static final Logger logger = LogManager.getLogger(EscapeRoomTEST.class);
    private int id;
    private String name;
    private String email;
    private int consentNotif;
    private int deleted;
    private ArrayList<GameTEST> completedGames;
    private ArrayList<SaleTEST> playerSales;


    public PlayerTEST(String name, String email, int consentNotif, int deleted) {
        this.name = name;
        this.email = email;
        this.consentNotif = consentNotif;
        this.deleted = deleted;
        this.completedGames = new ArrayList<>();
        this.playerSales = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConsentNotif() {
        return consentNotif;
    }

    public void setConsentNotif(int consentNotif) {
        this.consentNotif = consentNotif;
    }

    public int isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void addSale(SaleTEST sale){
        this.playerSales.add(sale);
    }

    public GameTEST getGame(){
        return this.completedGames.getLast();
    }

    public SaleTEST getSale(){
        return this.playerSales.getLast();
    }

    public void addGame(GameTEST game){
        this.completedGames.add(game);
    }

    @Override
    public String toString() {
        return "PlayerTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", consentNotif=" + consentNotif +
                ", deleted=" + deleted +
                '}';
    }
}