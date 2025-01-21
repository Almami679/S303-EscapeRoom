package org.example.Main.Services;

import org.example.Repository.Common.DatabaseConnection;

import java.util.Scanner;

public interface ServiceProcessor {
    void process(DatabaseConnection dbc, Scanner read);
}
