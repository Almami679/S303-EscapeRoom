package org.example.OLDMenuController;

import java.util.Scanner;

public class UserInputs {

    public static String askString(String question, Scanner read ) {
        String output = "";
        do{
            System.out.println(question);
            output = read.next();
        } while (output.equals(""));
        return output;
    }
}
