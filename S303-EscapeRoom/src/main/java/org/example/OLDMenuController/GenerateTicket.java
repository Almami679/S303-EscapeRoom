package org.example.OLDMenuController;

import org.example.Services.CommunicatesServices.TicketService;

import java.util.Scanner;

import static org.example.OLDMenuController.UserInputs.askString;

public class GenerateTicket implements ServiceProcessor {
    @Override
    public void process(Scanner read) {
        int id;
        id = Integer.parseInt(askString("For which player would you like to create the ticket?",read));
        TicketService ts = new TicketService();

    }
}
