package org.example.Services.GameServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.GameNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.GameHasPlayer;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.RepositoryRelations.RepositoryGameHasPlayer;
import org.example.Repository.RepositoryRelations.RepositroyRoomHasObjectDeco;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class GameService {
    private static Logger logger = LogManager.getLogger(GameService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public GameService() {
        this.repository = new RepositoryImpl();
    }

    private Game castToGame(Entity entity) {
        Game game = null;
        if (entity instanceof Game) {
            game = (Game) entity;
        }
        return game;
    }

    private void assertIfGameIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.game)
                .stream()
                .map(this::castToGame)
                .forEach(game -> {
                    if (game.getId() != id) {
                        throw new GameNotFoundException("Game with " + id + " id not found");
                    }
                });
    }

    public void createGame(
            int escapeRoomId
    ) {
        // assertIfGameAlreadyExists(email); //todo no se si este metodo debe ir aqui o dentro de try
        try {
            this
                    .repository
                    .add(new Game(escapeRoomId), EntityAttributes.game);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Game getGameById(
            int id
    ) {
        try {
            //this.assertIfGameIdNotFound(id);
            return (Game) this.repository
                    .getById(id, EntityAttributes.game);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteGame(
            int id
    ){
        try{
            this.assertIfGameIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.game);
        }catch (GameNotFoundException | SQLException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateGame(
            int id,
            int escapeRoomId
    ){
        try {

        this.assertIfGameIdNotFound(id);
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

        Game game = this.castToGame(entity);
        game.setEscapeRoom(escapeRoomId);

        this.repository
                .update(game, EntityAttributes.game);
    }

    public ArrayList<Game> getAllGame(){
        ArrayList<Game> gameArrayList = new ArrayList<>();
        try{

            this.repository
                .getAll(EntityAttributes.game)
                .forEach(game -> gameArrayList.add((Game) game));
            return gameArrayList;
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }
    }

    //TODO verify this method
    public void finishGame(
            int id,
            int finish
    ){
        try{

        assertIfGameIdNotFound(id);
        }catch (SQLException e){
            logger.info(e.getMessage());
        }
        Game game = this.castToGame(entity);
        game.setFinish(finish);

        if(finish == 0){
            game.setFinishedAt(new Timestamp(System.currentTimeMillis()));
        }
    }

    public void addPlayerInGame(int playerId, int gameId) {
        RepositoryGameHasPlayer repo = new RepositoryGameHasPlayer();
        Entity entity = new GameHasPlayer(playerId, gameId);
        try {
            repo.addGameHasPlayer(entity);
        } catch (SQLException e) {
            logger.info("Fail to fetch player in game");
        }
    }

    public ArrayList<Player> getAllPlayersInGame(int gameId) {
        RepositoryGameHasPlayer repoGameHasPlayer = new RepositoryGameHasPlayer();
        try {
            return repoGameHasPlayer.getAllPlayersByGameId(gameId);
        } catch (SQLException e) {
            logger.info("Fail to get players in game[id: " + gameId + "]");
        }
        return null;
    }

    public ArrayList<Game> getAllGamesInPlayer(int playerId) {
        RepositoryGameHasPlayer repoGameHasPlayer = new RepositoryGameHasPlayer();
        try {
            return repoGameHasPlayer.getAllGamesByPlayerId(playerId);
        } catch (SQLException e) {
            logger.info("Fail to get games for player[id: " + playerId + "]");
        }
        return null;
    }

    public Game getLastGameByPlayer(int playerId) {
        ArrayList<Game> games = this.getAllGamesInPlayer(playerId);
        return games.stream()
                .filter(game -> game.getDeleted() == 0) // Ignorar juegos eliminados
                .max(Comparator.comparing(Game::getGameDate)) // Buscar el máximo por `gameDate`
                .orElse(null); // Si no hay juegos, devuelve null
    }
}
