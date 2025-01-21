package org.example.Main.Services;

import org.example.Modules.Entities.CLASESTESTS.EscapeRoomBuilderTEST;
import org.example.Modules.Entities.CLASESTESTS.EscapeRoomTEST;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.DatabaseConnection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class CreateEscapeRoom implements ServiceProcessor{
    public String name, theme;
    public double price;
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        EscapeRoomBuilder builder = new EscapeRoomBuilder();
        System.out.println("What is the Escape Room's name?");
        name = read.next();
        builder.setName(name);
        System.out.println("What is the Escape Room's price?");
        price = Double.parseDouble(read.next());
        builder.setPrice(price);
        System.out.println("What is the Escape Room's theme?");
        theme = read.next();
        builder.setTheme(theme);
        builder.setDeleted(0);
        builder.setCreatedAt(new Timestamp(new Date().getTime()));
        builder.setUpdatedAt(new Timestamp(new Date().getTime()));
        EscapeRoom eroom = builder.build();
        //dbc.addEscapeRoom(eroom);
    }
}
