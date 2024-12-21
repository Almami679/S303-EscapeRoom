package org.example.Main;

import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;
import org.example.Modules.Communicates.CLASESTEST.SaleTEST;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Communicates.Gift;
import org.example.Modules.Communicates.Notification;
import org.example.Modules.Communicates.Ticket;

public class MainTEST_LOCAL {
    public static void main(String[] args) {

        PlayerTEST player1 = new PlayerTEST("Albert", "albert@gmail.com", true);
        PlayerTEST player2 = new PlayerTEST("Inga", "Inga@gmail.com", false);


        CommunicateFactory mainFactoryCommunicate = new CommunicateFactory();

        /////////////prueba ticket////////////
        SaleTEST sale1 = new SaleTEST(); //creando venta
        player1.addSale(sale1); //asignando venta a player1
        Ticket ticket1 = (Ticket) mainFactoryCommunicate.createCommunicate(CommunicateType.TICKET,player1);
        System.out.println(ticket1.getText() + "\n" + ticket1.getClass());
        ticket1.send();


        System.out.println("\n------------------------------\n");
        ////////////prueba notification/////////
        Notification notification1 = (Notification) mainFactoryCommunicate.createCommunicate(CommunicateType.NOTIFICATION,player2);
        System.out.println(notification1.getText() + "\n" + notification1.getClass());
        notification1.send();

        System.out.println("\n------------------------------\n");
        //////////Prueba Gift/////////////////
        Gift gift1 = (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,player2);
        System.out.println(gift1.getText() + "\n" + gift1.getClass());
        gift1.send();

    }
}
