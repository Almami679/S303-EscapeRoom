package org.example.Main;

import org.example.Modules.CLASESTESTS.*;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Communicates.Gift;
import org.example.Modules.Communicates.Notification;
import org.example.Modules.Communicates.Ticket;
import org.example.Repository.DatabaseConnection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

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

    /////////////prueba ticket////////////
    public void createTicketTest(PlayerTEST player) {
        System.out.println("\n------------------------------\n" +
                "Ticket TEST:");
        SaleTEST sale1 = new SaleTEST(); //creando venta
        player.addSale(sale1); //asignando venta a player1
        Ticket ticket1 = (Ticket) mainFactoryCommunicate.createCommunicate(CommunicateType.TICKET, player);
        System.out.println(ticket1.getText() + "\n" + ticket1.getClass());
        ticket1.send();
    }

    //////////Prueba Gift/////////////////
    public void createGiftTest(PlayerTEST player) {
        System.out.println("\n------------------------------\n" +
                "Gift TEST");
        Gift gift1 = (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,player);
        System.out.println(gift1.getText() + "\n" + gift1.getClass());
        gift1.send();
    }

    ////////////Prueba notification/////////
    public void createNotificationTest() {
        System.out.println("\n------------------------------\n" +
                "Notification TEST");
        new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2())).forEach(player -> {
            Notification notification1 = (Notification) mainFactoryCommunicate.createCommunicate(CommunicateType.NOTIFICATION,player);
            System.out.println(notification1.getText() +
                    "\n" + notification1.getClass());
            notification1.send();
        });
    }

    //////////Prueba Certificate/////////////////
    public void createCertificateTest() {
        System.out.println("\n------------------------------\n" +
                "Certificate TEST");
        GameTEST game = new GameTEST("SpaceDream",
                new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2())));
        game.finishGame();
    }
}