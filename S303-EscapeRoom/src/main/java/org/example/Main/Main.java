package org.example.Main;

import org.example.MenuController.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.start();
    }
}