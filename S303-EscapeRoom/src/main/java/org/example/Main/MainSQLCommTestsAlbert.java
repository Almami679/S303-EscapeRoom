package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.example.Modules.CLASESTESTS.GameTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;
import org.example.Modules.CLASESTESTS.SaleTEST;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Communicates.Gift;
import org.example.Modules.Communicates.Notification;
import org.example.Modules.Communicates.Ticket;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Main.MainSQLTest.*;
import static org.example.Repository.SqlGameRepository.getGameById;
import static org.example.Repository.SqlPlayerRepository.getPlayerById;
import static org.example.Repository.SqlSaleRepository.getSaleById;

public class MainSQLCommTestsAlbert {

    ///////////// Prueba ticket + Logger ////////////

    private static Ticket createTicket(int idPlayer){
        return (Ticket) mainFactoryCommunicate.createCommunicate(
                CommunicateType.TICKET,
                idPlayer);
    }
    public static void logicTicketTest(int idPlayer) {
        SaleTEST sale1 = getSaleById(1);
        PlayerTEST player = getPlayerById(1);
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() + "] created.");
        player.addSale(sale1);
        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() +
                "] assigned to PlayerId: " + player.getId());
        Ticket ticket1 = createTicket(player.getId());
        db.addTicket(ticket1);
        LogManager.getLogger(Ticket.class).info(ticket1.getText());
        ticket1.send();
    }

    ////////// Prueba Gift + Logger /////////////////
    private static Gift createGift(int idPlayer){
        return (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,
                idPlayer);
    }

    private static GameTEST logicGame() {
        GameTEST game = getGameById(1);
        LogManager.getLogger(GameTEST.class).info("GameId:" + game.getId() +
                " created.");
        game.finishGame();
    }

    public static void logicGiftTest(int idPlayer) {
        GameTEST game1 = logicGame();
        Gift gift1 = createGift(idPlayer);
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

    private static Notification createNotification(int idPlayer) {
        return (Notification) mainFactoryCommunicate.createCommunicate(
                CommunicateType.NOTIFICATION,
                idPlayer);
    }

    private static ArrayList<PlayerTEST> createListPlayers(){
        return new ArrayList<Integer>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2()));
    }
    public static void logicNotificationTest() {
        ArrayList<Integer> idPlayers = createListPlayers();
        idPlayers.forEach(playerId -> {
            Notification notification1 = createNotification(playerId);
            LogManager.getLogger(Notification.class).info("NotificationId: " + notification1.getId() +
                    " created with text:\n" + notification1.getText());
            notification1.send();
        });
    }
/*
    ///ARREGLANDO LA PARTE DE CREAR CERTIFICADOS CON PLAYERS PARA QUE NO SE DUPLIQUE A SACO,
    /// ir a la clase GameTEST, ahi esta la logica
    //////////Prueba Certificate/////////////////
    public void createCertificateTest() {
        GameTEST game = new GameTEST("SpaceDream",
                new ArrayList<PlayerTEST>(Arrays.asList(createPlayerTEST1(), createPlayerTEST2())));
        game.finishGame().send();
        LogManager.getLogger(Certificate.class).info(certificate.getText());
    }



 */
}

