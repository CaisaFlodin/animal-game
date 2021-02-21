package com.company.game.food;

public class Meat extends Food {

    private final static int BASE_PRICE = 20;
    private final static String FOOD_NAME = "Meat";

    @Override
    public int getUnitPrice() {
        return BASE_PRICE;
    }

    @Override
    public String getName() {
        return FOOD_NAME;
    }

    @Override
    public int getType() {
        return Food.MEAT;
    }


}
