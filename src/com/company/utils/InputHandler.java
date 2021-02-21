package com.company.utils;

import com.company.game.Player;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Locks the user into a prompt that requires a number between 1 and numberOfAlternatives (inclusive) to be
     * entered.
     * @param numberOfAlternatives the highest posible value
     * @return the selected value
     */
    public static int parseUserNumberInput(int numberOfAlternatives) {
        return parseUserNumberInput(1, numberOfAlternatives);
    }

    /**
     * Locks the user into a prompt that requires a number between lowerBound and upperBound (inclusive) to be
     * entered.
     * @param lowerBound the lowest possible value (inclusive)
     * @param upperBound the highest possible value (inclusive)
     * @return the selected value
     */
    public static int parseUserNumberInput(int lowerBound, int upperBound) {
        int number = -1; //Sets default value initially
        boolean isDone = false;
        while(!isDone) {
            try{
                number = scanner.nextInt();
                scanner.nextLine(); // Clears the input buffer!
                if (number >= lowerBound && number <= upperBound) {
                    isDone = true;
                    //return number;
                } else {
                    OutputHandler.printError("Number has to be between " + lowerBound + " and " + upperBound);
                }
            } catch (InputMismatchException ignored) {
                OutputHandler.printError("Error! not a number!");
                scanner.nextLine();
            }
        }
        return number;
    }

    /**
     * Simple wrapper method, which returns a string.
     * @return
     */
    public static String getString() {
        return scanner.nextLine();
    }


    public static String getUniqueAnimalName(ArrayList<String> usedNames) {
        String name;
        while(true) {
            name = getString();
            if (usedNames.contains(name)) {
                OutputHandler.printError("You already have an animal called" + name + "." + " Choose another name.");
            }else{
                return name;
            }
        }
    }

    /**
     * Helper method. Locks the game until a name is given, which has not already been used by the player.
     * @param player
     * @return The unique name
     */
    public static String getUniqueAnimalName(Player player) {
        ArrayList<String> usedNames = player.getUsedAnimalNames();
        return getUniqueAnimalName(usedNames);

    }

}
