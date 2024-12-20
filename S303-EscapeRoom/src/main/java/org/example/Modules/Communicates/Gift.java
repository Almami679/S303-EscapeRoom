package org.example.Modules.Communicates;

import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;

import java.util.ArrayList;

public class Gift extends Communicate {

    private String text;
    private String discountKey;
    private static ArrayList<String> KeysInUse = new ArrayList<>();

    public Gift(PlayerTEST player, String text) {
        super(player);
        this.text = text;
        this.discountKey =
    }

    public String getText() {
        return text;
    }

    public Gift setText(String text) {
        this.text = text;
        return this;
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
        int number = (int) (Math.random() * 10) + 1;

        kb.append(", Mundo!");

        return ( kb;
    }
}
