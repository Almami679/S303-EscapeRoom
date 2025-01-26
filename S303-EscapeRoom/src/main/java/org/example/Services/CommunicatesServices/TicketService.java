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




    public void createTicket(
            Ticket ticket
    ) {
        try {
            this
                    .repository
                    .add(ticket, EntityAttributes.ticket);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Ticket getTicketById(
            int id
    ) {
        try {
            Ticket ticket = (Ticket) this.repository.getById(id, EntityAttributes.ticket);
            if (ticket == null) {
                throw new TicketNotFoundException("Ticket with id " + id + " not found");
            } else {
                return (Ticket) this.repository
                        .getById(id, EntityAttributes.ticket);
            }
        } catch (SQLException | TicketNotFoundException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteTicket(
            int id
    ) {
        try {
            Ticket ticket = (Ticket) this.repository.getById(id, EntityAttributes.ticket);
            if (ticket == null) {
                throw new TicketNotFoundException("Ticket with id " + id + " not found");
            } else {
                this.repository
                        .delete(id, EntityAttributes.ticket);
            }
        } catch (TicketNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateTicket(
            int id,
            int player,
            int sale,
            String text
    ) {
        try {
            Ticket ticket = (Ticket) this.repository.getById(id, EntityAttributes.ticket);
            if (ticket == null) {
                throw new TicketNotFoundException("Ticket with id " + id + " not found");
            } else {

                ticket.setPlayer(player);
                ticket.setSale(sale);
                ticket.setText(text);
                this.repository
                        .update(ticket, EntityAttributes.ticket);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

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
