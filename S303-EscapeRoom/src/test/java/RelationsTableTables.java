import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class RelationsTableTables {

    @DisplayName("TEST ADD OBJECT IN ROOM")
    @Test
    public void testObjectsInRoom() {
        RoomService roomService = new RoomService();
        ObjectDecoService objectService = new ObjectDecoService();

        Room roomTest = roomService.getRoomById(1);
        System.out.println(roomTest);
        objectService.createObjectDeco("Caca de perro", "Plastic", 5);
        ObjectDeco objectTest = objectService.getObjectDecoById(5);
        System.out.println(objectTest);

        roomService.addObjectInRoom(roomTest.getId(), objectTest.getId());

        roomService.getAllObjectsInRoom(1).forEach(System.out::println);

    }
}
