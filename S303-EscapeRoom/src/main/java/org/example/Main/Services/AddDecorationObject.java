package org.example.Main.Services;

import org.example.Main.MenuController.ServiceProcessor;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class AddDecorationObject implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        ObjectDeco obectDeco = null;
        String name, material, difficulty;
        double price;
        System.out.println("What is the decoration object's name?");
        name = read.next();
        System.out.println("What is the object's material?");
        material = read.next();
        System.out.println("What is the object's price?");
        price = Double.parseDouble(read.next());
        System.out.println("What is the Room's difficulty?");
        difficulty = read.next();
        obectDeco = new ObjectDeco(name, material,price);
    }

}
