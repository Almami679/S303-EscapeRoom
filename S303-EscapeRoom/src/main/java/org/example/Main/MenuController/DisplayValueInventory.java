package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class DisplayValueInventory implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        double sum = 0;
        ObjectDecoService ods = new ObjectDecoService(repository);
        for(ObjectDeco o:ods.getAllObjectDeco()){
            sum+=o.getPrice();
        }
        RoomService rs = new RoomService(repository);
        for(Room r:rs.getAllRoom()){
            sum+=r.getPrice();
        }
        EscapeRoomService ers = new EscapeRoomService(repository);
        for(EscapeRoom er:ers.getAllEscapeRooms()){
            sum+=er.getPrice();
        }
        System.out.println("The inventory's value is: " + sum);
    }
}
