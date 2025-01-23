package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

import static org.example.Main.MenuController.UserInputs.askString;

public class RemoveDecorationObject implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("What is the ID of the object you want to delete?",read));
        ObjectDecoService ods = new ObjectDecoService();
        ods.deleteObjectDeco(id);
    }
}
