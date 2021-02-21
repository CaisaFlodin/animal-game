package com.company.game.food;

public class Stardust extends Food {

    private final static int BASE_PRICE = 40;
    private final static String FOOD_NAME = "Stardust";

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
        return Food.STARDUST;
    }


}