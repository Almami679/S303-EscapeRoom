package org.example.Modules.Entities.GameEntities;

import org.example.Modules.Entities.Entity;
import org.example.Services.GameServices.GameService;
import org.example.Services.GameServices.SaleService;
import org.example.observers.Observer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player extends Entity implements Observer {

    private static GameService gameService = new GameService();
    private static SaleService saleService = new SaleService();

    private String name;
    private String email;
    private int consentNotif;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private ArrayList<Integer> completedGamesIds;
    private ArrayList<Integer> playerSalesIds;


    public Player(String name,
                      String email,
                      int consentNotif) {
        super();
        this.name = name;
        this.email = email;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updateAt = new Timestamp(System.currentTimeMillis());
        this.consentNotif = consentNotif;
        this.completedGamesIds = new ArrayList<>();
        this.playerSalesIds = new ArrayList<>();
    }

    public Player(int id,
                      String name,
                      String email,
                      int consentNotif,
                      int deleted,
                      Timestamp createdAt,
                      Timestamp updateAt) {
        super(id, deleted);
        this.consentNotif = consentNotif;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updateAt = updateAt;

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

    public void addSale(int saleId){
        this.playerSalesIds.add(saleId);
    }

    public ArrayList<Game> getGames(){
        return gameService.getAllGamesInPlayer(super.getId());
    }

//    public Game getLastGame(){
//        return this.getGames().getLast();
//    }

    public Game getLastGame() {
        List<Game> games = this.getGames();
        if (games != null && !games.isEmpty()) {
            return games.get(games.size() - 1);
        }
        return null; // Si no hay juegos, devuelve null o lanza una excepción.
    }

    public ArrayList<Sale> getSales(){
        ArrayList<Sale> salesForPlayer = new ArrayList<>();
        playerSalesIds.forEach(saleId-> salesForPlayer.add(saleService.getSaleById(saleId)));
        return salesForPlayer;
    }

    public Sale getLastSale(){
        return getSales().stream()
                .filter(sale -> sale.getDeleted() == 0)
                .max(Comparator.comparing(Sale::getCreatedAt))
                .orElse(null);
    }

    public void addGame(int gameId){
        this.completedGamesIds.add(gameId);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", consentNotif=" + consentNotif +
                ", deleted=" + super.getDeleted() +
                '}';
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        values.add(name);
        values.add(email);
        value = this.consentNotif+"";
        values.add(value);
        value = super.getDeleted()+"";
        values.add(value);
        values.add(createdAt.toString());
        values.add(updateAt.toString());
        return values;
    }

    @Override
    public void update(String msg) {
        if(this.consentNotif == 1){
            System.out.println("Notificación para " + this.name + " " +  this.email + " : " + msg);
        }
    }
}