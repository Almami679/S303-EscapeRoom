package org.example.Main;

import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Repository.SqlEscapeRoomRepository;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MainSQLTest {
    public static void main(String[] args) {
        EscapeRoomTESTING();

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


}
