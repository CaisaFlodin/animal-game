package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.Food;

import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable {
    private final static int STARTING_MONEY = 1000;

    private final String name;

    private int money;
    protected ArrayList<Food> ownedFoods = new ArrayList<>();
    protected ArrayList<Animal> ownedAnimals = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.money = STARTING_MONEY;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Food> getFoodList() {
        return ownedFoods;
    }

    public void addFood(Food food) {
        ownedFoods.add(food);
    }

    public ArrayList<Animal> getAnimalList() {
        return ownedAnimals;
    }

    public void addAnimal(Animal animal) {
        ownedAnimals.add(animal);
    }

    public void feedAnimal() {
        int choice;
    }

    public boolean hasAnimalWithName(String name) {
        for (Animal animal : ownedAnimals) {
            if (animal.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Filters the available animals in the store, and returns the ones
     * that the player can afford.
     * @param store
     * @return
     */
    public ArrayList<Animal> getAffordedAnimals(Store store) {
        ArrayList<Animal> afforded = new ArrayList<>();
        for(Animal a: store.getAnimalsForSale()) {
            if(getMoney() >= a.getPrice()) {
                afforded.add(a);
            }
        }
        return afforded;
    }

    /**
     * Filters the available foods in the store, and returns the ones
     * that the player can afford.
     * @param store
     * @return
     */
    public ArrayList<Food> getAffordedFood(Store store) {
        ArrayList<Food> afforded = new ArrayList<>();
        for(Food f: store.getFoodsForSale()) {
            if(getMoney() >= f.getPrice()) {
                afforded.add(f);
            }
        }
        return afforded;
    }


    public void subtractExpense(int expense) {
        money = money - expense;
    }
    public void addProfit(int profit){
        money = money + profit;
    }

    public Animal getAnimal(int selection) {
        return ownedAnimals.get(selection - 1);
    }

    public Food getFood(int selection) {
        return ownedFoods.get(selection - 1);
    }

    public String toString() {
        return getName() + " : " + getMoney() + " money left";
    }

    public void roundEnded() {
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        for (Animal animal : ownedAnimals) {
            animal.hurt();
            if(!animal.isAlive()) {
                deadAnimals.add(animal);
            }
        }

        for(Animal deadAnimal: deadAnimals) {
            System.out.println(getName() + " : " + deadAnimal.getName() + " is dead!! :(");
            ownedAnimals.remove(deadAnimal);
        }

    }
}
