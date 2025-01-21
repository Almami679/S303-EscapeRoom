package org.example.Main.MenuController;


import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRoom implements ServiceProcessor {

    private static final Logger LOGGER = Logger.getLogger(CreateEscapeRoom.class.getName());
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        String name, difficulty;
        double price;
        int id;
        System.out.println("What is the Room's name?");
        name = read.next();
        System.out.println("What is the Room's price?");
        price = Double.parseDouble(read.next());
        System.out.println("What is the Room's difficulty?");
        difficulty = read.next();
        RepositoryImpl rep = new RepositoryImpl();
        Room room = new Room(name,difficulty,price);
        try {
            rep.add(room, EntityAttributes.room);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL exception occurred while adding room", e);
        }
    }
}
