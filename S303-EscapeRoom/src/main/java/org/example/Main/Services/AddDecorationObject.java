package org.example.Main.Services;

import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public class AddDecorationObject implements ServiceProcessor{
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        String name, material, difficulty;
        double price;
        int id;
        System.out.println("What is the decoration object's name?");
        name = read.next();
        System.out.println("What is the object's material?");
        material = read.next();
        System.out.println("What is the object's price?");
        price = Double.parseDouble(read.next());
        System.out.println("What is the decoration object's id?");
        id = Integer.parseInt(read.next());
        System.out.println("What is the Room's difficulty?");
        difficulty = read.next();
        //dbc.addObjectDeco(new ObjectDecoTEST(name, material, id, price, 0, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime())));
    }
}
