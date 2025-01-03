package org.example.Main;

import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.CLASESTESTS.RoomTEST;
import org.example.Repository.SqlEscapeRoomRepository;
import org.example.Repository.SqlRoomRepository;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MainSQLTest {
    public static void main(String[] args) {
        EscapeRoomTESTING();
        RoomTESTING();

    }

    private static void EscapeRoomTESTING() {
        SqlEscapeRoomRepository repository = new SqlEscapeRoomRepository();
        ArrayList<EscapeRoomTEST> escapeRoomTESTS;
        EscapeRoomTEST escapeRoomTEST1 = createEscapeRoomTEST1();
        EscapeRoomTEST escapeRoomTEST2 = createEscapeRoomTEST2();
        repository.addEscapeRoom(escapeRoomTEST1);
        repository.addEscapeRoom(escapeRoomTEST2);
        updateEscapeRoomTest(escapeRoomTEST1);
        repository.escapeRoomUpdate(escapeRoomTEST1);
        System.out.println("EscapeRoom id[1]: "+repository.getEscapeRoomById(1));
        escapeRoomTESTS = repository.getAllEscapeRooms();
        escapeRoomTESTS.forEach(escapeRoomTEST -> System.out.println(escapeRoomTEST));

    }
    private static void updateEscapeRoomTest(EscapeRoomTEST escapeRoomTEST) {
        escapeRoomTEST.setPrice(200.0);
        Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
        escapeRoomTEST.setUpdated_at(updateTimestamp);
    }
    private static EscapeRoomTEST createEscapeRoomTEST1() {
        EscapeRoomTEST escapeRoomTEST = new EscapeRoomTEST(
                "Mystery Room",
                150.0,
                "Mystery",
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        return escapeRoomTEST;
    }
    private static EscapeRoomTEST createEscapeRoomTEST2() {
        EscapeRoomTEST escapeRoomTEST = new EscapeRoomTEST(
                "Terror Room",
                250.0,
                "Terror",
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        return escapeRoomTEST;
    }


    private static void RoomTESTING() {
        SqlRoomRepository repository = new SqlRoomRepository();
        ArrayList<RoomTEST> roomTESTS;
        RoomTEST roomTEST1 = createRoomTEST1();
        RoomTEST roomTEST2 = createRoomTEST2();
        repository.addRoom(roomTEST1);
        repository.addRoom(roomTEST2);
        updateRoomTest(roomTEST1);
        repository.roomUpdate(roomTEST1);
        System.out.println("Room id[1]: " + repository.getRoomById(1));
        roomTESTS = repository.getAllRooms();
        roomTESTS.forEach(roomTEST -> System.out.println(roomTEST));
    }
    private static void updateRoomTest(RoomTEST roomTEST) {
        roomTEST.setPrice(300.0);
        Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
        roomTEST.setUpdated_at(updateTimestamp);
    }
    private static RoomTEST createRoomTEST1() {
        return new RoomTEST(
                "Maze Room",
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
                "Hotel Room",
                "Hard",
                350.0,
                2,
                false,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }
}
