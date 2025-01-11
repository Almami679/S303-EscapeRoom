package org.example.Main;

import java.util.ArrayList;

public class EscapeRoomBuilder {
    private String name;
    private ArrayList<ObjectDeco> decorations;
    private ArrayList<Tips> tips;
    private ArrayList<Room> rooms;
    private String theme;


    public EscapeRoom build(){
        return new EscapeRoom(name, decorations, tips,rooms,theme,false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecorations(ArrayList<ObjectDeco> decorations) {
        this.decorations = decorations;
    }

    public void setTips(ArrayList<Tips> tips) {
        this.tips = tips;
    }

    public void setRoom(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
