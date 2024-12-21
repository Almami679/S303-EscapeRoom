package org.example.Repository;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.DatabaseConnectionFailed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface RepositoryMGMT {

    Connection dbConnect() ;

}
