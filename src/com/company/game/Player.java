package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.Food;

import java.util.ArrayList;

public class Player {
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

    public boolean hasEnoughMoney() {
       /* if (money >= store. */return true;
    }

    public void subtractExpense(int expense) {
        money = money - expense;
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
            if(!animal.isAlive()){
                deadAnimals.add(animal);
            }
        }
        for(Animal deadAnimal: deadAnimals) {
            System.out.println(getName() + " : " + deadAnimal.getName() + " is dead!! :(");
            ownedAnimals.remove(deadAnimal);
        }

    }
}
