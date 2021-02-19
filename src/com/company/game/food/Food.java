package com.company.game.food;

import java.io.Serializable;

public abstract class Food implements Serializable {

    private int price;
    private int healthValue;
    private String name;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(int healthValue) {
        this.healthValue = healthValue;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        //TODO! add weight
        return getName() + " : N/A kg left";
    }

    public void setName(String name) {
        this.name = name;
    }
}
