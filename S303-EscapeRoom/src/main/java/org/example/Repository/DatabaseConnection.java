package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;
import org.example.Modules.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.CLASESTESTS.RoomTEST;
import org.example.Modules.CLASESTESTS.PlayerTEST;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private final static String URL = "jdbc:mysql://127.0.0.2:3306/escaperoomdb";
    private final static String USER = "root";
    private final static String PASSWORD = "mbernar910";

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
        if (!escapeRoomRepository.isDuplicateEscapeRoom(escapeRoomTEST.getName())) {
            escapeRoomRepository.addEscapeRoom(escapeRoomTEST);
        } else {
            logger.warn("Duplicate EscapeRoom entry detected: " + escapeRoomTEST.getName());
        }
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
    public int getLatestEscapeRoomId() {
        int latestId = 0;
        String sql = "SELECT MAX(EscapeRoom_id) AS latestId FROM escaperoom";
        try (Connection connection = dbConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                latestId = resultSet.getInt("latestId");
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch latest EscapeRoom ID: ", e);
        }
        return latestId;
    }

    // RoomTEST
    public void addRoom(RoomTEST roomTEST) {
        if (!roomRepository.isDuplicateRoom(roomTEST.getName())) {
            roomRepository.addRoom(roomTEST);
        } else {
            logger.warn("Duplicate Room entry detected: " + roomTEST.getName());
        }
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
    public int getLatestRoomId() {
        int latestId = 0;
        String sql = "SELECT MAX(Room_id) AS latestId FROM room";
        try (Connection connection = dbConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                latestId = resultSet.getInt("latestId");
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch latest Room ID: ", e);
        }
        return latestId;
    }

    // PlayerTEST
    public void createPlayer(PlayerTEST playerTEST) {
        if (!playerRepository.isDuplicatePlayer(playerTEST.getName())) {
            playerRepository.createPlayer(playerTEST);
        } else {
            logger.warn("Duplicate Player entry detected: " + playerTEST.getName());
        }
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
    public int getLatestPlayerId() {
        int latestId = 0;
        String sql = "SELECT MAX(Player_id) AS latestId FROM player";
        try (Connection connection = dbConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                latestId = resultSet.getInt("latestId");
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch latest Player ID: ", e);
        }
        return latestId;
    }


}