package org.example.Modules.Entities.CommunicatesEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Communicates.CommunicationInterface;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Gift extends Communicate implements CommunicationInterface {

    Logger logger = LogManager.getLogger(Gift.class);
    private static RepositoryImpl repositoryImpl = new RepositoryImpl();

    private String text;
    private String discountKey;
    private static ArrayList<String> KeysInUse = new ArrayList<>();
    private Game game;

    public Gift(Player player, String text) {
        super(player);
        this.text = text;
        this.discountKey = generateKey();
        this.game = player.getGame();
    }

    public Gift(int id, int gameId, String text, int playerId, String giftKey, Timestamp createdAt) throws SQLException {
        super(id, playerId, createdAt);
        this.game = (Game) repositoryImpl.getById(gameId, EntityAttributes.game);
        this.text = text;
        this.discountKey = giftKey;
        KeysInUse.add(giftKey);

    }

    public String getText() {
        return text;
    }

    public Gift setText(String text) {
        this.text = text;
        return this;
    }

    public Game getGame(){
        return this.game;
    }

    public String getDiscountKey() {
        return discountKey;
    }

    public Gift setDiscountKey(String discountKey) {
        this.discountKey = discountKey;
        return this;
    }

    private static String generateKey(){
        StringBuilder kb = new StringBuilder();
        String upperLetters = "ABCDEFGHYJKLMNOPQRSTUVWZ";
        do {
            int number = (int) (Math.random() * (upperLetters.length()-1));
            kb.append(upperLetters.charAt(number) + "-");

            number = (int) (Math.random() * 1000000000) + 1;
            kb.append(number);

        } while(KeysInUse.contains(kb.toString()));
        KeysInUse.add(kb.toString());

        return kb.toString();
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id= " + super.getId() +
                "Player= " + super.getPlayer() +
                "text='" + text + '\'' +
                ", discountKey='" + discountKey + '\'' +
                ", game=" + game +
                '}';
    }

    @Override
    public void send() {
        logger.info("sending Gift to " + super.getPlayer().getEmail() +
                " with GiftKey[id:" + super.getId() + " || Key: " + this.discountKey + "]");
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values =  new ArrayList<>();
        String value = super.getId() + "";
        values.add(value);
        value = super.getId() + "";
        values.add(value);
        values.add(this.text);
        values.add(this.discountKey);
        value = super.getPlayer().getId() + "";
        values.add(value);
        return values;
    }
}
