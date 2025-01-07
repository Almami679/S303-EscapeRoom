package org.example.Modules.CLASESTESTS;

import java.util.ArrayList;

public class PlayerTEST {
    private static int latestId = 0;
    private int id;
    private String name;
    private String email;
    private boolean consentNotif;
    private boolean deleted;
    private ArrayList<SaleTEST> sales;
    private ArrayList<GameTEST> games;

    public PlayerTEST(String name, String email, boolean consentNotif, boolean deleted) {
        this.id = ++latestId;
        this.name = name;
        this.email = email;
        this.consentNotif = consentNotif;
        this.deleted = deleted;
        this.sales = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public static int getLatestId() {
        return latestId;
    }

    public static void setLatestId(int latestId) {
        PlayerTEST.latestId = latestId;
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

    public boolean getConsentNotif() {
        return consentNotif;
    }

    public void setConsentNotif(boolean consentNotif) {
        this.consentNotif = consentNotif;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void addSale(SaleTEST sale){
        this.sales.add(sale);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addGame(GameTEST game){
        this.games.add(game);
    }

    public GameTEST getLastGame(){
        return games.getLast();
    }

    public SaleTEST getSale() {
        try {

            return this.sales.getLast();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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