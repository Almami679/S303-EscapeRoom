package org.example.Main;

import org.example.Exceptions.ObjectAvailabilityException;
import org.example.Exceptions.RoomAvailabilityException;
import org.example.Exceptions.TipAvailabilityException;

import java.security.Timestamp;
import java.util.ArrayList;

public class EscapeRoom {
    private String name;
    private static long idCounter=0;
    private long id;
    private ArrayList<ObjectDeco> decorations;
    private ArrayList<Tips> tips;
    private ArrayList<Room> rooms;
    private String theme;
    private boolean deleted;

    public EscapeRoom(String name, ArrayList<ObjectDeco> decorations, ArrayList<Tips> tips, ArrayList<Room> rooms, String theme, boolean deleted) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.decorations = decorations;
        this.tips = tips;
        this.rooms = rooms;
        this.theme = theme;
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }
    public long getIdCounter() {
        return idCounter;
    }

    public void setId(long latestId) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ObjectDeco> getDecorations() {
        return decorations;
    }

    public void setDecorations(ArrayList<ObjectDeco> decorations) {
        this.decorations = decorations;
    }

    public ArrayList<Tips> getTips() {
        return tips;
    }

    public void setTips(ArrayList<Tips> tips) {
        this.tips = tips;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addObjecttoRoom(ObjectDeco obj, Room room) throws ObjectAvailabilityException, RoomAvailabilityException {
        if(!rooms.contains(room)){
            throw new RoomAvailabilityException("This room is not in the inventory");
        }
        if(!decorations.contains(obj)){
            throw new ObjectAvailabilityException("This object is not in the inventory");
        }
        if(obj.getCount() < 1){
            throw new ObjectAvailabilityException("This object is not available");
        }
        obj.setCount(obj.getCount()-1);
        if(!room.getObjectsList().contains(obj)){
            room.addObjectDeco(obj);
        }
    }

    public void removeObjectfromRoom(ObjectDeco obj, Room room) throws ObjectAvailabilityException, RoomAvailabilityException {
        if(!rooms.contains(room)){
            throw new RoomAvailabilityException("This room is not in the inventory");
        }
        if(!decorations.contains(obj)){
            throw new ObjectAvailabilityException("This object is not in the inventory");
        }
        if(!room.getObjectsList().contains(obj)){
            throw new ObjectAvailabilityException("This object is not in the room");
        }
        obj.setCount(obj.getCount()+1);
        room.removeObjectDeco(obj);
    }

    public void addTiptoRoom(Tips tip, Room room) throws TipAvailabilityException, RoomAvailabilityException {
        if(!rooms.contains(room)){
            throw new RoomAvailabilityException("This room is not in the inventory");
        }
        if(!tips.contains(tip)){
            throw new TipAvailabilityException("This tip is not in the inventory");
        }
        if(tip.getCount() < 1){
            throw new TipAvailabilityException("This tip is not available");
        }
        tip.setCount(tip.getCount()-1);
        if(!room.getTipsList().contains(tip)){
            room.addTip(tip);
        }
    }

    public void removeTipfromRoom(Tips tip, Room room) throws TipAvailabilityException, RoomAvailabilityException {
        if(!rooms.contains(room)){
            throw new RoomAvailabilityException("This room is not in the inventory");
        }
        if(!tips.contains(tip)){
            throw new TipAvailabilityException("This tip is not in the inventory");
        }
        if(!room.getTipsList().contains(tip)){
            throw new TipAvailabilityException("This tip is not in the room");
        }
        tip.setCount(tip.getCount()+1);
        room.removeTip(tip);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }
    public void removeRoom(Room room){
        rooms.remove(room);
    }


    public double calculateTotalPrice(){
        double sum = 0;
        for(Room r:this.getRooms()){
            for (Tips t: r.getTipsList()){
                sum+=t.getPrice()*t.getICOUNT();
            }
            for (ObjectDeco o: r.getObjectsList()){
                sum+=o.getPrice()*o.getICOUNT();
            }
            sum+=r.getPrice();
        }
        return sum;
    }

    public double calculatePriceinUse(){
        double sum = 0;
        for(Room r:this.getRooms()){
            for (Tips t: r.getTipsList()){
                sum+=t.getPrice()*(t.getICOUNT()-t.getCount());
            }
            for (ObjectDeco o: r.getObjectsList()){
                sum+=o.getPrice()*(o.getICOUNT()-o.getCount());
            }
            sum+=r.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "EscapeRoom{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", decorations=" + decorations +
                ", tips=" + tips +
                ", rooms=" + rooms +
                ", theme='" + theme + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
