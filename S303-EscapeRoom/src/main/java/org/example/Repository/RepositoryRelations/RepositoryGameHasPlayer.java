package org.example.Repository.RepositoryRelations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.GameHasPlayer;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.RoomHasObject;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryGameHasPlayer extends RepositoryImpl {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    public RepositoryGameHasPlayer() {
        super();
    }

    public void addGameHasPlayer(Entity entity) throws SQLException {
        String query = "INSERT INTO escaperoomdb.game_has_player (game_game_id, player_player_id) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE player_player_id = VALUES(player_player_id);";
        ArrayList<String> values = entity.getValues();
        Serializer.serialize(query, EntityAttributes.game_has_player, "add", values);
    }

    public ArrayList<Player> getAllPlayersByGameId(int gameId) throws SQLException {
        String query = "SELECT * FROM escaperoomdb.game_has_player WHERE game_game_id = " + gameId +";";
        ArrayList<Entity> idPlayersInGame;
        ArrayList<Player> playersInGame = new ArrayList<>();

        idPlayersInGame = Serializer.deserializeGetAll(query, EntityAttributes.game_has_player);

        for (Entity entityForId : idPlayersInGame) {
            try {
                GameHasPlayer entity = (GameHasPlayer) entityForId;
                Player player = (Player) getById(entity.getIdPlayer(), EntityAttributes.player); // Corrige el método `getIdPlayer`
                playersInGame.add(player);
            } catch (SQLException e) {
                logger.error("Error al obtener el jugador para gameId: " + gameId + ", Detalles: " + e.getMessage());
            }
        }

        return playersInGame;
    }

    public ArrayList<Game> getAllGamesByPlayerId(int playerId) throws SQLException {
        String query = "SELECT * FROM escaperoomdb.game_has_player WHERE player_player_id = " + playerId +";";
        ArrayList<Entity> idGamesForPlayer;
        ArrayList<Game> games = new ArrayList<>();
        idGamesForPlayer = Serializer.deserializeGetAll(query,EntityAttributes.game_has_player);
        idGamesForPlayer.forEach(entityForId -> {
            try {
                GameHasPlayer entity = (GameHasPlayer) entityForId;
                games.add((Game) getById(entity.getId(), EntityAttributes.game));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return games;
    }
}
