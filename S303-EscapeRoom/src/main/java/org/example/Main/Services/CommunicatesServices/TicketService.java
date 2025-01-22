package org.example.Main.Services.CommunicatesServices;

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

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketService {

    private static Logger logger = LogManager.getLogger(TicketService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
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
                .getAll(EntityAttributes.objectdeco)
                .stream()
                .map(this::castToTicket)
                .forEach(ticket -> {
                    if (ticket.getId() != id) {
                        throw new TicketNotFoundException();
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

    public void getObjectDecoById(
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
            Sale sale,
            String text
    ) throws SQLException {
        this.assertIfTicketIdNotFound(id);

        Ticket ticket = this.castToTicket(entity);
        ticket.setPlayer(player);
        ticket.setSale(sale);
        ticket.setText(text);

        this.repository
                .update(ticket, EntityAttributes.ticket);
    }

    //Todo verificar estos metodos
    public ArrayList<Ticket> getAllTicket(){
        ArrayList<Ticket> outputList = new ArrayList<>();
        this.repository
                .getAll(EntityAttributes.ticket).forEach(entity -> outputList.add((Ticket) entity));
        return outputList;
    }
}
