package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class RemoveRoom implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        int id;
        System.out.println("What is the ID of the room you want to delete?");
        id = read.nextInt();
        RoomService rs = new RoomService(repository);
        rs.deleteRoom(id);
    }
}
