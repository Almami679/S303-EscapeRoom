package org.example.Main;

import java.util.Objects;

public class ObjectDeco {
    private Long id;
    private String name;
    private String material;
    private int count;
    private final int ICOUNT;
    private boolean enable;
    private double price;

    public ObjectDeco(Long id, String name, String material, int count, double price, boolean enable) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.count = count;
        this.price = price;
        this.enable = enable;
        this.ICOUNT = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getICOUNT() {
        return ICOUNT;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ObjectDeco that = (ObjectDeco) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
