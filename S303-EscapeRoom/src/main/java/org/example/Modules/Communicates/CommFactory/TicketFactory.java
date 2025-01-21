package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;


public class TicketFactory implements CommFactoryInterface{
    private RepositoryImpl repositoryImpl = new RepositoryImpl();
    @Override
    public Ticket createCommunicate(int idPlayer) throws SQLException {
        Player player = (Player) repositoryImpl.getById(idPlayer, EntityAttributes.player);
        Sale sale = player.getSale();
        String text = "Last sale for " + sale.getPrice() + "€\n" +
                "Payment success by " +player.getName() + "!";

        return new Ticket(player,text,sale);

    }
}
