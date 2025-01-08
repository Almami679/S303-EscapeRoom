package org.example.Main;

import com.mysql.cj.log.Log;
import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.*;
import org.example.Modules.Communicates.*;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Repository.DatabaseConnection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mysql.cj.conf.PropertyKey.logger;

public class MainSQLTest {
    private static final DatabaseConnection db = new DatabaseConnection();
    public static final CommunicateFactory mainFactoryCommunicate = new CommunicateFactory();

    public static void main(String[] args) {
        EscapeRoomTesting();
        RoomTesting();
        PlayerTesting();
    }

    private static void EscapeRoomTesting() {
        EscapeRoomTEST escapeRoomTEST1 = createEscapeRoomTEST1();
        EscapeRoomTEST escapeRoomTEST2 = createEscapeRoomTEST2();
        db.addEscapeRoom(escapeRoomTEST1);
        db.addEscapeRoom(escapeRoomTEST2);
        updateEscapeRoomTest(escapeRoomTEST1);
        db.escapeRoomUpdate(escapeRoomTEST1);
        System.out.println("EscapeRoom id[1]: " + db.getEscapeRoomById(1));
        ArrayList<EscapeRoomTEST> escapeRoomTESTS = db.getAllEscapeRooms();
        escapeRoomTESTS.forEach(System.out::println);
    }
    private static EscapeRoomTEST createEscapeRoomTEST1() {
        return new EscapeRoomTEST(
                "Mystery",
                150.0,
                "Mystery",
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
    private static EscapeRoomTEST createEscapeRoomTEST2() {
        return new EscapeRoomTEST(
                "Terror",
                250.0,
                "Terror",
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
    private static void updateEscapeRoomTest(EscapeRoomTEST escapeRoomTEST) {
        escapeRoomTEST.setPrice(200.0);
        Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
        escapeRoomTEST.setUpdated_at(updateTimestamp);
    }

    private static void RoomTesting() {
        RoomTEST roomTEST1 = createRoomTEST1();
        RoomTEST roomTEST2 = createRoomTEST2();
        db.addRoom(roomTEST1);
        db.addRoom(roomTEST2);
        updateRoomTest(roomTEST1);
        db.roomUpdate(roomTEST1);
        System.out.println("Room id[1]: " + db.getRoomById(1));
        ArrayList<RoomTEST> roomTESTS = db.getAllRooms();
        roomTESTS.forEach(System.out::println);
    }
    private static RoomTEST createRoomTEST1() {
        return new RoomTEST(
                "Maze",
                "Medium",
                200.0,
                1,
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
    private static RoomTEST createRoomTEST2() {
        return new RoomTEST(
                "Hotel",
                "Hard",
                350.0,
                2,
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
    private static void updateRoomTest(RoomTEST roomTEST) {
        roomTEST.setPrice(300.0);
        Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
        roomTEST.setUpdated_at(updateTimestamp);
    }

    private static void PlayerTesting() {
        PlayerTEST playerTEST1 = createPlayerTEST1();
        PlayerTEST playerTEST2 = createPlayerTEST2();
        db.createPlayer(playerTEST1);
        db.createPlayer(playerTEST2);
        updatePlayerTest(playerTEST1);
        db.updatePlayer(playerTEST1);
        System.out.println("Player id[1]: " + db.getPlayerById(1));
        ArrayList<PlayerTEST> playerTESTS = db.getAllPlayers();
        playerTESTS.forEach(System.out::println);
    }
    private static PlayerTEST createPlayerTEST1() {
        return new PlayerTEST(
                "Pepito Palotes",
                "pepito@palotes.com",
                true,
                false
        );
    }
    private static PlayerTEST createPlayerTEST2() {
        return new PlayerTEST(
                "Mojo Jojo",
                "mojo@jojo.com",
                false,
                false
        );
    }
    private static void updatePlayerTest(PlayerTEST playerTEST) {

        playerTEST.setConsentNotif(!playerTEST.getConsentNotif());
    }

    ///////////// Prueba ticket + Logger ////////////
    public void createTicketTest(PlayerTEST player) {
        SaleTEST sale1 = new SaleTEST();
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() + "] created.");
        player.addSale(sale1);
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() +
                "] assigned to PlayerId: " + player.getId());
        Ticket ticket1 = (Ticket) mainFactoryCommunicate.createCommunicate(CommunicateType.TICKET, player);
        LogManager.getLogger(Ticket.class).info(ticket1.getText());
        ticket1.send();
    }

    ////////// Prueba Gift + Logger /////////////////
    public void createGiftTest(PlayerTEST player) {
        Gift gift1 = (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,player);
        LogManager.getLogger(Gift.class).info("GiftId:" + gift1.getId() +
                " created with text:\n" + gift1.getText());
        gift1.send();
    }

    //////////// Prueba notification + Logger /////////
    public void createNotificationTest() {
        new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2())).forEach(player -> {
            Notification notification1 = (Notification) mainFactoryCommunicate.createCommunicate(CommunicateType.NOTIFICATION,player);
            LogManager.getLogger(Notification.class).info("NotificationId: " + notification1.getId() +
                    " created with text:\n" + notification1.getText());
            notification1.send();
        });
    }

    ///ARREGLANDO LA PARTE DE CREAR CERTIFICADOS CON PLAYERS PARA QUE NO SE DUPLIQUE A SACO,
    /// ir a la clase GameTEST, ahi esta la logica
    //////////Prueba Certificate/////////////////
    public void createCertificateTest() {
        GameTEST game = new GameTEST("SpaceDream",
                new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2())));
        game.finishGame().send();
        LogManager.getLogger(Certificate.class).info(certificate.getText());
    }
}