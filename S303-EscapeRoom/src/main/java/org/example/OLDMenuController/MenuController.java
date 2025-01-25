package org.example.OLDMenuController;

import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuController {
    private static final Map<Integer, ServiceProcessor> actions = new HashMap<>();


    static {
        actions.put(1, new CreateEscapeRoom());
        actions.put(2, new AddRoom());
        actions.put(3, new AddTip());
        actions.put(4, new AddDecorationObject());
        actions.put(5, new DisplayInventory());
        actions.put(6, new DisplayValueInventory());
        actions.put(7, new RemoveRoom());
        actions.put(8, new RemoveTip());
        actions.put(9, new RemoveDecorationObject());
        actions.put(10, new GenerateTicket());
        actions.put(11, new DisplayIncome());
        actions.put(12, new SubscribeNotifications());
    }

    public static void handleUserInput(int userInput, Scanner read) throws SQLException {
        ServiceProcessor action = actions.get(userInput);
        if (action != null) {
            action.process(read);
        } else if (userInput == 0) {
            System.out.println("Exiting... Goodbye!");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }
}

