package org.example.Main.MenuController;


import org.example.Main.Services.EscapeRoomServices.EscapeRoomService;
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
        System.out.println("What is the Escape Room's name?");
        name = read.next();
        System.out.println("What is the Escape Room's price?");
        price = Double.parseDouble(read.next());
        System.out.println("What is the Escape Room's theme?");
        theme = read.next();
        EscapeRoomService ers = new EscapeRoomService(repository);
        ers.createEscapeRoom(name,price,theme);
    }
}

