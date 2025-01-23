package org.example.Main.Services.GameServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerService {
    private static Logger logger = LogManager.getLogger(PlayerService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public PlayerService() {
        this.repository = new RepositoryImpl();
    }

    private Player castToPlayer(Entity entity) {
        Player player = null;
        if (entity instanceof Player) {
            player = (Player) entity;
        }
        return player;
    }


    private void assertIfPlayerAlreadyExists(String email) throws SQLException {
        this.repository
                .getAll(EntityAttributes.player)
                .stream()
                .map(this::castToPlayer)
                .forEach(player -> {
                    if (player.getEmail().equals(email)) {
                        System.out.println("player already exists");
                    }
                });
    }

    private void assertIfPlayerIdNotFound(int id) throws SQLException {
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
        // assertIfPlayerAlreadyExists(email); //todo no se si este metodo debe ir aqui o dentro de try
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
    ) {
        try {
            this.assertIfPlayerIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.player);
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updatePlayer(
            int id,
            String name,
            String email,
            int consentNotif
    ) {
        try {

            this.assertIfPlayerIdNotFound(id);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        Player player = this.castToPlayer(entity);
        player.setName(name);
        player.setEmail(email);
        player.setConsentNotif(consentNotif);

        this.repository
                .update(player, EntityAttributes.player);
    }

    public ArrayList<Player> getAllPlayer() {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.player)
                    .forEach(player -> playerArrayList.add((Player) player));
            return playerArrayList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
