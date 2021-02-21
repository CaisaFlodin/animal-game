package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.*;
import com.company.utils.InputHandler;
import com.company.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {

    private ArrayList<Food> foodsForSale = new ArrayList<>();
    private ArrayList<Animal> animalsForSale = new ArrayList<>();

    public Store() {
        //restock foods and animals when bought
        restockFoods();
        restockAnimals();
    }

    private void restockFoods(){
        foodsForSale.clear();
        foodsForSale.add(new Stardust());
        foodsForSale.add(new Meat());
        foodsForSale.add(new Leaves());
        foodsForSale.add(new Grass());
    }

    private void restockAnimals(){
        animalsForSale.clear();
        animalsForSale.add(new Unicorn());
        animalsForSale.add(new Gryphon());
        animalsForSale.add(new Dragon());
        animalsForSale.add(new Llama());
        animalsForSale.add(new Sloth());
    }
    /**
     * Removes the animal from the selling list and creates a new one in its stead.
     * @param animal the animal to be replaced
     */
    public void replenishAnimal(Animal animal) {
        int index = animalsForSale.indexOf(animal);
        Animal newAnimal = switch(index) {
            case 0 -> new Unicorn();
            case 1 -> new Gryphon();
            case 2 -> new Dragon();
            case 3 -> new Llama();
            case 4 -> new Sloth();
            default -> null;
        };
        if (newAnimal != null) {
            animalsForSale.set(index, newAnimal);
        }
       }

    public ArrayList<Animal> getAnimalsForSale() {
        return animalsForSale;
    }

    public ArrayList<Food> getFoodsForSale() {
        return foodsForSale;
    }

    /**
     * Replenishes food in the store.
     * @param food food to be replaced
     */
    public void replenishFood(Food food) {
        int index = foodsForSale.indexOf(food);
        Food newFood = switch (index) {
            case 0 -> new Stardust();
            case 1 -> new Meat();
            case 2 -> new Leaves();
            case 3 -> new Grass();
            default -> null;
        };
        if(newFood != null) {
            foodsForSale.set(index, newFood);
        }

    }

    /**
     * Filters the available animals in the store, and returns the ones
     * that the player can afford.
     * @param money
     * @return
     */
    public ArrayList<Animal> getAffordedAnimals(int money) {
        ArrayList<Animal> afforded = new ArrayList<>();
        for(Animal a: animalsForSale){
            if(a.getPrice() <= money) {
                afforded.add(a);
            }
        }
        return  afforded;
    }

    /**
     * Filters the available foods in the store, and returns the ones
     * that the player can afford.
     * @param money
     * @return
     */
    public ArrayList<Food> getAffordedFood(int money) {
        ArrayList<Food> affordedFoods = new ArrayList<>();
        for(Food f: foodsForSale) {
            if(money >= f.getUnitPrice()) {
                affordedFoods.add(f);
            }
        }
        return affordedFoods;
    }
}

