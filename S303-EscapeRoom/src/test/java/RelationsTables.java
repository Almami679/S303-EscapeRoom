// RelationsTables.java
import org.assertj.core.api.Assertions;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Repository.RepositoryRelations.RepositoryGameHasPlayer;
import org.example.Repository.RepositoryRelations.RepositoryRoomHasTips;
import org.example.Repository.RepositoryRelations.RepositoryRoomHasObjectDeco;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class RelationsTables {

    @Test
    public void testAddEscapeRoomHasRoom() {
        RepositoryEscapeHasRoom repositoryEscapeHasRoom = new RepositoryEscapeHasRoom();
        try {
            repositoryEscapeHasRoom.addEscapeRoomHasRoom(1, 2);
        } catch (SQLException e) {
            Assertions.fail("Failed to add escape room and room relationship", e);
        }
    }

    @Test
    public void testAddGameHasPlayer() {
        RepositoryGameHasPlayer repositoryGameHasPlayer = new RepositoryGameHasPlayer();
        try {
            repositoryGameHasPlayer.addGameHasPlayer(1, 2);
        } catch (SQLException e) {
            Assertions.fail("Failed to add game and player relationship", e);
        }
    }

    @Test
    public void testAddRoomHasTips() {
        RepositoryRoomHasTips repositoryRoomHasTips = new RepositoryRoomHasTips();
        try {
            repositoryRoomHasTips.addRoomHasTips(1, 2);
        } catch (SQLException e) {
            Assertions.fail("Failed to add room and tips relationship", e);
        }
    }

    @Test
    public void testAddRoomHasObjectDeco() {
        RepositoryRoomHasObjectDeco repositoryRoomHasObjectDeco = new RepositoryRoomHasObjectDeco();
        try {
            repositoryRoomHasObjectDeco.addRoomHasObjectDeco(1, 2);
        } catch (SQLException e) {
            Assertions.fail("Failed to add room and object deco relationship", e);
        }
    }
}