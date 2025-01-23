package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;
import java.util.logging.Logger;

public class AddDecorationObject implements ServiceProcessor {
    private static final Logger LOGGER = Logger.getLogger(ObjectDeco.class.getName());
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        ObjectDeco objectDeco = null;
        String name, material;
        double price;
        System.out.println("What is the decoration object's name?");
        name = read.next();
        System.out.println("What is the object's material?");
        material = read.next();
        System.out.println("What is the object's price?");
        price = Double.parseDouble(read.next());
        ObjectDecoService ods= new ObjectDecoService(repository);
        ods.createObjectDeco(name,material,price);
    }
}
