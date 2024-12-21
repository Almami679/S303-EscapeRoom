package org.example.Modules.Communicates.CLASESTEST;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerTEST {

    private String name;
    private String email;
    private ArrayList<SaleTEST> sales;
    private ArrayList<GameTEST> games;

    private boolean consent;

    public PlayerTEST(String name, String email, boolean consent) {
        this.name = name;
        this.email = email;
        this.consent = consent;
        this.sales = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getConsent() {
        return consent;
    }

    public void addSale(SaleTEST sale){
        this.sales.add(sale);
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
}
