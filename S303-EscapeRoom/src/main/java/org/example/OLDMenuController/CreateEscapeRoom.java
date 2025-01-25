package org.example.OLDMenuController;


import org.example.Services.EscapeRoomServices.EscapeRoomService;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.OLDMenuController.UserInputs.askString;

public class CreateEscapeRoom implements ServiceProcessor {
    private static final Logger LOGGER = Logger.getLogger(CreateEscapeRoom.class.getName());

    public String name, theme;
    public double price;

    @Override
    public void process(Scanner read) {
        name = askString("What is the Escape Room's name?",read);
        price = Double.parseDouble(askString("What is the Escape Room's price?",read));
        theme = askString("What is the Escape Room's theme?",read);
        EscapeRoomService ers = new EscapeRoomService();
        ers.createEscapeRoom(name,price,theme);
    }
}

