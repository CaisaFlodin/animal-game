package com.company.game.animals;

import com.company.game.food.Food;
import com.company.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal implements Serializable {

    // Constants used to define an animal's gender.
    public static final int UNDEFINED = 0, MALE = 1, FEMALE = 2;

    private String name;
    private int gender;
    private int health;
    private int age;
    private int lastDamage;
    // This attribute starts off as false, otherwise a player can buy animals, breed them,
    // and immediately sell them with no loss in money.
    private boolean canBreedThisRound;

    public Animal() {
        health = 100;
        canBreedThisRound = false;
        gender = UNDEFINED;
        lastDamage = 0;
        age = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the animal's gender.
     *
     * @return gender string
     */
    public String getGenderString() {
        return switch (gender) {
            case MALE -> "male";
            case FEMALE -> "female";
            default -> ""; // If the animal's gender is undefined
        };
    }


    /**
     * Sets the animal's gender to one of the gender constants. If another value is used,
     * the gender is set to undefined.
     *
     * @param gender one of the gender constants defined in Animal.
     */
    public void setGender(int gender) {
        if (gender == Animal.MALE || gender == Animal.FEMALE) {
            this.gender = gender;
        } else {
            this.gender = UNDEFINED;
        }
    }


    /**
     * Returns the price of the animal type. It is a constant value which depends on the type
     * of the animal.
     *
     * @return the base price of the animal.
     */
    protected abstract int getAnimalBasePrice();

    /**
     * Returns the animal's maximum age. The maximum age depends upon the animal.
     *
     * @return
     */
    protected abstract int getMaximumAge();

    /**
     * Returns the current price of the animal. It changes depending on how healthy the animal is.
     *
     * @return the current price of the animal.
     */
    public int getPrice() {
        // At maximum age, the animal is sold at half the price
        double ageMultiplier = 1 - 0.5 * ((double) age / getMaximumAge());
        // The price of an animal goes down to 0 at zero health
        double healthMultiplier = (double) health / 100;
        int price = (int) (ageMultiplier * healthMultiplier * getAnimalBasePrice());
        return price;
        //return (getAnimalBasePrice() * health) / 100;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        if (health <= 0) {
            return true;
        }
        if (this.age >= getMaximumAge()) {
            return true;
        }
        return false;
    }

    public void nextRound() {
        hurt();
        this.age = this.age + 1;
        this.canBreedThisRound = true;
    }


    /**
     * checks if an animal can breed this round.
     *
     * @return true if the animal can breed
     */
    public boolean canBreed() {
        return canBreedThisRound;
    }

    /**
     * Checks if this animal is compatible with another animal.
     *
     * @param other another animal
     * @return true if they are of the same type and different gender.
     */
    public boolean canBreedWith(Animal other) {
        if (!this.getType().equals(other.getType())) {
            return false;
        }
        if (this.gender == other.gender) {
            return false;
        }
        return true;
    }

    /**
     * Returns the type of this animal as a string.
     *
     * @return
     */
    public abstract String getType();

    public void hurt() {
        Random random = new Random();
        int range = 30 - 10 + 1;
        int damage = random.nextInt(range) + 10;
        lastDamage = damage;
        health = health - damage;
    }

    public void feed(Food food) {
        if (health == 100) {
            OutputHandler.printError("The animal is already healthy!");
        } else if (food.foodLeft()) {
            health = health + food.getHealthValue();
            if(health>100){
                health = 100;
            }
            food.lowerQuantity();
        } else {
            OutputHandler.printError("No food left!");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(getName()).append(" : ");
        }
        sb.append(getGenderString())
                .append(" ")
                .append(getType())
                .append(", Health: ")
                .append(getHealth());

        if (lastDamage > 0) {
            sb.append(" (Lost ").append(lastDamage).append(" health points last round)");
        }

        sb.append(", Age: ")
                .append(getAge())
                .append(" (Maximum ")
                .append(getMaximumAge())
                .append(")")
                .append(", Selling price: ")
                .append(getPrice());
        return sb.toString();
    }

    /**
     * Method which tells whether an animal can eat a certain type of food or not.
     *
     * @param f the food to be checked
     * @return true if the food may be eaten
     */
    public abstract boolean canEat(Food f);

    /**
     * Attempts to spawn babies. The maximum number of babies spawned is set to
     * the litter size through the abstract method getMaximumLitterSize.
     * <p>
     * If the animals successfully breed, they may not breed again this round.
     *
     * @return a list of spawned animal babies.
     */
    public ArrayList<Animal> attemptBreed(Animal partner) {


        ArrayList<Animal> newAnimals = new ArrayList<>();
        // If the animals have exhausted their ability to breed this round, return prematurely
        if (!this.canBreed() || !partner.canBreed()) {
            return newAnimals;
        }

        Random randomGenerator = new Random();
        int maxNbrBabies = getMaximumLitterSize();

        for (int i = 0; i < maxNbrBabies; i++) {

            //Generates the numbers 0 or 1 randomly, simulating the 50% chance of spawning a baby.
            int nbr = randomGenerator.nextInt(2);

            if (nbr == 1) { //A baby!
                Animal newAnimal = this.spawnBaby();

                //Generates the numbers 0 or 1 randomly, simulating the 50% chance of being either male or female.
                int gender = randomGenerator.nextInt(2);
                if (gender == 0) {
                    newAnimal.setGender(Animal.MALE);
                } else {
                    newAnimal.setGender(Animal.FEMALE);
                }
                newAnimals.add(newAnimal);
            }
        }

        //If the animals produced babies, prevent them from breeding further this round.
        if (!newAnimals.isEmpty()) {
            this.canBreedThisRound = false;
            partner.canBreedThisRound = false;
        }

        return newAnimals;
    }

    /**
     * Creates a new animal. Abstract since the different kinds of animals will produce animals of their own kind.
     *
     * @return a new Animal of the same type as its parent.
     */
    protected abstract Animal spawnBaby();

    /**
     * return max number of babies in a litter for the animal. Depends on the type of animal.
     *
     * @return an integer value of the maximum number of babies in a litter,
     */
    protected abstract int getMaximumLitterSize();

    public void setCanBreed(boolean canBreed) {
        this.canBreedThisRound = canBreed;
    }

    public int getAge() {
        return age;
    }
}
