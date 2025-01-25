package org.example.OLDMenuController;


import org.example.Services.EscapeRoomServices.TipService;

import java.util.Scanner;

import static org.example.OLDMenuController.UserInputs.askString;

public class RemoveTip implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("What is the ID of the tip you want to delete?",read));
        TipService ts = new TipService();
        ts.deleteTip(id);
    }
}
