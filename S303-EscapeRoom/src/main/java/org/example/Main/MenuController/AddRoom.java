package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.Main.MenuController.UserInputs.askString;

public class AddRoom implements ServiceProcessor {

    private static final Logger LOGGER = Logger.getLogger(CreateEscapeRoom.class.getName());
    @Override
    public void process(Scanner read) {
        String name, difficulty;
        double price;
        int id;
        name = askString("What is the Room's name?",read);
        price = Double.parseDouble(askString("What is the Room's price?",read));
        difficulty = askString("What is the Room's difficulty?",read);
        RoomService rs = new RoomService();
        rs.createRoom(name,price,difficulty);
    }
}
