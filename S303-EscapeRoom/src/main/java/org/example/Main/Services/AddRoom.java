package org.example.Main.Services;

import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public class AddRoom implements ServiceProcessor{
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        String name, difficulty;
        double price;
        int id;
        System.out.println("What is the Room's name?");
        name = read.next();
        System.out.println("What is the Room's price?");
        price = Double.parseDouble(read.next());
        System.out.println("What is the Room's difficulty?");
        difficulty = read.next();
        System.out.println("What is the Escape Room's id?");
        id = Integer.parseInt(read.next());
        //dbc.addRoom(new RoomTEST(name,difficulty,price,id, 0,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime())));
    }
}
