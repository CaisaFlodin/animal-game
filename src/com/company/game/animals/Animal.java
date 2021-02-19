package com.company.game.animals;

import com.company.game.food.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal implements Serializable {

    public static final int MALE = 1, FEMALE = 2;


    private String name;
    private int gender;
    private int price;

    private int health = 100;
    private boolean breeded = false;
    protected static ArrayList<Food> canEat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderString() {
        return switch (gender) {
            case MALE   -> "Male";
            case FEMALE -> "Female";
            default     -> ""; //Before the animal is named
        };
    }

    private int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * Returns the current price of the animal. It changes depending on how healthy the animal is.
     * @return the current price of the animal.
     */
    public int getPrice() {
        return (price * health) / 100;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean isBreeded() {
        return breeded;
    }

    public void setBreeded(boolean breeded) {
        this.breeded = breeded;
    }

    public boolean canBreedWith(Animal other) {
        return  other.getType().equals(this.getType()) &&
                other.getGender() != this.getGender();
    }

    public ArrayList<Food> getCanEat() {
        return canEat;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public void hurt() {
        Random random = new Random();
        int range = 30 - 10 + 1;
        int randomNum =  random.nextInt(range) + 10;
        health = health - randomNum;
    }

    public void feed(Food food) {
        health = health + 10;
        //Todo! the quantity of food should be lowered by some amount.
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(name != null) {
            sb.append(getName()).append(" : ");
        }

        sb.append(getGenderString())
            .append(" ")
            .append(getType())
            .append(". Health: ")
            .append(getHealth())
            .append(", Selling price: ")
            .append(getPrice());
        return sb.toString();
    }
}
