
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
import org.example.Repository.Common.RepositoryImpl;
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

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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

        String lastPlayerInDb = playersInDb.get(playersInDb.size() - 1).getEmail();
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
    public void getCertificateDesdeRepo() throws SQLException {
        RepositoryImpl repo = new RepositoryImpl();
        Certificate certificate = (Certificate) repo.getById(2, EntityAttributes.certificate);
        System.out.println(certificate);
    }

    @Test
    public void getGift() { //Semi fallo en relaciones

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

    @Test
    public void addTipsInRoom() {
        RoomService roomService = new RoomService();
        TipService tipService = new TipService();
        ArrayList<Tips> tips = tipService.getAllTips();
        tips.forEach(tip -> roomService.addTipInRoom(tip.getId(), 1));
        ArrayList<Tips> tipsInRoomTest = roomService.getAllTipsInRoom(1);
        tipsInRoomTest.forEach(System.out::println);
        System.out.println("___________________________");
        tips.forEach(System.out::println);
    }

    @Test
    public void getAllTipsInRoom() {
        RoomService roomService = new RoomService();
        ArrayList<Tips> tipsTest = roomService.getAllTipsInRoom(1);
        tipsTest.forEach(System.out::println);
    }

    @Test
    public void addObjectsInRoom() {
        RoomService roomService = new RoomService();
        ObjectDecoService objectService = new ObjectDecoService();
        ArrayList<ObjectDeco> objects = objectService.getAllObjectDeco();
        objects.forEach(objectDeco -> roomService.addObjectInRoom(objectDeco.getId(), 1));
        ArrayList<ObjectDeco> objectsInRoomTest = roomService.getAllObjectsInRoom(1);
        objectsInRoomTest.forEach(System.out::println);
        System.out.println("___________________________");
        objects.forEach(System.out::println);
    }

    @Test
    public void getAllObjectsInRoom() {
        RoomService roomService = new RoomService();
        ArrayList<ObjectDeco> objectsTest = roomService.getAllObjectsInRoom(1);
        objectsTest.forEach(System.out::println);
    }

    @Test
    public void getAllGames(){
        GameService service = new GameService();
        System.out.println(service.getAllGame());
    }

    @Test
    public void addPlayerInGame() {
        GameService gameService = new GameService();
        PlayerService playerService = new PlayerService();
        ArrayList<Player> players = playerService.getAllPlayer();
        System.out.println(players);
        players.forEach(player -> gameService.addPlayerInGame(player.getId(), 1));
        ArrayList<Player> playersInGameTest = gameService.getAllPlayersInGame(1);
        playersInGameTest.forEach(System.out::println);
        System.out.println("___________________________");
        players.forEach(System.out::println);

        assertEquals(players, playersInGameTest);
    }

    @Test
    public void createTicketSale() {
        SaleService saleService = new SaleService();

        saleService.getTicketSale(1,2);

    }

}
