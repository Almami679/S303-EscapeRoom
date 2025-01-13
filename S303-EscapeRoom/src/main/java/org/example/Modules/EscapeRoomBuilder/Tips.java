package org.example.Modules.EscapeRoomBuilder;

import java.util.Objects;

public class Tips {
    private Long id;
    private String text;
    private String theme;
    private double price;

    private int count;
    private final int ICOUNT;

    public Tips(Long id, String text, String theme, double price, int count) {
        this.id = id;
        this.text = text;
        this.theme = theme;
        this.price = price;
        this.count = count;
        this.ICOUNT = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getICOUNT() {
        return ICOUNT;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tips tips = (Tips) object;
        return Objects.equals(id, tips.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
