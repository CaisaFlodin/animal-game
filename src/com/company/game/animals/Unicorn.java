package com.company.game.animals;

import com.company.game.food.Food;

public class Unicorn extends Animal {

    private static final int BASE_PRICE = 300;
    private static final int MAX_AGE = 30;

    @Override
    protected int getAnimalBasePrice() {
        return BASE_PRICE;
    }

    @Override
    protected int getMaximumAge() {
        return MAX_AGE;
    }

    @Override
    public String getType() {
        return "Unicorn";
    }

    @Override
    public boolean canEat(Food f) {
        return f.getType() == Food.STARDUST;
    }

    @Override
    protected Animal spawnBaby() {
        return new Unicorn();
    }

    @Override
    protected int getMaximumLitterSize() {
        return 2;
    }
}
