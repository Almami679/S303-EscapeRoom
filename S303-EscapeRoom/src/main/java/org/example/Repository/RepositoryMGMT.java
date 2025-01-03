package org.example.Repository;
import java.sql.Connection;
import java.util.ArrayList;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.CLASESTESTS.RoomTEST;

public interface RepositoryMGMT {
    Connection dbConnect();

    ArrayList<EscapeRoomTEST> getAllEscapeRooms();
    void addEscapeRoom(EscapeRoomTEST escapeRoomTEST);
    EscapeRoomTEST getEscapeRoomById(int id);
    void escapeRoomUpdate(EscapeRoomTEST escapeRoomTEST);

    ArrayList<RoomTEST> getAllRooms();
    void addRoom(RoomTEST roomTEST);
    RoomTEST getRoomById(int id);
    void roomUpdate(RoomTEST roomTEST);

}
