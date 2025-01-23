import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Services.GameServices.PlayerService;
import org.example.Modules.Entities.GameEntities.Player;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ServicesTesting {

    @DisplayName("TEST CREATE NEW PLAYER FROM PlayerService")
    @Test
    public void testNewPlayerFromPlayerService(){
        PlayerService playerService = new PlayerService();
        Player playerTest = new Player ("Juan", "juanito@gmail.com", 0);

        playerService.createPlayer("Juan", "juanito@gmail.com", 0);

        ArrayList<Player> playersInDb = new ArrayList<>();
        playersInDb = playerService.getAllPlayer();

        String lastPlayerInDb = playersInDb.getLast().getEmail();
        assertEquals("Player Found in Db", playerTest.getEmail(), lastPlayerInDb);


    }

    @DisplayName("TEST ADD OBJECT IN ROOM")
    @Test
    public void testObjectsInRoom() {
        RoomService roomService = new RoomService();
        ObjectDecoService objectService = new ObjectDecoService();

        Room roomTest = roomService.getRoomById(1);
        objectService.createObjectDeco("Caca de perro", "Plastic", 5);
        ObjectDeco objectTest = objectService.getObjectDecoById(5);

        roomService.addObjectInRoom(roomTest.getId(), objectTest.getId());

        String nameLastObject = roomService.getAllObjectsInRoom(1).getLast().getName();
        String nameExpected = "Caca de perro";

        assertEquals("Test OK", nameLastObject, nameExpected);




    }

}
