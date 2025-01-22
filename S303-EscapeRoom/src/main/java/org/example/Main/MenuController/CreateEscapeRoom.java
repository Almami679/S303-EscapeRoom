package org.example.Main.MenuController;


import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateEscapeRoom implements ServiceProcessor {
    private static final Logger LOGGER = Logger.getLogger(CreateEscapeRoom.class.getName());

    public String name, theme;
    public double price;

    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        EscapeRoomBuilder builder = new EscapeRoomBuilder();
        System.out.println("What is the Escape Room's name?");
        name = read.next();
        builder.setName(name);
        System.out.println("What is the Escape Room's price?");
        price = Double.parseDouble(read.next());
        builder.setPrice(price);
        System.out.println("What is the Escape Room's theme?");
        theme = read.next();
        builder.setTheme(theme);
        EscapeRoom eroom = builder.build();
        RepositoryImpl rep = new RepositoryImpl();
        try {
            rep.add(eroom, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL exception occurred while adding escaperoom", e);
        }
    }
}

