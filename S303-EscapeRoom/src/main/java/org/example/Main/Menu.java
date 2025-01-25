package org.example.Main;


import org.example.Exceptions.InvalidMenuOptionException;
import org.example.Main.MenuOptions;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private MenuController menuController = new MenuController();

    public void displayMainMenu(Scanner read) throws SQLException {
        int userInput;
        do {
            System.out.print(MenuOptions.MAIN_MENU.getMenuText());
            try {
                userInput = read.nextInt();
                menuController.handleMainMenu(userInput, read);
            } catch (InputMismatchException e) {
                read.next();
                System.out.println("Invalid input. Please enter a number.");
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void start() throws SQLException {
        Scanner read = new Scanner(System.in);
        read.useDelimiter("\r?\n");
        displayMainMenu(read);
    }}
/*
Main Menu ==> 1. Create Escape Room
              2. Manage Escape Room
              3. Display Escape Room
              4. Manage Inventory
              5. Manage Game
              6. Manage Sales
              0. Exit

Manage Escape Room ==> 1. Manage rooms
                       2. Manage tips
                       3. Manage Inventory
                       0. Exit

Manage Rooms ==> 1. Add new room
                 2. Display rooms
                 3. Update room
                 4. Remove room
                 5. Manage tips
                 0. Exit

Manage Tips ==> 1. Add new tip
                2. Display tips
                3. Remove tip
                0. Exit

Manage Inventory ==> 1. Add new object
                     2. Display inventory
                     3. Remove object
                     0. Exit

Manage Game ==> 1. Add new game
                2. Display games
                3. Finish game
                4. Manage players
                0. Exit

Manage Players ==> 1. Create player
                   2. Display players
                   3. Update player
                   4. Remove player
                   0. Exit

Manage Sales ==> 1. Generate new sale
                 2. Display sales
                 3. Remove sale
                 0. Exit
*/