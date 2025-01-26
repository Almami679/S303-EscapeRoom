package org.example.MenuController;

import org.example.Exceptions.SaleIdNotFoundException;
import org.example.Exceptions.RoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomHasRoom;
import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.example.Services.EscapeRoomServices.RoomService;
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
            //System.out.println("Room id: " + roomId);
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
            //System.out.println("Rooms for Escape Room ID " + selectedID + ":");
            for (Entity room : rooms) {
                System.out.println(room);
            }
        }
    }

    public static void updateRoom(Scanner read, int selectedEscapeRoomID) {
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
            return;
        }

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

    public static void addNewTip(Scanner read) {

    }

    public static void displayTips(Scanner read) {

    }

    public static void removeTip(Scanner read) {

    }

    public static void addObjectToRoom(Scanner read) {

    }

    public static void displayInventory(Scanner read) {

    }

    public static void removeObject(Scanner read) {

    }

    public static void addNewGame(Scanner read) {

    }

    public static void displayGames(Scanner read) {

    }

    public static void finishGame(Scanner read) {

    }

    public static void createPlayer(Scanner read) {

    }

    public static void displayPlayers(Scanner read) {

    }

    public static void updatePlayer(Scanner read) {

    }

    public static void removePlayer(Scanner read) {

    }

    public static void generateNewSale(Scanner read) {

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
            System.out.println("Failed to delete sale: " + e.getMessage());
        }
    }
}