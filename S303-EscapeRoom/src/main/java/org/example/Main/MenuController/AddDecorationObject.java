package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.Main.MenuController.UserInputs.askString;

public class AddDecorationObject implements ServiceProcessor {
    private static final Logger LOGGER = Logger.getLogger(ObjectDeco.class.getName());
    @Override
    public void process(Scanner read) {
        ObjectDeco objectDeco = null;
        String name, material;
        double price;
        name = askString("What is the decoration object's name?",read);
        material = askString("What is the object's material?",read);
        price = Double.parseDouble(askString("What is the object's price?",read));
        ObjectDecoService ods= new ObjectDecoService();
        ods.createObjectDeco(name,material,price);
    }
}
