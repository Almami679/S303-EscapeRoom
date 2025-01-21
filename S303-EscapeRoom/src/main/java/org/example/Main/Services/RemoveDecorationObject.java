package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.DatabaseConnection;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Objects;
import java.util.Scanner;

public class RemoveDecorationObject implements ServiceProcessor{
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        String name;
        System.out.println("What room do you want to delete?");
        name = read.next();
        /*for(ObjectDeco o:dbc.getAllRooms()){
            if(Objects.equals(name, o.getName())){

            }
        }*/
    }
}
