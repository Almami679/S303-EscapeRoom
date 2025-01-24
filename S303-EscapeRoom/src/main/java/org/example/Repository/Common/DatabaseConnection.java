package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;

import java.sql.*;

public class DatabaseConnection {
    private String URL;
    private String USER = "root";
    private String PASSWORD;
    private static final int MAX_CONNECTION_OPTIONS = 4;

    Logger logger = LogManager.getLogger(DatabaseConnection.class);

    void setConnectionOption(int connectionOption) {
        switch (connectionOption) {
            case 0://Albert
                URL = "jdbc:mysql://localhost:3306/escaperoomdb";
                PASSWORD = "Chefwork135731";
                break;
            case 1://Marc
                URL = "jdbc:mysql://127.0.0.2:3306/escaperoomdb";
                PASSWORD = "mbernar910";
                break;
            case 2://Inga
                URL = "jdbc:mysql://localhost:3306/escaperoomdb";
                PASSWORD = "password";
                break;
            case 3://Pau
                URL = "jdbc:mysql://127.0.0.1:3306/escaperoomdb";
                PASSWORD = "";
                break;
        }
    }

    public Connection dbConnect() {
        Connection connection = null;
        for (int i = 0; i < MAX_CONNECTION_OPTIONS; i++) {
            setConnectionOption(i);
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.info("Connection established successfully");
                i=MAX_CONNECTION_OPTIONS;
            } catch (SQLException e) {
                logger.error("Connection failed , trying with another connection.", e);
            }
        }
        if (connection == null) {
            throw new DatabaseConnectionFailed("All connection attempts failed.");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            logger.info("Connection closed.");
        } catch (SQLException e) {
            logger.error("Failed to close connection: ", e);
        }
    }

    // EscapeRoomTEST
    /*public void addEscapeRoom(EscapeRoomTEST escapeRoomTEST) {
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

    //ObjectDecoTEST
    public void addObjectDeco(ObjectDecoTEST objectDecoTEST) {
        if (!objectDecoRepository.isDuplicateObjectDeco(objectDecoTEST.getName())) {
            objectDecoRepository.addObjectDeco(objectDecoTEST);
        } else {
            logger.warn("Duplicate ObjectDeco entry detected: " + objectDecoTEST.getName());
        }
    }

    public ObjectDecoTEST getObjectDecoById(int id) {
        return objectDecoRepository.getObjectDecoById(id);
    }

    public ArrayList<ObjectDecoTEST> getAllObjectDecos() {
        return objectDecoRepository.getAllObjectDecos();
    }

    public void updateObjectDeco(ObjectDecoTEST objectDecoTEST) {
        objectDecoRepository.updateObjectDeco(objectDecoTEST);
    }

    //TipsTEST
    public int getLatestTipsId() {
        int latestId = 0;
        String sql = "SELECT MAX(Tips_id) AS latestId FROM tips";
        try (Connection connection = dbConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                latestId = resultSet.getInt("latestId");
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch latest Tips ID: ", e);
        }
        return latestId;
    }

    public void addTips(TipsTEST tipsTEST) {
        if (!tipsRepository.isDuplicateTips(tipsTEST.getText())) {
            tipsRepository.addTips(tipsTEST);
        } else {
            logger.warn("Duplicate Tips entry detected: " + tipsTEST.getText());
        }
    }

    public TipsTEST getTipsById(int id) {
        return tipsRepository.getTipsById(id);
    }

    public ArrayList<TipsTEST> getAllTips() {
        return tipsRepository.getAllTips();
    }

    public void updateTips(TipsTEST tipsTEST) {
        tipsRepository.updateTips(tipsTEST);
    }


    //Ticket
    public void addSale(SaleTEST sale) {
        saleRepository.createSale(sale);
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.addTicket(ticket);
    }

    public void addGift(Gift gift) {
        giftRepository.addGift(gift);
    }

    public void addGame(GameTEST game) {
        gameRepository.addGame(game);
    }

     */
}


