package org.example.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.TicketNotFoundException;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketService {

    private static Logger logger = LogManager.getLogger(TicketService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public TicketService() {
        this.repository = new RepositoryImpl();
    }

    private Ticket castToTicket(Entity entity) {
        Ticket ticket = null;
        if (entity instanceof Ticket) {
            ticket = (Ticket) entity;
        }
        return ticket;
    }


    private void assertIfTicketIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.objectdeco)
                .stream()
                .map(this::castToTicket)
                .forEach(ticket -> {
                    if (ticket.getId() != id) {
                        throw new TicketNotFoundException("Tocket with id " + id + " not found");
                    }
                });
    }

    public void createTicket(
            int idPlayer
    ) {
        try {
            Ticket ticket = (Ticket) mainFactory.createCommunicate(CommunicateType.TICKET, idPlayer);
            this
                    .repository
                    .add(ticket, EntityAttributes.objectdeco);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Ticket getTicketById(
            int id
    ) {
        try {
            //this.assertIfTicketIdNotFound(id);
            return (Ticket) this.repository
                    .getById(id, EntityAttributes.ticket);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteTicket(
            int id
    ) {
        try {
            this.assertIfTicketIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.ticket);
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateTicket(
            int id,
            Player player,
            Sale sale,
            String text
    ) {
        try {
            this.assertIfTicketIdNotFound(id);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        Ticket ticket = this.castToTicket(entity);
        ticket.setPlayer(player);
        ticket.setSale(sale);
        ticket.setText(text);

        this.repository
                .update(ticket, EntityAttributes.ticket);
    }

    //Todo verificar estos metodos
    public ArrayList<Ticket> getAllTicket() {
        ArrayList<Ticket> outputList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.ticket).forEach(entity -> outputList.add((Ticket) entity));
            return outputList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
