package org.example.Main.MenuController;


import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class RemoveTip implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        String name;
        System.out.println("What room do you want to delete?");
        name = read.next();
        /*for(Tips t:dbc.getAllRooms()){
            if(Objects.equals(name, t.getText())){

            }
        }*/
    }
}
