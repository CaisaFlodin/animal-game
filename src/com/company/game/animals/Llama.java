package com.company.game.animals;

import com.company.game.food.Food;

public class Llama extends Animal {

    private static final int BASE_PRICE = 60;
    private static final int MAX_AGE = 12;

    @Override
    protected int getAnimalBasePrice() { return BASE_PRICE; }

    @Override
    public String getType() {
        return "Llama";
    }

    @Override
    protected int getMaximumAge() {
        return MAX_AGE;
    }

    @Override
    public boolean canEat(Food f) { return f.getType() == Food.GRASS || f.getType() == Food.LEAVES; }

    @Override
    protected Animal spawnBaby() { return new Llama(); }

    @Override
    protected int getMaximumLitterSize() { return 2; }
}
