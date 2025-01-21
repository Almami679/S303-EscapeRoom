package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public class DisplayValueInventory implements ServiceProcessor{
    @Override
    public void process(Scanner read) {
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
