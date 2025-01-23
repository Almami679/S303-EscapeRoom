package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.*;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Services.GameServices.PlayerService;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainSQLTest {
    static Logger logger = LogManager.getLogger(MainSQLTest.class);

    public static void main(String[] args) {
        /*Repository repository = new RepositoryImpl();
        PlayerService playerService = new PlayerService();
        playerService.createPlayer("inga", "example@email.com", 0);*/

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
        RepositoryImpl repository = new RepositoryImpl();
        /*EscapeRoom escapeRoom1 = new EscapeRoomBuilder()
                .setName("Pedos House")
                .setPrice(50.0)
                .setTheme("Smelly")
                .build();
        try {
            repository.add(escapeRoom1, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            logger.error("Failed to add escape room, Duplicated entry", e);
        }*/
        /*try {
            ArrayList<Entity> entities = repository.getAll(EntityAttributes.escaperoom);
            ArrayList<EscapeRoom> escapeRooms = new ArrayList<>();
            for(int i = 0; i< entities.size(); i++){
                escapeRooms.add((EscapeRoom) entities.get(i));
                System.out.println(escapeRooms.get(i));
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve escape rooms: ", e);
        }*/
        /*EscapeRoom escapeRoom2 = null;
        try {
            escapeRoom2 = (EscapeRoom) repository.getById(3, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve escape room", e);
        }
        System.out.println(escapeRoom2);*/
        /*RepositoryEscapeHasRoom repositoryEscapeHasRoom = new RepositoryEscapeHasRoom();
        try {
            repositoryEscapeHasRoom.addEscapeRoomHasRoom(1, 2);
        } catch (SQLException e) {
            logger.error("Failed to add escape room and room relationship: ", e);
        }*/
    }
}