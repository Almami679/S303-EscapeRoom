package org.example.Services.GameServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.PlayerAlreadyExistsException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.RepositoryRelations.RepositoryGameHasPlayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PlayerService {
    private static Logger logger = LogManager.getLogger(PlayerService.class);

    private final RepositoryImpl repository;


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


    private boolean assertIfPlayerAlreadyExists(String email) throws SQLException {
        return this.repository
                .getAll(EntityAttributes.player)
                .stream()
                .filter(entity -> entity instanceof Player)
                .map(this::castToPlayer)
                .anyMatch(player -> player.getEmail().equalsIgnoreCase(email));
    }


    public void createPlayer(
            String name,
            String email,
            int consentNotif
    ) {
        try {
            if (assertIfPlayerAlreadyExists(email)) {
                throw new PlayerAlreadyExistsException();
            }else{
            this
                    .repository
                    .add(new Player(name, email, consentNotif), EntityAttributes.player);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }catch (PlayerAlreadyExistsException e){
            logger.info("Player with email: " + email + " already exists. " + e.getMessage());
        }
    }

    public Player getPlayerById(int id) {
        try {
            Player player = (Player) this.repository.getById(id, EntityAttributes.player);

            if (player == null) {
                throw new PlayerNotFound("Player with id " + id + " not found");
            } else {
                logger.info(player.getValues());
            }

            return player;

        } catch (PlayerNotFound e) {
            logger.warn(e.getMessage());
            return null;
        } catch (SQLException e) {
            logger.error("Database error: " + e.getMessage());
            return null;
        }
    }

    public void deletePlayer(
            int id
    ) {
        try {
            Player player = (Player) this.repository.getById(id, EntityAttributes.player);

            if (player == null) {
                throw new PlayerNotFound("Player with id " + id + " not found");
            } else {
                this.repository
                        .delete(id, EntityAttributes.player);

            }
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }


    public void updatePlayer(
            int id,
            String name,
            String email,
            int consentNotif
    ) {
        try {

            Player player = (Player) this.repository.getById(id, EntityAttributes.player);

            if (player == null) {
                throw new PlayerNotFound("Player with id " + id + " not found");
            } else {
                player.setName(name);
                player.setEmail(email);
                player.setConsentNotif(consentNotif);
                player.setUpdateAt(new Timestamp(System.currentTimeMillis()));
                this.repository
                        .update(player, EntityAttributes.player);

            }
        } catch (PlayerNotFound | SQLException e) {

            logger.info(e.getMessage());
        }
    }

    public ArrayList<Player> getAllPlayer() {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.player)
                    .forEach(player ->
                            playerArrayList.add((Player) player)
                    );
            return playerArrayList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public Game getGameForId(int playerId, int gameId) {
        RepositoryGameHasPlayer repoGameHasPlayer = new RepositoryGameHasPlayer();
        try {
            return repoGameHasPlayer.getAllGamesByPlayerId(playerId).stream()
                    .filter(game -> game.getId() == gameId)
                    .findFirst()
                    .orElse(null);

        } catch (SQLException e) {
            logger.info("Fail to get games for player[id: " + playerId + "]");
        }
        return null;
    }
}
