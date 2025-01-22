package org.example.Main.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Exceptions.SaleIdNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;

public class SaleService {
    private static Logger logger = LogManager.getLogger(SaleService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public SaleService(Repository repository) {
        this.repository = repository;
    }

    private Sale castToSale(Entity entity) {
        Sale sale = null;
        if (entity instanceof Sale) {
            sale = (Sale) entity;
        }
        return sale;
    }



    private void assertIfSaleIdNotFound(int id) {
        this.repository
                .getAll(EntityAttributes.sale)
                .stream()
                .map(this::castToSale)
                .forEach(sale -> {
                    if (sale.getId() != id) {
                        throw new SaleIdNotFoundException();
                    }
                });
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

    public void getSaleById(
            int id
    ) {
        try {
            this.assertIfSaleIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.sale);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteSale(
            int id
    ){
        try{
            this.assertIfSaleIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.sale);
        }catch (PlayerNotFound e){
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

    //Todo verificar estos metodos
    public void getAllSale(){
        this.repository
                .getAll(EntityAttributes.sale);
    }
}
