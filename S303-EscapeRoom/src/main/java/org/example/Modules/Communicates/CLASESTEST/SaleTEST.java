package org.example.Modules.Communicates.CLASESTEST;

public class SaleTEST {

    private double price;
    private Long id;
    private static Long nextId = 1L;

    public SaleTEST() {
        this.price = Math.random()*500;
        this.id = nextId;
        nextId++;
    }

    public double getPrice() {
        return price;
    }

    public SaleTEST setPrice(double price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SaleTEST setId(Long id) {
        this.id = id;
        return this;
    }
}
