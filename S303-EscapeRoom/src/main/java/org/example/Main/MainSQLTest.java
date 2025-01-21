package org.example.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
           for (Entity entity : entities) {
               if (entity instanceof EscapeRoom) {
                   escapeRooms.add((EscapeRoom) entity);
               }
           }
           for (EscapeRoom escapeRoom : escapeRooms) {
               System.out.println(escapeRoom);
           }
       } catch (SQLException e) {
           logger.error("Failed to retrieve escape rooms: ", e);
       }
    }
}