package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.*;

public class Store {

    // ANIMALS

    public Horse buyAHorse() {
        return new Horse();

    }

    public Hen buyAHen(){
        return new Hen();

    }

    public Pig buyAPig(){
        return new Pig();

    }

    public Sheep buyASheep(){
        return new Sheep();

    }

    public Cow buyACow(){
        return new Cow();

    }

    // FOOD

    public HorseFood buyHorseFood() {
        return new HorseFood();

    }

    public HenFood buyHenFood(){
        return new HenFood();

    }

    public PigFood buyPigFood(){
        return new PigFood();

    }

    public SheepFood buySheepFood(){
        return new SheepFood();

    }

    public CowFood buyCowFood(){
        return new CowFood();

    }
}
