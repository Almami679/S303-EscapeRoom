package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.TipService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

import static org.example.Main.MenuController.UserInputs.askString;

public class RemoveTip implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("What is the ID of the tip you want to delete?",read));
        TipService ts = new TipService();
        ts.deleteTip(id);
    }
}
