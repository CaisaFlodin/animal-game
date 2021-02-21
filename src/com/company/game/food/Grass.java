package com.company.game.food;

public class Grass extends Food {

    private final static int BASE_PRICE = 10;
    private final static String FOOD_NAME = "Grass";

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
        return Food.GRASS;
    }


}
