package org.example.Modules.CLASESTESTS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Repository.DatabaseConnection;

public class PlayerTEST {
    private static final Logger logger = LogManager.getLogger(EscapeRoomTEST.class);
    private int id;
    private String name;
    private String email;
    private int consentNotif;
    private int deleted;

    public PlayerTEST(String name, String email, int consentNotif, int deleted) {
        this.id = getLatestIdFromDB();
        this.name = name;
        this.email = email;
        this.consentNotif = consentNotif;
        this.deleted = deleted;
    }

    private int getLatestIdFromDB() {
        DatabaseConnection db = new DatabaseConnection();
        return db.getLatestPlayerId() + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConsentNotif() {
        return consentNotif;
    }

    public void setConsentNotif(int consentNotif) {
        this.consentNotif = consentNotif;
    }

    public int isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "PlayerTEST{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", consentNotif=" + consentNotif +
                ", deleted=" + deleted +
                '}';
    }
}