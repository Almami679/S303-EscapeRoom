package org.example.Repository.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Repository.Serializer;

public class EscapeRoomImp extends RepositoryImpl {
    private static final Logger logger = LogManager.getLogger(EscapeRoomImp.class);
    private DatabaseConnection dbConnection;
    private Serializer serializer;

    public EscapeRoomImp(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.serializer = new Serializer();
    }


}