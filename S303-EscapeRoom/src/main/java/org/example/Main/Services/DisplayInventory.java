package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public class DisplayInventory implements ServiceProcessor{
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        String name, difficulty;
        double price;
        int id;
        System.out.println("These are the rooms in the Escape Room's inventory:");
        /*for(Room r:dbc.getAllRooms()){
            System.out.println(r.getName());
        }
        System.out.println("These are the decoration objects in the Escape Room's inventory:");
        for(ObjectDeco o:dbc.getAllObjectDecos()){
            System.out.println(o.getName());
        }
        System.out.println("These are the tips in the Escape Room's inventory:");
        for(Tips t:dbc.getAllTips()){
            System.out.println(t.getText());
        }*/
    }
}
