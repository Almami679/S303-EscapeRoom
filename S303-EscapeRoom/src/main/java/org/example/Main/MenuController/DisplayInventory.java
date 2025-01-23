package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Services.EscapeRoomServices.TipService;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Modules.Entities.RoomEntities.Tips;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class DisplayInventory implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        RoomService rs = new RoomService();
        System.out.println("These are the rooms in the Escape Room's inventory:");
        for(Room r:rs.getAllRoom()){
            System.out.println(r.getName());
        }
        ObjectDecoService ods = new ObjectDecoService();
        System.out.println("These are the decoration objects in the Escape Room's inventory:");
        for(ObjectDeco o:ods.getAllObjectDeco()){
            System.out.println(o.getName());
        }
        TipService ts = new TipService();
        System.out.println("These are the tips in the Escape Room's inventory:");
        for(Tips t:ts.getAllTips()){
            System.out.println(t.getText());
        }
    }
}
