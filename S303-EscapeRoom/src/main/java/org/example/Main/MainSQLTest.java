package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main.Services.GameServices.PlayerService;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;

public class MainSQLTest {
    static Logger logger = LogManager.getLogger(MainSQLTest.class);

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
                .build();
        RepositoryImpl repository = new RepositoryImpl();
        try {
            repository.add(escapeRoom1, EntityAttributes.escaperoom);
            System.out.println("Error al crear");
            logger.info("Escape room added successfully.");
        } catch (SQLException e) {
            logger.error("Failed to retrieve escape rooms: ", e);
        }
        /*EscapeRoom escapeRoom2 = null;
        try {
            escapeRoom2 = (EscapeRoom) repository.getById(3, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve escape room", e);
        }
        System.out.println(escapeRoom2);*/
    }
}