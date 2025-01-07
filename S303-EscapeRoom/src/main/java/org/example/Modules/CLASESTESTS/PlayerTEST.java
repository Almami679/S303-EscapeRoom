package org.example.Modules.CLASESTESTS;

public class PlayerTEST {
    private static int latestId = 0;
    private int id;
    private String name;
    private String email;
    private int consentNotif;
    private boolean deleted;

    public PlayerTEST(String name, String email, int consentNotif, boolean deleted) {
        this.id = ++latestId;
        this.name = name;
        this.email = email;
        this.consentNotif = consentNotif;
        this.deleted = deleted;
    }

    public static int getLatestId() {
        return latestId;
    }

    public static void setLatestId(int latestId) {
        PlayerTEST.latestId = latestId;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
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