package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.Food;
import com.company.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private final static int STARTING_MONEY = 1000;

    private final String name;
    private int money;
    private ArrayList<Food> ownedFoods = new ArrayList<>();
    private ArrayList<Animal> ownedAnimals = new ArrayList<>();

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

    public ArrayList<Food> getFoodList() {
        return ownedFoods;
    }

    /**
     * Adds new food to the user's inventory.
     * If there already exists food of the same kind in the bag, its quantity will increase
     * instead of adding a new food object.
     * @param newFood the new food to be added.
     */
    public void addFood(Food newFood) {
        //If the user already has the same kind of food in their bag, increase its quantity
        for(Food ownedFood: ownedFoods) {
            if(ownedFood.getType() == newFood.getType()) {
                int newQuantity = ownedFood.getQuantity() + newFood.getQuantity();
                ownedFood.setQuantity(newQuantity);
                return;
            }
        }
        // If the program gets here, it means there was no food of the same type in ownedFoods.
        ownedFoods.add(newFood);
    }

    public ArrayList<Animal> getAnimalList() {
        return ownedAnimals;
    }

    public void addAnimal(Animal animal) {
        ownedAnimals.add(animal);
    }

    /**
     * Checks whether a player can continue playing in the next round.
     * They should be able to continue if there have any living animals left,
     * OR can purchase an animal with the money they have left.
     * @param store
     * @return
     */
    public boolean canContinue(Store store) {
        if (ownedAnimals.size()==0){
            ArrayList<Animal> affordedAnimals = store.getAffordedAnimals(money);
            if(affordedAnimals.size() == 0) {
                return false;
            }
        } return true;
    }

    public void subtractExpense(int expense) {
        money = money - expense;
    }
    public void addProfit(int profit){
        money = money + profit;
    }

    public Animal getAnimal(int selection) {
        return ownedAnimals.get(selection);
    }

    public Food getFood(int selection) {
        return ownedFoods.get(selection);
    }

    public String toString() {
        return getName() + " : " + getMoney() + " money left";
    }


    /**
     * Hurts animals, and remove them from the user's animal list if they are dead.
     */
    private void updateAnimalList() {
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        for (Animal animal : ownedAnimals) {
            animal.nextRound();

            if(animal.isDead()) {
                deadAnimals.add(animal);
            }
        }
        for(Animal deadAnimal: deadAnimals) {
            OutputHandler.printMessage(getName() + " : " + deadAnimal.getName() + " is dead!! :(");
            ownedAnimals.remove(deadAnimal);
        }
    }

    /**
     * Removes foods from a user's food list which have no quantity left.
     */
    private void updateFoodList() {
        ArrayList<Food> emptyFoods = new ArrayList<>();

        for(Food f: this.ownedFoods) {
            if(!f.foodLeft()) {
                emptyFoods.add(f);
            }
        }
        for(Food emptyFood: emptyFoods) {
            this.ownedFoods.remove(emptyFood);
        }
    }

    /**
     * Method that should be run every time a round has ended in the game.
     */
    public void nextRound() {
        updateAnimalList();
        updateFoodList();
    }

    /**
     * Calculates how many units of a type of food a user can buy.
     * @param food the food to be bought.
     * @return the maximum quantity of the food the user can buy.
     */
    public int getMaxQuantity(Food food) {
        return getMoney() / food.getUnitPrice();
    }


    /**
     * @return A list of names of all the animals that a player owns.
     */
    public ArrayList<String> getUsedAnimalNames() {
        ArrayList<String> usedNames = new ArrayList<>();
        for(Animal a: getAnimalList()) {
            usedNames.add(a.getName());
        }
        return usedNames;
    }

    public void removeAnimal(Animal animal) {
        this.ownedAnimals.remove(animal);
    }


    public void removeFood(Food food) {
        this.ownedFoods.remove(food);
    }
}
