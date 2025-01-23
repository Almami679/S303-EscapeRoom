package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.Serializers.Serializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MainSQLTest {
    static Logger logger = LogManager.getLogger(MainSQLTest.class);

    public static void main(String[] args) {
        Repository repository = new RepositoryImpl();
        PlayerService playerService = new PlayerService();
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
        RepositoryImpl repository = new RepositoryImpl();
        try {
            ArrayList<Entity> entities = repository.getAll(EntityAttributes.escaperoom);
            ArrayList<EscapeRoom> escapeRooms = new ArrayList<>();
            for(int i = 0; i< entities.size(); i++){
                escapeRooms.add((EscapeRoom) entities.get(i));
                System.out.println(escapeRooms.get(i));
            }
            /*for (EscapeRoom escapeRoom : escapeRooms) {
                System.out.println(escapeRoom);
            }*/
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
        RepositoryEscapeHasRoom repositoryEscapeHasRoom = new RepositoryEscapeHasRoom();
        try {
            repositoryEscapeHasRoom.addEscapeRoomHasRoom(1, 2);
        } catch (SQLException e) {
            logger.error("Failed to add escape room and room relationship: ", e);
        }
    }
}