package org.example.Services.GameServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.GameNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
            EscapeRoom escapeRoom,
            ArrayList<Player> players
    ) {
        // assertIfGameAlreadyExists(email); //todo no se si este metodo debe ir aqui o dentro de try
        try {
            this
                    .repository
                    .add(new Game(escapeRoom, players), EntityAttributes.game);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getGameById(
            int id
    ) {
        try {
            this.assertIfGameIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.game);
        } catch (SQLException e) {
            logger.info(e.getMessage());
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
            EscapeRoom escapeRoom,
            Player player
    ){
        try {

        this.assertIfGameIdNotFound(id);
        }catch (SQLException e){
            logger.info(e.getMessage());
        }

        Game game = this.castToGame(entity);
        game.setEscapeRoom(escapeRoom);
        game.setPlayers(player);

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
}
