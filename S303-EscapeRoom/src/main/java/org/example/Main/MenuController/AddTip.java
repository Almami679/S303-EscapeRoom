package org.example.Main.MenuController;


import org.example.Services.EscapeRoomServices.TipService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class AddTip implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        String name;
        int id;
        System.out.println("What is the tip's text?");
        name = read.next();
        System.out.println("To which room does this tip belong?");
        id = Integer.parseInt(read.next());
        TipService ts = new TipService(repository);
        ts.createTip(name);
    }
}
