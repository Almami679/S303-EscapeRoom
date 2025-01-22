package org.example.Main;
import org.example.Main.MenuController.MenuController;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void displayMenu(Scanner read) throws SQLException {
        int userInput;
        String text = "---------------------------------------------------------------------------\n" +
                "------------------------WELCOME TO  THE ESCAPE ROOM------------------------\n" +
                "---------------------------------------------------------------------------\n" +
                "[1]Create Escape Room\t\t\t[2]Add new room\n[3]Add tip to room\t\t\t[4]Add object to room\n" +
                "[5]Display Inventory\t\t\t[6]Display inventory value\n[7]Remove room\t\t\t[8]Remove tip\n" +
                "[9]Remove object\t\t\t[10]Generate player's ticket\n[11]Display ticket sales\t\t\t[12]Subscribe to receive notifications\n[0]Exit";
        do {
            System.out.println(text);
            userInput = read.nextInt();
            MenuController.handleUserInput(userInput, read);
        } while (userInput != 0);
        System.out.println("Exiting... Goodbye!");
    }

    public void start() throws SQLException {
        int option, id;
        String name, theme, material, difficulty;
        double price;
        Scanner read = new Scanner(System.in);
        read.useDelimiter("\r?\n");
        displayMenu(read);
    }
}
