package com.company.game.animals;

import com.company.game.food.Food;

public class Sloth extends Animal {

    private static final int BASE_PRICE = 50;
    private static final int MAX_AGE = 9;

    @Override
    protected int getAnimalBasePrice() {
        return BASE_PRICE;
    }

    @Override
    public String getType() {
        return "Sloth";
    }

    @Override
    protected int getMaximumAge() {
        return MAX_AGE;
    }

    @Override
    public boolean canEat(Food f) {
        return f.getType() == Food.LEAVES;
    }

    @Override
    protected Animal spawnBaby() {
        return new Sloth();
    }

    @Override
    protected int getMaximumLitterSize() {
        return 2;
    }
}
