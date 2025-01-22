package org.example.Main.MenuController;

import org.example.Modules.Entities.GameEntities.Sale;
import org.example.Repository.Common.RepositoryImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class GenerateTicket implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {

    }

//    private static Sale selectSale(Scanner read, RepositoryImpl repository) {
//        ArrayList<Sale> allSales = new ArrayList<>();
//        int option = 0;
//       // allSales = repository.getAll(EntityAttributes.sale);
//        System.out.println("Select a sale ID to generate Ticket");
//        allSales.forEach(System.out::println);
//        try {
//            option = read.nextInt();
//        }
//
//
//    }
}
