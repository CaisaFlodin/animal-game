package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.*;

import java.util.ArrayList;

public class Store {

    protected ArrayList<Food> foodsForSale = new ArrayList<>();
    protected ArrayList<Animal> animalsForSale = new ArrayList<>();

    public Store() {
        foodsForSale.add(new Stardust());
        foodsForSale.add(new Meat());
        foodsForSale.add(new Leaves());
        foodsForSale.add(new Grass());
        animalsForSale.add(new Unicorn());
        animalsForSale.add(new Gryphon());
        animalsForSale.add(new Dragon());
        animalsForSale.add(new Llama());
        animalsForSale.add(new Sloth());
    }

    public void displayAnimals() {
        int index = 1;
        for (Animal animal : animalsForSale) {
            System.out.println(index + ". " + animal.getType() + " " + animal.getPrice());
            index++;
        }
    }

    public void displayFoods() {
        int index = 1;
        for (Food food : foodsForSale) {
            System.out.println(index + "." + food.getName());
            index++;
        }
    }

    public Food buyStardust() {
        return foodsForSale.get(0);
    }

    public Food buyMeat() {
        return foodsForSale.get(1);
    }

    public Food buyLeaves() {
        return foodsForSale.get(2);
    }

    public Food buyGrass() {
        return foodsForSale.get(3);
    }

    public Animal buyUnicorn() {
        return animalsForSale.get(0);
    }

    public Animal buyGryphon() {
        return animalsForSale.get(1);
    }

    public Animal buyDragon() {
        return animalsForSale.get(2);
    }

    public Animal buyLlama() {
        return animalsForSale.get(3);
    }

    public Animal buySloth() {
        return animalsForSale.get(4);
    }

    public void sellAnimal(Player player) {
        player.ownedAnimals.remove(0);
    }

       public void buyAnimal(Animal animal, Player player) {
           if (player.hasEnoughMoney()) {
               displayAnimals();
           }
       }
    }




   /* public Horse buyAHorse() {
        return new Horse();

    }

    public Hen buyAHen(){
        return new Hen();

    }

    public Pig buyAPig(){
        return new Pig();

    }

    public Sheep buyASheep(){
        return new Sheep();

    }

    public Cow buyACow(){
        return new Cow();

    }

    // FOOD

    public HorseFood buyHorseFood() {
        return new HorseFood();

    }

    public HenFood buyHenFood(){
        return new HenFood();

    }

    public PigFood buyPigFood(){
        return new PigFood();

    }

    public SheepFood buySheepFood(){
        return new SheepFood();

    }

    public CowFood buyCowFood(){
        return new CowFood();

    }*/

