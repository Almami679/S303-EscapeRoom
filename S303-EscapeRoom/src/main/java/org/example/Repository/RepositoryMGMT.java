package org.example.Repository;
import java.sql.Connection;
import java.util.ArrayList;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;

public interface RepositoryMGMT {
    Connection dbConnect();
    ArrayList<EscapeRoomTEST> getAllEscapeRooms();
    void addEscapeRoom(EscapeRoomTEST escapeRoomTEST);
    EscapeRoomTEST getEscapeRoomById(int id);
    void escapeRoomUpdate(EscapeRoomTEST escapeRoomTEST);
}
