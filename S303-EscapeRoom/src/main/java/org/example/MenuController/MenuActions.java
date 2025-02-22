package org.example.MenuController;

import org.example.Exceptions.SaleIdNotFoundException;
import org.example.Exceptions.RoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Services.EscapeRoomServices.TipService;
import org.example.Services.GameServices.PlayerService;
import org.example.Services.GameServices.GameService;
import org.example.Services.GameServices.SaleService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuActions {
    private static final EscapeRoomService escapeRoomService = new EscapeRoomService();
    private static final SaleService salesService = new SaleService();
    private static final RoomService roomService = new RoomService();
    private static final TipService tipService = new TipService();
    private static final PlayerService playerService = new PlayerService();
    private static final ObjectDecoService objectDecoService = new ObjectDecoService();
    private static final GameService gameService = new GameService();


    public static void createEscapeRoom(Scanner read) {
        System.out.print("Enter the name of the escape room: ");
        String name = read.next();
        double price = 0;
        boolean validInput = false;
        do {
            System.out.print("Enter the price of the escape room: ");
            try {
                price = read.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                read.next();
            }
        } while (!validInput);
        System.out.print("Enter the theme of the escape room: ");
        String theme = read.next();
        escapeRoomService.createEscapeRoom(name, price, theme);
        System.out.println("Escape room created successfully!");
    }

    public static void displayEscapeRoom() {
        List<EscapeRoom> escapeRooms = escapeRoomService.getAllEscapeRooms();
        if (escapeRooms.isEmpty()) {
            System.out.println("No escape rooms found.");
        } else {
            escapeRooms.forEach(escapeRoom -> System.out.println(escapeRoom.toStringDisplay()));
        }
    }

    public static void addNewRoom(Scanner read, int selectedID) {
        System.out.print("Enter the name of the room: ");
        String name = read.next();
        System.out.print("Enter the difficulty of the room: ");
        String difficulty = read.next();
        double price = 0;
        boolean validInput = false;
        do {
            System.out.print("Enter the price of the room: ");
            try {
                price = read.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                read.next();
            }
        } while (!validInput);
        roomService.createRoom(name, price, difficulty);
        ArrayList<Room> rooms = roomService.getAllRoom();
        Room createdRoom = rooms.stream()
                .filter(room -> room.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (createdRoom != null) {
            int roomId = createdRoom.getId();
            escapeRoomService.addRoomToEscapeRoom(selectedID, roomId);
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Failed to create room.");
        }
    }

    public static void displayRooms(int selectedID) {
        ArrayList<Room> rooms = escapeRoomService.getRoomInEscapeRoom(selectedID);
        if (rooms.isEmpty()) {
            System.out.println("No rooms found for the selected escape room.");
        } else {
            for (Entity room : rooms) {
                System.out.println(room);
            }
        }
    }

    public static void updateRoom(Scanner read, int selectedEscapeRoomID) {
        ArrayList<Room> roomsInSelectedER = escapeRoomService.getRoomInEscapeRoom(selectedEscapeRoomID);
        if (roomsInSelectedER.isEmpty()) {
            System.out.println("No rooms found for the selected escape room.");
        } else {
            System.out.println("Rooms available:");
            for (int i = 0; i < roomsInSelectedER.size(); i++) {
                System.out.println((i + 1) + ". " + roomsInSelectedER.get(i));
            }
            int roomIndex = -1;
            do {
                System.out.print("Select the room to update: ");
                try {
                    roomIndex = read.nextInt() - 1;
                    if (roomIndex < 0 || roomIndex >= roomsInSelectedER.size()) {
                        System.out.println("Invalid selection. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    read.next();
                }
            } while (roomIndex < 0 || roomIndex >= roomsInSelectedER.size());
            Room selectedRoom = roomsInSelectedER.get(roomIndex);
            Room roomToUpdate = roomService.getRoomById(selectedRoom.getId());
            if (roomToUpdate == null) {
                System.out.println("Failed to retrieve the selected room from the database.");
            } else {
                System.out.print("Enter the new name of the room: ");
                String name = read.next();
                System.out.print("Enter the new difficulty of the room: ");
                String difficulty = read.next();
                double price = 0;
                boolean validInput = false;
                do {
                    System.out.print("Enter the new price of the room: ");
                    try {
                        price = read.nextDouble();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        read.next();
                    }
                } while (!validInput);
                try {
                    roomService.updateRoom(roomToUpdate.getId(), name, difficulty, price,roomToUpdate.getDeleted());
                    System.out.println("Room updated successfully!");
                } catch (RoomNotFoundException e) {
                    System.out.println("Failed to update room: Room not found.");
                } catch (SQLException e) {
                    System.out.println("Failed to update room: " + e.getMessage());
                }
            }
        }
    }

    public static void removeRoom(Scanner read, int selectedEscapeRoomID) {
        ArrayList<Room> roomsInSelectedER = escapeRoomService.getRoomInEscapeRoom(selectedEscapeRoomID);
        if (roomsInSelectedER.isEmpty()) {
            System.out.println("No rooms found for the selected escape room.");
        }else {
            System.out.println("Rooms available:");
            for (int i = 0; i < roomsInSelectedER.size(); i++) {
                System.out.println((i + 1) + ". " + roomsInSelectedER.get(i));
            }
            int roomIndex = -1;
            do {
                System.out.print("Select the room to delete (1-" + roomsInSelectedER.size() + "): ");
                try {
                    roomIndex = read.nextInt() - 1;
                    if (roomIndex < 0 || roomIndex >= roomsInSelectedER.size()) {
                        System.out.println("Invalid selection. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    read.next();
                }
            } while (roomIndex < 0 || roomIndex >= roomsInSelectedER.size());
            Room selectedRoom = roomsInSelectedER.get(roomIndex);
            try {
                roomService.updateRoom(selectedRoom.getId(), selectedRoom.getName(), selectedRoom.getDifficulty(), selectedRoom.getPrice(), 1);
                System.out.println("Room deleted successfully!");
            } catch (RoomNotFoundException e) {
                System.out.println("Failed to delete room: Room not found.");
            } catch (SQLException e) {
                System.out.println("Failed to delete room: " + e.getMessage());
            }
        }
    }

    public static void addNewTip(Scanner read, int selectedRoomID) {
        System.out.print("Enter the tip text: ");
        String tipText = read.next();
        tipService.createTip(tipText);
        ArrayList<Tips> tips = tipService.getAllTips();
        Tips createdTip = tips.stream()
                .filter(tip -> tip.getText().equals(tipText))
                .findFirst()
                .orElse(null);
        if (createdTip != null) {
            int tipId = createdTip.getId();
            roomService.addTipToRoom(tipId, selectedRoomID);
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Failed to create room.");
        }
    }

    public static void displayTips(int selectedRoomID) {
        ArrayList<Tips> tips = roomService.getTipsInRoom(selectedRoomID);
        if (tips.isEmpty()) {
            System.out.println("No tips found for the selected room.");
        } else {
            for (Tips tip : tips) {
                System.out.println(tip);
            }
        }
    }

    public static void removeTip(Scanner read, int selectedRoomID) {
        ArrayList<Tips> tipsInSelectedRoom = roomService.getTipsInRoom(selectedRoomID);
        if (tipsInSelectedRoom.isEmpty()) {
            System.out.println("No tips found for the selected room.");
        } else {
            System.out.println("Tips available:");
            for (int i = 0; i < tipsInSelectedRoom.size(); i++)
                System.out.println((i + 1) + ". " + tipsInSelectedRoom.get(i));
            int tipIndex = -1;
            do {
                System.out.print("Select the tip to delete (1-" + tipsInSelectedRoom.size() + "): ");
                try {
                    tipIndex = read.nextInt() - 1;
                    if (tipIndex < 0 || tipIndex >= tipsInSelectedRoom.size()) {
                        System.out.println("Invalid selection. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    read.next();
                }
            } while (tipIndex < 0 || tipIndex >= tipsInSelectedRoom.size());
            Tips selectedTip = tipsInSelectedRoom.get(tipIndex);
            tipService.deleteTip(selectedTip.getId());
            System.out.println("Tip deleted successfully!");
        }
    }

    public static void addObjectDeco(Scanner read) {
        System.out.print("Enter the name of the decoration object ");
        String name = read.next();
        System.out.println("Enter material of the decoration object ");
        String material = read.next();
        double price = 0;
        boolean validInput = false;
        do {
            System.out.println("Enter the price of decoration object");
            try {
                price = read.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                read.next();
            }
        } while (!validInput);

        objectDecoService.createObjectDeco(name, material, price);
        System.out.println("Decoration object created successfully!");

    }
    public static void addObjectToRoom(Scanner read) {
        List<Room> rooms = roomService.getAllRoom();
        List<ObjectDeco> objectDecos = objectDecoService.getAllObjectDeco();
        if (rooms.isEmpty()) {
            System.out.println("No room was found.");
        } else {
            rooms.forEach(room -> {
                System.out.println("Room ID: " + room.getId() + ", Name: " + room.getName());
            });

            System.out.print("Enter the ID of the room you want to add an object to: ");
            int roomId = read.nextInt();
            Room selectedRoom = rooms.stream().filter(r -> r.getId() == roomId).findFirst().orElse(null);

            if (selectedRoom == null) {
                System.out.println("Invalid room ID.");
                return;
            }

            objectDecos.forEach(objectDeco -> {
                System.out.println("Object ID: " + objectDeco.getId() + ", Name: " + objectDeco.getName());
            });

            System.out.print("Enter the ID of the object you want to add to the room: ");
            int objectId = read.nextInt();
            ObjectDeco selectedObject = objectDecos.stream().filter(o -> o.getId() == objectId).findFirst().orElse(null);

            if (selectedObject == null) {
                System.out.println("Invalid object ID.");
            } else {
                roomService.addObjectInRoom(selectedRoom.getId(), selectedObject.getId());
                System.out.println("Object added to the room successfully!");
            }
        }
    }

    public static void displayInventory(Scanner read) {
        List<ObjectDeco> objectDecos = objectDecoService.getAllObjectDeco();
        double totalPrice = 0;
        if (objectDecos.isEmpty()) {
            System.out.println("No decoration object was found.");
        } else {
            objectDecos.forEach(objectDeco -> System.out.println(objectDeco.toString()));
            for(ObjectDeco objectDeco : objectDecos){
                if(objectDeco.getDeleted() != 1){
                    totalPrice += objectDeco.getPrice();
                }
            }
                System.out.println("Decoration objects total price is: " + totalPrice);
        }

    }

    public static void removeObject(Scanner read) {
        List<ObjectDeco> objectDecos = objectDecoService.getAllObjectDeco();
        if (objectDecos.isEmpty()) {
            System.out.println("No decoration objects were found.");
        } else {
            System.out.println("Decoration objects available:");
            for (ObjectDeco objectDeco : objectDecos) {
                if (objectDeco.getDeleted() == 0) { // Mostrar solo jugadores no eliminados
                    System.out.println("ID: " + objectDeco.getId()
                            + ", Name: " + objectDeco.getName()
                            + ", Material: " + objectDeco.getMaterial()
                            + ", Price: " + objectDeco.getPrice()
                    );
                }
            }

            int objectDecoId = -1;
            boolean validId = false;

            do {
                System.out.print("Enter the ID of the decoration object to delete: ");
                try {
                    objectDecoId = read.nextInt();
                    ObjectDeco objectDecoToDelete = objectDecoService.getObjectDecoById(objectDecoId);

                    if (objectDecoToDelete != null && objectDecoToDelete.getDeleted() == 0) {
                        validId = true;

                        System.out.print("Are you sure you want to delete this decoration object? (yes/no): ");
                        String confirmation = read.next();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            objectDecoService.deleteObjectDeco(objectDecoId);
                            System.out.println("Decoration object deleted successfully!");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    } else if (objectDecoToDelete != null && objectDecoToDelete.getDeleted() == 1) {
                        System.out.println("Decoration object with this ID is already deleted.");
                    } else {
                        System.out.println("Invalid ID. Decoration object not found.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    read.next();
                }
            } while (!validId);
        }


    }

    public static void addNewGame(int escaperoomId, Scanner read) {
        ArrayList<Integer> playerIds = selectPlayers(read);
        Game game = new Game(escaperoomId);
        gameService.createGame(escaperoomId);
        int idGame = gameService.getLastGameId();
        playerIds.forEach(playerId -> {
            gameService.addPlayerInGame(idGame, playerId);
        });
        System.out.println("Game [id:"+ idGame +"] created with " + playerIds.size() + " players");


    }

    public static ArrayList<Integer> selectPlayers(Scanner read) {
        ArrayList<Integer> playerIds = new ArrayList<>();
        List<Player> players = playerService.getAllPlayer();
        boolean exit = false;

        if (players.isEmpty()) {
            System.out.println("No players found.");
        } else {
            do {
                System.out.println("Select one Player:\nPress [0] to finish selection.");
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + players.get(i).getName());
                }

                int selectedPlayer = -1;
                try {
                    System.out.print("Your choice: ");
                    selectedPlayer = read.nextInt();

                    if (selectedPlayer == 0) {
                        exit = true;
                    } else if (selectedPlayer > 0 && selectedPlayer <= players.size()) {
                        int playerId = players.get(selectedPlayer - 1).getId();
                        if (playerIds.contains(playerId)) {
                            System.out.println("Player already selected. Choose a different player.");
                        } else {
                            playerIds.add(playerId);
                            System.out.println("Player added.");
                        }
                    } else {
                        System.out.println("Invalid selection. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    read.next();
                }
            } while (!exit);
        }

        return playerIds;
    }

    public static void displayGames() {
        ArrayList<Game> games = gameService.getAllGame();
        if (games.isEmpty()) {
            System.out.println("No games found.");
        } else {
            //System.out.println("Rooms for Escape Room ID " + selectedID + ":");
            for (Entity game : games) {
                System.out.println(game);
            }
        }

    }

    public static void finishGame(Scanner read) {

    }

    public static void createPlayer(Scanner read) {
        System.out.print("Enter the name of the player ");
        String name = read.next();
        System.out.println("Enter the email of the player ");
        String email = read.next();
        int consentNotif = 0;
        boolean validInput = false;
        do {
            System.out.println("Enter 1 if you want receive notification otherwise enter 0");
            try {
                consentNotif = read.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                read.next();
            }
        } while (!validInput);

        playerService.createPlayer(name, email, consentNotif);
        //System.out.println("Player created successfully!");

    }

    public static void displayPlayers(Scanner read) {
        List<Player> players = playerService.getAllPlayer();
        if (players.isEmpty()) {
            System.out.println("No players was found.");
        } else {
            players.forEach(player -> System.out.println(player.toString()));
        }
    }

    public static void updatePlayer(Scanner read) {
        List<Player> players = playerService.getAllPlayer();
        if (players.isEmpty()) {
            System.out.println("No players were found.");
        } else {
            System.out.println("Players available:");
            for (Player player : players) {
                System.out.println("ID: " + player.getId()
                        + ", Name: " + player.getName()
                        + ", Email: " + player.getEmail()
                        + ", Consent Notification: " + player.getConsentNotif());
            }

            int playerId = -1;
            boolean validId = false;

            do {
                System.out.print("Enter the ID of the player to update: ");
                try {
                    playerId = read.nextInt();
                    Player playerToUpdate = playerService.getPlayerById(playerId);
                    if (playerToUpdate != null) {
                        validId = true; // ID válido
                        System.out.print("Enter the new name of the player: ");
                        String name = read.next();

                        System.out.print("Enter the email of the player: ");
                        String email = read.next();

                        int consentNotif = 0;
                        boolean validInput = false;

                        do {
                            System.out.print("Enter 1 if you want to receive notifications, otherwise enter 0: ");
                            try {
                                consentNotif = read.nextInt();
                                if (consentNotif == 0 || consentNotif == 1) {
                                    validInput = true;
                                } else {
                                    System.out.println("Invalid input. Please enter 1 or 0.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                read.next();
                            }
                        } while (!validInput);

                        playerService.updatePlayer(playerId, name, email, consentNotif);
                        System.out.println("Player updated successfully!");
                    } else {
                        System.out.println("Invalid ID. Player not found.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    read.next();
                }
            } while (!validId);
        }
    }

    public static void removePlayer(Scanner read) {
        List<Player> players = playerService.getAllPlayer();
        if (players.isEmpty()) {
            System.out.println("No players were found.");
        } else {
            System.out.println("Players available:");
            for (Player player : players) {
                if (player.getDeleted() == 0) {
                    System.out.println("ID: " + player.getId()
                            + ", Name: " + player.getName()
                            + ", Email: " + player.getEmail()
                    );
                }
            }

            int playerId = -1;
            boolean validId = false;

            do {
                System.out.print("Enter the ID of the player to delete: ");
                try {
                    playerId = read.nextInt();
                    Player playerToDelete = playerService.getPlayerById(playerId);

                    if (playerToDelete != null && playerToDelete.getDeleted() == 0) {
                        validId = true;

                        System.out.print("Are you sure you want to delete this player? (yes/no): ");
                        String confirmation = read.next();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            playerService.deletePlayer(playerId);
                            System.out.println("Player deleted successfully!");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    } else if (playerToDelete != null && playerToDelete.getDeleted() == 1) {
                        System.out.println("Player with this ID is already deleted.");
                    } else {
                        System.out.println("Invalid ID. Player not found.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    read.next();
                }
            } while (!validId);
        }

    }

    public static void generateNewSale(int escapeRoomId) {
        ArrayList<Room> roomsInEscaperoom = escapeRoomService.getRoomInEscapeRoom(escapeRoomId);
        ArrayList<ObjectDeco> objectsInRoom = new ArrayList<>();
        roomsInEscaperoom.forEach(room -> {
            ArrayList<ObjectDeco> objects = roomService.getAllObjectsInRoom(room.getId());
            objectsInRoom.addAll(objects);
        });
        double finalPrice = 0;
        for (int i = 0; i < roomsInEscaperoom.size(); i++) {
            finalPrice += roomsInEscaperoom.get(i).getPrice();
        }
        for (int i = 0; i < objectsInRoom.size(); i++) {
            finalPrice += objectsInRoom.get(i).getPrice();
        }
        Sale newSale = new Sale(finalPrice);
        salesService.createSale(finalPrice, escapeRoomId);
        System.out.println("Sale: " + newSale.toString() + " created Successful");

    }

    public static void displaySales() {
        ArrayList<Sale> sales = salesService.getAllSale();
        if (sales.isEmpty()) {
            System.out.println("No rooms found for the selected escape room.");
        } else {
            //System.out.println("Rooms for Escape Room ID " + selectedID + ":");
            for (Entity sale : sales) {
                System.out.println(sale);
            }
        }

    }

    public static void removeSale(int idSelected) {
        Sale selectedSale = salesService.getSaleById(idSelected);
        try {
            salesService.deleteSale(selectedSale.getId());
            System.out.println("Sale deleted successfully!");
        } catch (SaleIdNotFoundException e) {
            System.out.println("Failed to delete SaleId not found.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}