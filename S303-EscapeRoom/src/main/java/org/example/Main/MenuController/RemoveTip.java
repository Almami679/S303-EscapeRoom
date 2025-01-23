package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.TipService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class RemoveTip implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        int id;
        System.out.println("What is the ID of the tip you want to delete?");
        id = read.nextInt();
        TipService ts = new TipService(repository);
        ts.deleteTip(id);
    }
}
