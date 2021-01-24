package com.company.game;

import com.company.game.animals.Animal;
import com.company.game.food.Food;

import java.util.ArrayList;

public class Player {

    private final String name;

    private int money;
    private ArrayList<Food> ownedFood = new ArrayList<>();
    private ArrayList<Animal> ownedAnimals = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.money = Rules.STARTING_MONEY;
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
        return ownedFood;
    }

    public void addFood(Food food) {
        ownedFood.add(food);
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

    public void sellAnimal() {

    }

    public boolean nameExists(String name) {
        for (Animal animal : ownedAnimals) {
            if (animal.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
