package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.TipService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

import static org.example.Main.MenuController.UserInputs.askString;

public class AddTip implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        String text;
        int id;
        text = askString("What is the tip's text?",read);
        id = Integer.parseInt(askString("To which room does this tip belong?",read));
        TipService ts = new TipService();
        ts.createTip(text);
    }
}
