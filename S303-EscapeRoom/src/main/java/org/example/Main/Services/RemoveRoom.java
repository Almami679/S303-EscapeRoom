package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.DatabaseConnection;

import java.util.Objects;
import java.util.Scanner;

public class RemoveRoom implements ServiceProcessor{
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        String name;
        System.out.println("What room do you want to delete?");
        name = read.next();
        /*for(Room r:dbc.getAllRooms()){
            if(Objects.equals(name, r.getName())){

            }
        }*/
    }
}
