package org.example.Main;

import org.example.Modules.Communicates.CLASESTEST.GameTEST;
import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;
import org.example.Modules.Communicates.CLASESTEST.SaleTEST;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Communicates.Gift;
import org.example.Modules.Communicates.Notification;
import org.example.Modules.Communicates.Ticket;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTEST_LOCAL {

    public static final CommunicateFactory mainFactoryCommunicate = new CommunicateFactory();

    public static void main(String[] args) {

        PlayerTEST player1 = new PlayerTEST("Albert", "albert@gmail.com", true);
        PlayerTEST player2 = new PlayerTEST("Inga", "Inga@gmail.com", false);


        System.out.println("\n------------------------------\n" +
                "Ticket TEST");
        /////////////prueba ticket////////////
        SaleTEST sale1 = new SaleTEST(); //creando venta
        player1.addSale(sale1); //asignando venta a player1
        Ticket ticket1 = (Ticket) mainFactoryCommunicate.createCommunicate(CommunicateType.TICKET,player1);
        System.out.println(ticket1.getText() + "\n" + ticket1.getClass());
        ticket1.send();


        System.out.println("\n------------------------------\n" +
                "Notification TEST");
        ////////////prueba notification/////////
        Notification notification1 = (Notification) mainFactoryCommunicate.createCommunicate(CommunicateType.NOTIFICATION,player2);
        System.out.println(notification1.getText() + "\n" + notification1.getClass());
        notification1.send();

        System.out.println("\n------------------------------\n" +
                "Gift TEST");
        //////////Prueba Gift/////////////////
        Gift gift1 = (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,player2);
        System.out.println(gift1.getText() + "\n" + gift1.getClass());
        gift1.send();

        System.out.println("\n------------------------------\n" +
                "Certificate TEST");
        //////////Prueba Certificate/////////////////
        GameTEST game = new GameTEST("SpaceDream",
                new ArrayList<PlayerTEST>(Arrays.asList(player1, player2)));
        game.finishGame();

    }
}
