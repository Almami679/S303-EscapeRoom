//package org.example.Main;
//
//import org.apache.logging.log4j.LogManager;
//import org.example.Modules.Entities.CLASESTESTS.GameTEST;
//import org.example.Modules.Entities.CLASESTESTS.PlayerTEST;
//import org.example.Modules.Entities.GameEntities.SaleTEST;
//import org.example.Modules.Communicates.CommunicateType;
//import org.example.Modules.Entities.CommunicatesEntities.Gift;
//import org.example.Modules.Entities.CommunicatesEntities.Notification;
//import org.example.Modules.Entities.CommunicatesEntities.Ticket;
//
//import static org.example.Main.MainSQLTest.*;
//import static org.example.Repository.Old.SqlGameRepository.getGameById;
//import static org.example.Repository.Old.SqlPlayerRepository.getPlayerById;
//import static org.example.Repository.Old.SqlSaleRepository.getSaleById;
//
//public class MainSQLCommTestsAlbert {
//
//    ///////////// Prueba ticket + Logger ////////////
//
//    private static Ticket createTicket(int idPlayer){
//        return (Ticket) mainFactoryCommunicate.createCommunicate(
//                CommunicateType.TICKET,
//                idPlayer);
//    }
//    public static void logicTicketTest(int idPlayer) {
//        SaleTEST sale1 = getSaleById(1);
//        PlayerTEST player = getPlayerById(1);
//        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() + "] created.");
//        player.addSale(sale1);
//        LogManager.getLogger(SaleTEST.class).info("Sale [id:" + sale1.getId() +
//                "] assigned to PlayerId: " + player.getId());
//        Ticket ticket1 = createTicket(player.getId());
//        //db.addTicket(ticket1);
//        LogManager.getLogger(Ticket.class).info(ticket1.getText());
//        ticket1.send();
//    }
//
//    ////////// Prueba Gift + Logger /////////////////
//    private static Gift createGift(int idPlayer){
//        return (Gift) mainFactoryCommunicate.createCommunicate(CommunicateType.GIFT,
//                idPlayer);
//    }
//
//    private static GameTEST logicGame() {
//        GameTEST game = getGameById(1);
//        LogManager.getLogger(GameTEST.class).info("GameId:" + game.getId() +
//                " created.");
//        game.finishGame();
//        return game;
//    }
//
//    public static void logicGiftTest(int idPlayer) {
//        GameTEST game1 = logicGame();
//        Gift gift1 = createGift(idPlayer);
//        //db.addGift(gift1);
//        LogManager.getLogger(Gift.class).info("GiftId:" + gift1.getId() +
//                " created with text:\n" + gift1.getText());
//        gift1.send();
//
//    }
//
//    /*
//    private static GameTEST createGame(){
//        ArrayList<PlayerTEST> playersInGame = new ArrayList<>();
//        playersInGame.add(createPlayerTEST1());
//        playersInGame.add(createPlayerTEST2());
//        return new GameTEST(
//                createEscapeRoomTEST1(),
//                new Timestamp(System.currentTimeMillis()),
//                playersInGame,
//                0);
//    }
//
//     */
//
//
//    //////////// Prueba notification + Logger /////////
//
//    private static Notification createNotification(int idPlayer) {
//        return (Notification) mainFactoryCommunicate.createCommunicate(
//                CommunicateType.NOTIFICATION,
//                idPlayer);
//    }
//
//
//}
//
