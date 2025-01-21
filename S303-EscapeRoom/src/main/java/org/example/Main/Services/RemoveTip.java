package org.example.Main.Services;

import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.DatabaseConnection;

import java.util.Objects;
import java.util.Scanner;

public class RemoveTip implements ServiceProcessor{
    @Override
    public void process(Scanner read) {
        String name;
        System.out.println("What room do you want to delete?");
        name = read.next();
        /*for(Tips t:dbc.getAllRooms()){
            if(Objects.equals(name, t.getText())){

            }
        }*/
    }
}
