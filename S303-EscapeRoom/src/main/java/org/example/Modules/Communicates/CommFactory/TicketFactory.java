package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.CLASESTEST.PlayerTEST;
import org.example.Modules.Communicates.CLASESTEST.SaleTEST;
import org.example.Modules.Communicates.Ticket;

public class TicketFactory implements CommFactoryInterface{
    @Override
    public Ticket createCommunicate(PlayerTEST player) {
        SaleTEST sale = player.getSale();
        String text = "Last sale for " + sale.getPrice() + "€\n" +
                "Payment success by " +player.getName() + "!\n" +
                "Ticket sent to email: " + player.getEmail();

        return new Ticket(player,text,sale);

    }
}
