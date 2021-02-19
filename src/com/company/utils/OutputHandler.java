package com.company.utils;

import com.company.game.Player;
import com.company.game.animals.Animal;
import com.company.game.food.Food;

import java.util.ArrayList;

public class OutputHandler {


    private static final int CLEAR_SIZE = 20;


    /**
     * Creates a block of newline characters in the output feed.
     */
    public static void clearTheScreen() {
        System.out.println(System.lineSeparator().repeat(CLEAR_SIZE));
    }

    /**
     * Simple wrapper method which prints the string s. A tad bit superfluous, but it is nice to have
     * all behaviour collected in one place.
     * @param s
     */
    public static void printMessage(String s) {
        System.out.println(s);
    }

    /**
     * Simple wrapper method which prints the string s as an error.
     * @param s
     */
    public static void printError(String s) {
        System.err.println(s);
    }




    /**
     * Lists the food available to a player.
     * @param player
     */
    public static void displayPlayerFoods(Player player) {
        int index = 1;
        for (Food food : player.getFoodList()) {
            printMessage(index + ". " + food.toString());
            //System.out.println(index + "." + food.getName());
            index++;
        }
    }

    public static void displayPlayerStatus(Player player) {
        OutputHandler.printMessage("Player " + player.toString());

        if (player.getAnimalList().size() != 0) {
            OutputHandler.printMessage("Your animals:");
            displayAnimals(player);
        }

        if (player.getFoodList().size() != 0) {
            OutputHandler.printMessage("Your food: ");
            displayPlayerFoods(player);
        }
    }


    /**
     * Lists the status of a player's animals.
     * @param player
     */
    public static void displayAnimals(Player player) {
        displayAnimals(player.getAnimalList());
    }

    /**
     * Lists a sequence of animals.
     * @param animals animals to be listed
     */
    public static void displayAnimals(ArrayList<Animal> animals) {
        int index = 1;
        for (Animal animal : animals) {
            printMessage(index + ". " + animal.toString());
            index++;
        }
    }
}
