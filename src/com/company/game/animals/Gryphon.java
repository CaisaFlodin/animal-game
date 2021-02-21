package com.company.game.animals;

import com.company.game.food.Food;

public class Gryphon extends Animal{

    private static final int BASE_PRICE = 200;
    private static final int MAX_AGE = 25;

    @Override
    protected int getAnimalBasePrice() {
        return BASE_PRICE;
    }

    @Override
    public String getType() {
        return "Gryphon";
    }

    @Override
    protected int getMaximumAge() {
        return MAX_AGE;
    }

    @Override
    public boolean canEat(Food f) {
        return f.getType() == Food.MEAT;
    }

    @Override
    protected Animal spawnBaby() {
        return new Gryphon();
    }

    @Override
    protected int getMaximumLitterSize() {
        return 2;
    }
}
