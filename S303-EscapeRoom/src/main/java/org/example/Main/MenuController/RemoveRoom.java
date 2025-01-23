package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.RoomService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

import static org.example.Main.MenuController.UserInputs.askString;

public class RemoveRoom implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("What is the ID of the room you want to delete?",read));
        RoomService rs = new RoomService();
        rs.deleteRoom(id);
    }
}
