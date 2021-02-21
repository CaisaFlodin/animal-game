package com.company.game.food;

public class Leaves extends Food {

    private final static int BASE_PRICE = 15;
    private final static String FOOD_NAME = "Leaves";

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
        return Food.LEAVES;
    }
}
