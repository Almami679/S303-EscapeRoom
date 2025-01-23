package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;
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
        RoomService rs = new RoomService(repository);
        rs.createRoom(name,price,difficulty);
    }
}
