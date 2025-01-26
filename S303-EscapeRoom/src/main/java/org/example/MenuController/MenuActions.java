package org.example.MenuController;

import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomHasRoom;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Services.EscapeRoomServices.EscapeRoomService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuActions {
    private static final EscapeRoomService escapeRoomService = new EscapeRoomService();

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
    public static void addNewRoom(Scanner read) {

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

    public static void updateRoom(Scanner read) {

    }

    public static void removeRoom(Scanner read) {

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

    public static void displaySales(Scanner read) {

    }

    public static void removeSale(Scanner read) {

    }
}