package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerAlreadyExistsException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class PlayerService {

    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    private final Repository repository;


    public PlayerService(Repository repository) {
        this.repository = repository;
    }

    private Player castToPlayer(Entity entity) {
        Player player = null;
        if (entity instanceof Player) {
            player = (Player) entity;
        }
        return player;
    }


    private void assertIfPlayerAlreadyExists(String email) {
        this.repository
                .getAll(EntityAttributes.player)
                .stream()
                .map(this::castToPlayer)
                .forEach(player -> {
                    if (player.getEmail().equals(email)) {
                        throw new PlayerAlreadyExistsException();
                    }
                });
    }

    private void assertIfPlayerIdNotFound(int id) {
        this.repository
                .getAll(EntityAttributes.player)
                .stream()
                .map(this::castToPlayer)
                .forEach(player -> {
                    if (player.getId() != id) {
                        throw new PlayerNotFound("Player with " + id + " id not found");
                    }
                });
    }

    public void createPlayer(
            String name,
            String email,
            int consentNotif
    ) {
        assertIfPlayerAlreadyExists(email); //todo no se si este metodo debe ir aqui o dentro de try
        try {
            this
                    .repository
                    .add(new Player(name, email, consentNotif), EntityAttributes.player);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getPlayerById(
            int id
    ) {
        try {
            this.assertIfPlayerIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.player);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deletePlayer(
            int id
    ){
        try{
            this.assertIfPlayerIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.player);
        }catch (PlayerNotFound e){
            logger.info(e.getMessage());
        }
    }

}
