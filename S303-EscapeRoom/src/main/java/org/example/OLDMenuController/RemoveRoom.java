package org.example.OLDMenuController;


import org.example.Services.EscapeRoomServices.RoomService;

import java.util.Scanner;

import static org.example.OLDMenuController.UserInputs.askString;

public class RemoveRoom implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("What is the ID of the room you want to delete?",read));
        RoomService rs = new RoomService();
        rs.deleteRoom(id);
    }
}
