package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.*;
import com.company.utils.InputHandler;
import com.company.utils.OutputHandler;

import java.util.ArrayList;

public class Store {

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

    /**
     * Removes the animal from the list and creates a new one in it's stead.
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

