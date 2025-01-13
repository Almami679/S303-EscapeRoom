package org.example.Modules.Communicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.util.ArrayList;

public class Gift extends Communicate implements CommunicationInterface{

    Logger logger = LogManager.getLogger(Gift.class);
    private String text;
    private String discountKey;
    private static ArrayList<String> KeysInUse = new ArrayList<>();
    private GameTEST game;

    public Gift(PlayerTEST player, String text) {
        super(player);
        this.text = text;
        this.discountKey = generateKey();
        this.game = player.getGame();
    }

    public String getText() {
        return text;
    }

    public Gift setText(String text) {
        this.text = text;
        return this;
    }

    public GameTEST getGame(){
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
    public void send() {
        logger.info("sending Gift to " + super.getPlayer().getEmail() +
                " with GiftKey[id:" + super.getId() + " || Key: " + this.discountKey + "]");
    }
}
