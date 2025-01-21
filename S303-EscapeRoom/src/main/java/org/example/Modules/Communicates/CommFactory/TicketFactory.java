package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;

import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;

public class TicketFactory implements CommFactoryInterface{
    @Override
    public Ticket createCommunicate(int idPlayer) {
        Player player = getPlayerById(idPlayer);
        Sale sale = player.getSale();
        String text = "Last sale for " + sale.getPrice() + "€\n" +
                "Payment success by " +player.getName() + "!";

        return new Ticket(player,text,sale);

    }
}
