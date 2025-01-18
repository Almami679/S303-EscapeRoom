package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Entities.Ticket;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class TicketFactory implements CommFactoryInterface{
    @Override
    public Ticket createCommunicate(int idPlayer) {
        PlayerTEST player = getPlayerById(idPlayer);
        SaleTEST sale = player.getSale();
        String text = "Last sale for " + sale.getPrice() + "€\n" +
                "Payment success by " +player.getName() + "!";

        return new Ticket(player,text,sale);

    }
}
