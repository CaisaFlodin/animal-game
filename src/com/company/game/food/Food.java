package com.company.game.food;

import java.io.Serializable;

public abstract class Food implements Serializable {

    public static final int GRASS = 1, LEAVES = 2, MEAT = 3, STARDUST = 4;
    // Health per unit food.
    private static final int HEALTH_VALUE = 10;

    private int quantity;

    public Food() {
        this.quantity = 0;
    }

    /** Returns what one unit of the food costs. Depends on the food.
     * @return the cost of one unit of the food
     */
    public abstract int getUnitPrice();

    /**
     * Returns a integer defined in Food, defining the type of food.
     * @return food integer constant
     */
    public abstract int getType();

    /**
     * @return the name of the food.
     */
    public abstract String getName();

    public int getHealthValue() {
        return HEALTH_VALUE;
    }

    public String toString() {
        return getName() + " : " + getQuantity() + " kg left";
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean foodLeft() {
        return this.quantity > 0;
    }

    public void lowerQuantity() {
        if(foodLeft()) {
            this.quantity = this.quantity - 1;
        }
    }
}
