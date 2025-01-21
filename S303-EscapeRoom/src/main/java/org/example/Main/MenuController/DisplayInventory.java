package org.example.Main.MenuController;


import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class DisplayInventory implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
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
