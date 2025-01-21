package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity_services.PlayerService;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

public class MainSQLTest {
    static Logger logger = LogManager.getLogger(MainSQLTest.class);
    public static final DatabaseConnection db = new DatabaseConnection();
    public static CommunicateFactory mainFactoryCommunicate = new CommunicateFactory();



    public static void main(String[] args) {
        Repository repository = new RepositoryImpl();
        PlayerService playerService = new PlayerService(repository);
        playerService.createPlayer("inga", "example@email.com", 0);

        EscapeRoomTesting();
        /*RoomTesting();
        PlayerTesting();
        ObjectsDecoTesting();
        TipsTesting();
        logicTicketTest(createPlayerTEST1());
        logicGiftTest(createPlayerTEST2());
        //logicNotificationTest();
        //logicCertificate();*/


    }

    private static void EscapeRoomTesting() {
        EscapeRoom escapeRoom1 = new EscapeRoomBuilder()
                .setName("Pedos House")
                .setPrice(50.0)
                .setTheme("Smelly")
                .setDeleted(0)
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setUpdatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        RepositoryImpl repository = new RepositoryImpl();
        try {
            repository.add(escapeRoom1, EntityAttributes.escaperoom);
            System.out.println("Error al crear");
            logger.info("Escape room added successfully.");
        } catch (SQLException e) {
            logger.error("Failed to add escape room: ", e);
        }
    }

   /*
    private static void PlayerTesting() {
        PlayerTEST playerTEST1 = createPlayerTEST1();
        PlayerTEST playerTEST2 = createPlayerTEST2();
        db.createPlayer(playerTEST1);
        db.createPlayer(playerTEST2);
        PlayerTEST playerTEST1Updated = db.getPlayerById(1);
        playerTEST1Updated.setConsentNotif(1);
        db.updatePlayer(playerTEST1Updated);
        System.out.println("Player id[1]: " + db.getPlayerById(1));
        ArrayList<PlayerTEST> playerTESTS = db.getAllPlayers();
        playerTESTS.forEach(playerTEST -> System.out.println(playerTEST));
    }
    public static PlayerTEST createPlayerTEST1() {
        return new PlayerTEST(
                "Pepito Palotes",
                "pepito@palotes.com",
                1,
                0
        );
    }
    public static PlayerTEST createPlayerTEST2() {
        return new PlayerTEST(
                "Mojo Jojo",
                "mojo@jojo.com",
                0,
                0
        );
    }

    private static void ObjectsDecoTesting() {
        ObjectDecoTEST objectDecoTEST1 = createObjectDecoTEST1();
        ObjectDecoTEST objectDecoTEST2 = createObjectDecoTEST2();
        ObjectDecoTEST objectDecoTEST3 = createObjectDecoTEST3();
        db.addObjectDeco(objectDecoTEST1);
        db.addObjectDeco(objectDecoTEST2);
        db.addObjectDeco(objectDecoTEST3);
        ObjectDecoTEST objectDecoTEST1Updated = db.getObjectDecoById(1);
        objectDecoTEST1Updated.setPrice(15);
        objectDecoTEST1Updated.setUpdatedat();
        db.updateObjectDeco(objectDecoTEST1Updated);
        System.out.println("ObjectDeco id[1]: " + db.getObjectDecoById(1));
        ArrayList<ObjectDecoTEST> objectDecoTESTS = db.getAllObjectDecos();
        objectDecoTESTS.forEach(objectDecoTEST -> System.out.println(objectDecoTEST));
    }
    private static ObjectDecoTEST createObjectDecoTEST1() {
        return new ObjectDecoTEST(
                "Flashlight",
                "Plastic",
                1,
                12,
                0,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }

    private static ObjectDecoTEST createObjectDecoTEST2() {
        return new ObjectDecoTEST(
                "key",
                "Metallic",
                1,
                3,
                0,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
    private static ObjectDecoTEST createObjectDecoTEST3() {
        return new ObjectDecoTEST(
                "Candle",
                "Wax",
                2,
                5,
                0,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }

    private static void TipsTesting() {
        TipsTEST tipsTEST1 = createTipsTEST1();
        TipsTEST tipsTEST2 = createTipsTEST2();
        db.addTips(tipsTEST1);
        db.addTips(tipsTEST2);
        TipsTEST tipsTEST1Updated = db.getTipsById(1);
        tipsTEST1Updated.setText("Use the flashlight to reveal hidden messages");
        db.updateTips(tipsTEST1Updated);
        //System.out.println("Tips id[1]: " + db.getTipsById(1));
        ArrayList<TipsTEST> tipsTESTS = db.getAllTips();
        tipsTESTS.forEach(tipsTEST -> System.out.println(tipsTEST));
    }
    private static TipsTEST createTipsTEST1() {
        return new TipsTEST(
                "Use the asdasf to revawdaweal hiddasdan messages",
                1
        );
    }
    private static TipsTEST createTipsTEST2() {
        return new TipsTEST(
                "Use the key to open the door",
                1
        );


    }*/
}