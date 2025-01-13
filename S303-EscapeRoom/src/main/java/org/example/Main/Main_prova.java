package org.example.Main;

import org.example.Exceptions.ObjectAvailabilityException;
import org.example.Exceptions.RoomAvailabilityException;
import org.example.Exceptions.TipAvailabilityException;
import org.example.Modules.EscapeRoomBuilder.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main_prova {
    public static void main(String[] args) {
        ArrayList<ObjectDeco> decos = new ArrayList<>();
        decos.add(new ObjectDeco(1L,"planta","plastic",3,10,true));
        ArrayList<Tips> tips = new ArrayList<>();
        tips.add(new Tips(1L,"Mira sota la catifa","marlowe",3,3));
        ArrayList<Room> rooms1 = new ArrayList<>();
        rooms1.add(new Room("Hab1","Medium",40, Timestamp.valueOf("2018-09-01 09:01:15"),Timestamp.valueOf(LocalDateTime.now())));
        rooms1.add(new Room("Hab4","High",20, Timestamp.valueOf("2018-09-01 09:01:15"),Timestamp.valueOf(LocalDateTime.now())));

        //ESCAPE ROOM 1
        EscapeRoomBuilder esroomb = new EscapeRoomBuilder();
        esroomb.setName("Cuina");
        esroomb.setDecorations(decos);
        esroomb.setTips(tips);
        esroomb.setRoom(rooms1);
        esroomb.setTheme("Retro");
        EscapeRoom eroom = esroomb.build();
        try {
            eroom.addObjecttoRoom(decos.get(0),eroom.getRooms().get(0));
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        try {
            eroom.addTiptoRoom(tips.get(0),eroom.getRooms().get(0));
        } catch (RoomAvailabilityException | TipAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(eroom.toString());
        System.out.println("First escape room");
        System.out.println("These are the rooms:");
        for(Room r:eroom.getRooms()){
            System.out.println(r.toString());
        }
        System.out.println("These are the tips:");
        for (Tips t: eroom.getRooms().get(0).getTipsList()){
            System.out.println(t.getText());
            System.out.println(t.getCount());
        }
        for (Tips t: eroom.getRooms().get(1).getTipsList()){
            System.out.println(t.getText());
            System.out.println(t.getCount());
        }
        System.out.println("These are the decorations:");
        for (ObjectDeco o: eroom.getRooms().get(0).getObjectsList()){
            System.out.println(o.getName());
            System.out.println(o.getCount());
        }
        for (ObjectDeco o: eroom.getRooms().get(1).getObjectsList()){
            System.out.println(o.getName());
            System.out.println(o.getCount());
        }
        System.out.println("The total price is:");
        System.out.println(eroom.calculateTotalPrice());
        System.out.println("The price in use is:");
        System.out.println(eroom.calculatePriceinUse());
        try {
            eroom.removeTipfromRoom(tips.get(0),eroom.getRooms().get(0));
        } catch (TipAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The price in use after is:");
        System.out.println(eroom.calculatePriceinUse());
        System.out.println("The count of the tip is:");
        System.out.println(eroom.getTips().get(0).getCount());


        //ESCAPE ROOM 2
        System.out.println("\n\n\n");
        ArrayList<Room> rooms2 = new ArrayList<>();
        ArrayList<ObjectDeco> decos2 = new ArrayList<>();
        decos2.add(new ObjectDeco(1L,"planta","plastic",3,10,true));
        ObjectDeco table = new ObjectDeco(2L,"table","wood",3,15,true);
        decos2.add(table);
        rooms2.add(new Room("Hab2","Low",45, Timestamp.valueOf("2018-09-01 09:01:15"),Timestamp.valueOf(LocalDateTime.now())));
        EscapeRoom esroom2 = new EscapeRoom("Estudi",decos2,tips, rooms2,"Futurista",false);
        try {
            esroom2.addObjecttoRoom(decos2.get(0),esroom2.getRooms().get(0));
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        try {
            esroom2.addObjecttoRoom(decos2.get(1),esroom2.getRooms().get(0));
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        try {
            esroom2.addTiptoRoom(tips.get(0),esroom2.getRooms().get(0));
        } catch (RoomAvailabilityException | TipAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(esroom2.toString());

        System.out.println("Second escape room");
        System.out.println("These are the rooms:");
        for(Room r:esroom2.getRooms()){
            System.out.println(r.toString());
        }
        System.out.println("These are the tips:");
        for (Tips t: esroom2.getRooms().get(0).getTipsList()){
            System.out.println(t.getText());
            System.out.println(t.getCount());
        }
        System.out.println("These are the decorations:");
        for (ObjectDeco o: esroom2.getRooms().get(0).getObjectsList()){
            System.out.println(o.getName());
            System.out.println(o.getCount());
        }
        System.out.println("The total price is:");
        System.out.println(esroom2.calculateTotalPrice());
        System.out.println("The price in use is:");
        System.out.println(esroom2.calculatePriceinUse());
        try {
            esroom2.removeObjectfromRoom(esroom2.getRooms().get(0).getObjectsList().get(0),esroom2.getRooms().get(0));
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Després de treure una planta");
        System.out.println(esroom2.getDecorations().get(0).getName());
        System.out.println(esroom2.getDecorations().get(0).getCount());
        System.out.println("The price in use is:");
        System.out.println(esroom2.calculatePriceinUse());
        try {
            esroom2.addObjecttoRoom(table,esroom2.getRooms().get(0));
            System.out.println(esroom2.getDecorations().get(1).getName());
            System.out.println(esroom2.getDecorations().get(1).getCount());
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        try {
            esroom2.addObjecttoRoom(table,esroom2.getRooms().get(0));
            System.out.println(esroom2.getDecorations().get(1).getName());
            System.out.println(esroom2.getDecorations().get(1).getCount());
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        try {
            esroom2.addObjecttoRoom(table,esroom2.getRooms().get(0));
            System.out.println(esroom2.getDecorations().get(1).getName());
            System.out.println(esroom2.getDecorations().get(1).getCount());
        } catch (ObjectAvailabilityException | RoomAvailabilityException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("These are the decorations:");
        for (ObjectDeco o: esroom2.getDecorations()){
            System.out.println(o.getName());
            System.out.println(o.getCount());
        }
        System.out.println("These are the rooms:");
        for(Room r:esroom2.getRooms()){
            System.out.println(r.toString());
        }
        System.out.println("These are the tips:");
        for (Tips t: esroom2.getRooms().get(0).getTipsList()){
            System.out.println(t.getText());
            System.out.println(t.getCount());
        }
        System.out.println("These are the decorations:");
        for (ObjectDeco o: esroom2.getRooms().get(0).getObjectsList()){
            System.out.println(o.getName());
            System.out.println(o.getCount());
        }
        System.out.println(esroom2.calculatePriceinUse());
    }
}