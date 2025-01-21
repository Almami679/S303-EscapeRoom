package org.example.Main.Services;

import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public class AddTip implements ServiceProcessor{
    @Override
    public void process(DatabaseConnection dbc, Scanner read) {
        String name;
        int id;
        System.out.println("What is the tip's text?");
        name = read.next();
        System.out.println("To which room does this tip belong?");
        id = Integer.parseInt(read.next());
        //dbc.addTips(new TipsTEST(name,id));
    }
}
