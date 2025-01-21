package org.example.Main.MenuController;


import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class DisplayValueInventory implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        double sum = 0;
        /*for(ObjectDeco o:dbc.getAllObjectDecos()){
            sum += o.getPrice();
        }
        for(Room r:dbc.getAllRooms()){
            sum += r.getPrice();
        }*/
        System.out.println("The inventory's value is: " + sum);
    }
}
