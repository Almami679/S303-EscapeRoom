package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;


public class TicketFactory implements CommFactoryInterface{
    private static final PlayerService playerService = new PlayerService();
    @Override
    public Ticket createCommunicate(int playerId) {


        Sale sale = playerService.getPlayerById(playerId).getLastSale();
        String text = "Last sale for " + sale.getPrice() + "€\n" +
                "Payment success by " +playerService.getPlayerById(playerId).getName() + "!";

        return new Ticket(playerId,text,sale.getId());
    }
}
