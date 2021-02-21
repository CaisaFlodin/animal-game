package com.company.game.animals;

import com.company.game.food.Food;
import com.company.game.food.Meat;
import com.company.game.food.Stardust;

import java.util.ArrayList;

public class Dragon extends Animal {

    private static final int BASE_PRICE = 90;
    private static final int MAX_AGE = 20;

    @Override
    protected int getAnimalBasePrice() {
        return BASE_PRICE;
    }

    @Override
    public String getType() {
        return "Dragon";
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
        return new Dragon();
    }

    @Override
    protected int getMaximumLitterSize() {
        return 2;
    }
}