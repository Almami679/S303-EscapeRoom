package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Main.MainSQLTest.*;

public class MainSQLCommTestsAlbert {

    ///////////// Prueba ticket + Logger ////////////
    private static SaleTEST createSale(){
        return new SaleTEST(
                new Timestamp(System.currentTimeMillis()),
                800,
                1,
                0);
    }

    private static Ticket createTicket(PlayerTEST player){
        return (Ticket) mainFactoryCommunicate.createCommunicate(
                CommunicateType.TICKET,
                player);
    }
    public static void logicTicketTest(PlayerTEST player) {
        SaleTEST sale1 = createSale();
        db.addSale(sale1);
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() + "] created.");
        player.addSale(sale1);
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() +
                "] assigned to PlayerId: " + player.getId());
        Ticket ticket1 = createTicket(player);
        db.addTicket(ticket1);
        LogManager.getLogger(Ticket.class).info(ticket1.getText());
        ticket1.send();
    }

    ////////// Prueba Gift + Logger /////////////////
    private static Gift createGift(PlayerTEST player){
        return (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,
                player);
    }

    private static void logicGame() {
        GameTEST game = createGame();
        db.addGame(game);
        LogManager.getLogger(GameTEST.class).info("GameId:" + game.getId() +
                " created.");
        game.finishGame();
    }

    public static void logicGiftTest(PlayerTEST player) {
        logicGame();
        Gift gift1 = createGift(player);
        db.addGift(gift1);
        LogManager.getLogger(Gift.class).info("GiftId:" + gift1.getId() +
                " created with text:\n" + gift1.getText());
        gift1.send();

    }

    private static GameTEST createGame(){
        ArrayList<PlayerTEST> playersInGame = new ArrayList<>();
        playersInGame.add(createPlayerTEST1());
        playersInGame.add(createPlayerTEST2());
        return new GameTEST(
                createEscapeRoomTEST1(),
                new Timestamp(System.currentTimeMillis()),
                playersInGame,
                0);
    }


    //////////// Prueba notification + Logger /////////

    private static Notification createNotification(PlayerTEST player) {
        return (Notification) mainFactoryCommunicate.createCommunicate(
                CommunicateType.NOTIFICATION,
                player);
    }

    private static ArrayList<PlayerTEST> createListPlayers(){
        return new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2()));
    }
    public static void logicNotificationTest() {
        ArrayList<PlayerTEST> playersTEST = createListPlayers();
        playersTEST.forEach(player -> {
            Notification notification1 = createNotification(player);
            LogManager.getLogger(Notification.class).info("NotificationId: " + notification1.getId() +
                    " created with text:\n" + notification1.getText());
            notification1.send();
        });
    }

    ///ARREGLANDO LA PARTE DE CREAR CERTIFICADOS CON PLAYERS PARA QUE NO SE DUPLIQUE A SACO,
    /// ir a la clase GameTEST, ahi esta la logica
    //////////Prueba Certificate/////////////////
    private static Certificate createCertificateTest(PlayerTEST player) {
        return (Certificate) mainFactoryCommunicate.createCommunicate(
                CommunicateType.CERTIFICATE,
                player);
    }

    public static void logicCertificate() {
        GameTEST game = createGame();
        if(game.checkGameStatus()) {
            game.getPlayers().forEach(player-> {
                createCertificateTest(player);
                LogManager.getLogger(Certificate.class).info("Certificate for player " +
                        player.getName() +
                        "created");
            });
        } else {
            LogManager.getLogger(Certificate.class).warn("Game id: " +
                    game.getId() + " finish: " +
                    game.checkGameStatus() +
                    "\nCertificate not created.");
        }

    }







}

