package org.example.Main.Services;

import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;

public interface ServiceProcessor {
    void process(Scanner read, RepositoryImpl repository) throws SQLException;
}
