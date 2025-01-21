package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.logging.Logger;

public class AddDecorationObject implements ServiceProcessor{
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
        objectDeco = new ObjectDeco(name, material,price);
        try {
            repository.add(objectDeco, EntityAttributes.objectdeco);
        } catch (SQLException e) {
            LOGGER.info;
        }
    }
}
