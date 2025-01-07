package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.CLASESTESTS.RoomTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {
    private final static String URL = "jdbc:mysql://127.0.0.2:3306/mydb";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    Logger logger = LogManager.getLogger(DatabaseConnection.class);

    private SqlEscapeRoomRepository escapeRoomRepository;
    private SqlRoomRepository roomRepository;
    private SqlPlayerRepository playerRepository;

    public DatabaseConnection() {
        this.escapeRoomRepository = new SqlEscapeRoomRepository(this);
        this.roomRepository = new SqlRoomRepository(this);
        this.playerRepository = new SqlPlayerRepository(this);
    }

    public Connection dbConnect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Connection established successfully.");
        } catch (SQLException e) {
            logger.error("Connection failed.", e);
            throw new DatabaseConnectionFailed(e.getMessage());
        }
        return connection;
    }

    // EscapeRoomTEST
    public void addEscapeRoom(EscapeRoomTEST escapeRoomTEST) {
        escapeRoomRepository.addEscapeRoom(escapeRoomTEST);
    }

    public EscapeRoomTEST getEscapeRoomById(int id) {
        return escapeRoomRepository.getEscapeRoomById(id);
    }

    public ArrayList<EscapeRoomTEST> getAllEscapeRooms() {
        return escapeRoomRepository.getAllEscapeRooms();
    }

    public void escapeRoomUpdate(EscapeRoomTEST escapeRoomTEST) {
        escapeRoomRepository.escapeRoomUpdate(escapeRoomTEST);
    }

    // RoomTEST
    public void addRoom(RoomTEST roomTEST) {
        roomRepository.addRoom(roomTEST);
    }

    public RoomTEST getRoomById(int id) {
        return roomRepository.getRoomById(id);
    }

    public ArrayList<RoomTEST> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public void roomUpdate(RoomTEST roomTEST) {
        roomRepository.roomUpdate(roomTEST);
    }

    // PlayerTEST
    public void createPlayer(PlayerTEST playerTEST) {
        playerRepository.createPlayer(playerTEST);
    }

    public PlayerTEST getPlayerById(int id) {
        return playerRepository.getPlayerById(id);
    }

    public ArrayList<PlayerTEST> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    public void updatePlayer(PlayerTEST playerTEST) {
        playerRepository.updatePlayer(playerTEST);
    }
}