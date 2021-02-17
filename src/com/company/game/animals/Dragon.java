package com.company.game.animals;

import com.company.game.food.Food;
import com.company.game.food.Meat;

import java.util.ArrayList;

public class Dragon extends Animal {

private final ArrayList<Food> canEat;

    public Dragon() {
        setPrice(90);
        canEat = new ArrayList<Food>();
    canEat.add(new Meat());
    }

public void animalCanEat(){
    int index = 1;
    for(Food food : canEat){
            System.out.println(index + "." + food.getName());
            index++;
        }

    }
}