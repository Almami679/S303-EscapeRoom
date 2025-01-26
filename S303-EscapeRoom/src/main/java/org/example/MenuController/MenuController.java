package org.example.MenuController;

import org.example.Exceptions.InvalidMenuOptionException;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Services.EscapeRoomServices.EscapeRoomService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private static EscapeRoomService escapeRoomService = new EscapeRoomService();
    private static int selectedID;
    public static void handleMainMenu(int userInput, Scanner read) throws SQLException, InvalidMenuOptionException {
        switch (userInput) {
            case 1:
                MenuActions.createEscapeRoom(read);
                break;
            case 2:
                displayManageEscapeRoomMenu(read);
                break;
            case 3:
                MenuActions.displayEscapeRoom();
                break;
            case 4:
                displayManageInventoryMenu(read);
                break;
            case 5:
                displayManageGameMenu(read);
                break;
            case 6:
                displayManageSalesMenu(read);
                break;
            case 0:
                break;
            default:
                throw new InvalidMenuOptionException("Invalid option. Please try again.");
        }
    }

    private static void displayManageEscapeRoomMenu(Scanner read) throws SQLException {
        List<EscapeRoom> escapeRooms = escapeRoomService.getAllEscapeRooms();
        if (escapeRooms.isEmpty()) {
            System.out.println("No escape rooms found.");
        } else {
            System.out.println("Select an Escape Room:");
            for (int i = 0; i < escapeRooms.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + escapeRooms.get(i).getName());
            }
            int selectedEscapeRoom = -1;
            boolean validInput = false;
            do {
                System.out.print("Enter the number of the escape room: ");
                try {
                    selectedEscapeRoom = read.nextInt();
                    if (selectedEscapeRoom > 0 && selectedEscapeRoom <= escapeRooms.size()) {
                        validInput = true;
                        selectedID = selectedEscapeRoom;
                    } else {
                        System.out.println("Invalid selection. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    read.next();
                }
            } while (!validInput);
            int userInput;
            do {
                System.out.print(MenuOptions.MANAGE_ESCAPE_ROOM_MENU.getMenuText());
                userInput = read.nextInt();
                handleManageEscapeRoomMenu(userInput, read);
            } while (userInput != 0);
        }
    }

    private static void handleManageEscapeRoomMenu(int userInput, Scanner read) throws SQLException {
        switch (userInput) {
            case 1:
                displayManageRoomsMenu(read);
                break;
            case 2:
                displayManageTipsMenu(read);
                break;
            case 3:
                displayManageInventoryMenu(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManageRoomsMenu(Scanner read) throws SQLException {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_ROOMS_MENU.getMenuText());
            userInput = read.nextInt();
            handleManageRoomsMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManageRoomsMenu(int userInput, Scanner read) throws SQLException {
        switch (userInput) {
            case 1:
                MenuActions.addNewRoom(read);
                break;
            case 2:
                MenuActions.displayRooms(selectedID);
                break;
            case 3:
                MenuActions.updateRoom(read);
                break;
            case 4:
                MenuActions.removeRoom(read);
                break;
            case 5:
                displayManageTipsMenu(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManageTipsMenu(Scanner read) {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_TIPS_MENU.getMenuText());
            userInput = read.nextInt();
            handleManageTipsMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManageTipsMenu(int userInput, Scanner read) {
        switch (userInput) {
            case 1:
                MenuActions.addNewTip(read);
                break;
            case 2:
                MenuActions.displayTips(read);
                break;
            case 3:
                MenuActions.removeTip(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManageInventoryMenu(Scanner read) throws SQLException {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_INVENTORY_MENU.getMenuText());
            userInput = read.nextInt();
            handleManageInventoryMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManageInventoryMenu(int userInput, Scanner read) {
        switch (userInput) {
            case 1:
                MenuActions.addObjectToRoom(read);
                break;
            case 2:
                MenuActions.displayInventory(read);
                break;
            case 3:
                MenuActions.removeObject(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManageGameMenu(Scanner read) throws SQLException {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_GAME_MENU.getMenuText());
            userInput = read.nextInt();
            handleManageGameMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManageGameMenu(int userInput, Scanner read) throws SQLException {
        switch (userInput) {
            case 1:
                MenuActions.addNewGame(read);
                break;
            case 2:
                MenuActions.displayGames(read);
                break;
            case 3:
                MenuActions.finishGame(read);
                break;
            case 4:
                displayManagePlayersMenu(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManagePlayersMenu(Scanner read) throws SQLException {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_PLAYERS_MENU.getMenuText());
            userInput = read.nextInt();
            handleManagePlayersMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManagePlayersMenu(int userInput, Scanner read) {
        switch (userInput) {
            case 1:
                MenuActions.createPlayer(read);
                break;
            case 2:
                MenuActions.displayPlayers(read);
                break;
            case 3:
                MenuActions.updatePlayer(read);
                break;
            case 4:
                MenuActions.removePlayer(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displayManageSalesMenu(Scanner read) {
        int userInput;
        do {
            System.out.print(MenuOptions.MANAGE_SALES_MENU.getMenuText());
            userInput = read.nextInt();
            handleManageSalesMenu(userInput, read);
        } while (userInput != 0);
    }

    private static void handleManageSalesMenu(int userInput, Scanner read) {
        switch (userInput) {
            case 1:
                MenuActions.generateNewSale(read);
                break;
            case 2:
                MenuActions.displaySales(read);
                break;
            case 3:
                MenuActions.removeSale(read);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}


