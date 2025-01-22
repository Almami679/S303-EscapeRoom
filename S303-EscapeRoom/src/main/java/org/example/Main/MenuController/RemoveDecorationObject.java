package org.example.Main.MenuController;


import org.example.Main.Services.EscapeRoomServices.ObjectDecoService;
import org.example.Modules.Entities.RoomEntities.ObjectDeco;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class RemoveDecorationObject implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        int id;
        System.out.println("What is the ID of the object you want to delete?");
        id = read.nextInt();
        ObjectDecoService ods = new ObjectDecoService(repository);
        ods.deleteObjectDeco(id);
    }
}
