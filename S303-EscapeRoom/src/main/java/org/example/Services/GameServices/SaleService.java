package org.example.Services.GameServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.SaleIdNotFoundException;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.CommunicatesServices.TicketService;

import java.sql.SQLException;
import java.util.ArrayList;

public class SaleService {
    private static final Logger logger = LogManager.getLogger(SaleService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public SaleService() {
        this.repository = new RepositoryImpl();
    }

    private Sale castToSale(Entity entity) {
        Sale sale = null;
        if (entity instanceof Sale) {
            sale = (Sale) entity;
        }
        return sale;
    }


    private void assertIfSaleIdNotFound(int id) throws SQLException {
        boolean roomFound = this.repository
                .getAll(EntityAttributes.room)
                .stream()
                .map(this::castToSale)
                .anyMatch(room -> room.getId() == id);

        if (!roomFound) {
            throw new SaleIdNotFoundException();
        }
    }

    public void createSale(
            double price,
            Game game
    ) {
        try {
            this
                    .repository
                    .add(new Sale(price, game), EntityAttributes.sale);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Sale getSaleById(
            int id
    ) {
        try {
            //this.assertIfSaleIdNotFound(id);
            return (Sale) this.repository
                    .getById(id, EntityAttributes.sale);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteSale(
            int id
    ) {
        try {
            this.assertIfSaleIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.sale);
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateSale(
            int id,
            double price,
            Game game
    ) throws SQLException {
        this.assertIfSaleIdNotFound(id);

        Sale sale = this.castToSale(entity);
        sale.setPrice(price);
        sale.setGame(game.getId());

        this.repository
                .update(sale, EntityAttributes.sale);
    }

    public ArrayList<Sale> getAllSale() {
        ArrayList<Sale> saleArrayList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.sale)
                    .forEach(sale -> saleArrayList.add((Sale) sale));
            return saleArrayList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public Ticket getTicketSale(int saleId, int playerId) {
        CommunicateFactory mainFactory = new CommunicateFactory();
        PlayerService playerService = new PlayerService();
        TicketService ticketService = new TicketService();

        Player player = playerService.getPlayerById(playerId);
        player.addSale(saleId);
        Ticket ticket = (Ticket) mainFactory.createCommunicate(CommunicateType.TICKET, playerId);
        ticketService.createTicket(ticket);
        ticket.send();
        return ticket;
    }
}
