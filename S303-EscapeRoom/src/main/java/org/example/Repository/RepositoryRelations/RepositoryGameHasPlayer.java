package org.example.Repository.RepositoryRelations;

import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryGameHasPlayer extends RepositoryImpl {
    public RepositoryGameHasPlayer() {
        super();
    }

    public void addGameHasPlayer(int gameId, int playerId) throws SQLException {
        String query = "INSERT INTO escaperoomdb.game_has_player (game_game_id, player_player_id) VALUES (?, ?);";
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(gameId));
        values.add(String.valueOf(playerId));
        Serializer.serialize(query, EntityAttributes.game_has_player, "add", values);
    }
}
