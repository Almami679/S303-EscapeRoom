package org.example.Main.MenuController;

import org.example.Services.CommunicatesServices.TicketService;
import org.example.Repository.Common.RepositoryImpl;

import java.util.Scanner;

public class GenerateTicket implements ServiceProcessor {
    @Override
    public void process(Scanner read, RepositoryImpl repository) {
        int id;
        System.out.println("For which player would you like to create the ticket?");
        id = read.nextInt();
        TicketService ts = new TicketService(repository);
        ts.createTicket(id);
    }
}
