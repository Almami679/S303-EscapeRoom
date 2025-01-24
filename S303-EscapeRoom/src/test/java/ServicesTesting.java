import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.CommunicatesEntities.Ticket;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.EntityAttributes;
import org.example.Services.CommunicatesServices.CertificateService;
import org.example.Services.CommunicatesServices.GiftService;
import org.example.Services.CommunicatesServices.NotificationService;
import org.example.Services.CommunicatesServices.TicketService;
import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Services.EscapeRoomServices.TipService;
import org.example.Services.GameServices.GameService;
import org.example.Services.GameServices.PlayerService;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Services.GameServices.SaleService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Timestamp;
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

    @DisplayName("TEST GET ENTITIES")
    @Test

    public void getPlayer() {
        PlayerService playerService = new PlayerService();
        Player playerFound = playerService.getPlayerById(5);
        System.out.println(playerFound);
    }


    @Test
    public void getCertificate() {
        CertificateService commService = new CertificateService();
        Certificate certificate = commService.getCertificateById(1);
        System.out.println(certificate);
    }

    @Test
    public void getGift() { //Fallo con Timestamp
        GiftService commService = new GiftService();
        Gift gift = commService.getGiftById(1);
        System.out.println(gift);
    }

    @Test
    public void getNotification() {
        NotificationService commService = new NotificationService();
        Notification notification = commService.getNotificationById(1);
        System.out.println(notification);
    }

    @Test
    public void getTicket() {
        TicketService commService = new TicketService();
        Ticket ticket = commService.getTicketById(1);
        System.out.println(ticket);
    }

    @Test
    public void getEscapeRoom() {
        EscapeRoomService commService = new EscapeRoomService();
        EscapeRoom escaperoom = commService.getEscapeRoomById(1);
        System.out.println(escaperoom);
    }

    @Test
    public void getObjectDeco() { // failed to deserialized
        ObjectDecoService commService = new ObjectDecoService();
        ObjectDeco objectDeco = commService.getObjectDecoById(1);
        System.out.println(objectDeco);
    }

    @Test
    public void getRoom() {
        RoomService roomService = new RoomService();
        Room roomTest = roomService.getRoomById(1);
        System.out.println(roomTest);
    }

    @Test
    public void getTip() {
        TipService commService = new TipService();
        Tips tip = commService.getTipById(1);
        System.out.println(tip);
    }

    @Test
    public void getGame() {
        GameService commService = new GameService();
        Game game = commService.getGameById(1);
        System.out.println(game);
    }

    @Test
    public void getSale() { // Error TimeStamp
        SaleService commService = new SaleService();
        Sale sale = commService.getSaleById(1);
        System.out.println(sale);
    }





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
