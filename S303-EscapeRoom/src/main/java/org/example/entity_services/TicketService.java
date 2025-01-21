package org.example.entity_services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.TicketNotFoundException;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;

public class TicketService {
    private static final Logger logger = LogManager.getLogger(TicketService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public TicketService(Repository repository) {
        this.repository = repository;
    }

    private Ticket castToTicket(Entity entity) {
        Ticket ticket = null;
        if (entity instanceof Ticket) {
            ticket = (Ticket) entity;
        }
        return ticket;
    }



    private void assertIfTicketIdNotFound(int id) {
        this.repository
                .getAll(EntityAttributes.ticket)
                .stream()
                .map(this::castToTicket)
                .forEach(ticket -> {
                    if (ticket.getId() != id) {
                        throw new TicketNotFoundException("Ticket with id: " + id + " not found");
                    }
                });
    }

    public void createTicket(
            Player player,
            String text,
            Sale sale
    ) {
        try {
            this
                    .repository
                    .add(new Ticket(player, text, sale), EntityAttributes.ticket);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getTicketById(
            int id
    ) {
        try {
            this.assertIfTicketIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.ticket);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteTicket(
            int id
    ){
        try{
            this.assertIfTicketIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.ticket);
        }catch (PlayerNotFound e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateTicket(
            int id,
            Player player,
            String text
    ) throws SQLException {
        this.assertIfTicketIdNotFound(id);

        Ticket ticket = this.castToTicket(entity);
        ticket.setPlayer(player);
        ticket.setText(text);

        this.repository
                .update(ticket, EntityAttributes.ticket);
    }

    //Todo verificar estos metodos
    public void getAllTicket(){
        this.repository
                .getAll(EntityAttributes.ticket);
    }
}
