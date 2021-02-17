package com.company.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Locks the user into a prompt that requires a number between 1 and numberOfAlternatives (inclusive) to be
     * entered.
     * @param numberOfAlternatives the highest posible value
     * @return the selected value
     */
    @Deprecated
    public static int parseUserNumberInput(int numberOfAlternatives) {
        return parseUserNumberInput(1, numberOfAlternatives);
    }

    /**
     * Locks the user into a prompt that requires a number between lowerBound and upperBound (inclusive) to be
     * entered.
     * @param lowerBound the lowest possible value
     * @param upperBound the highest posible value
     * @return the selected value
     */
    @Deprecated
    public static int parseUserNumberInput(int lowerBound, int upperBound) {
        int number = -1; //Sets default value initially
        boolean isDone = false;
        while(!isDone) {
            try{
                number = scanner.nextInt();
                scanner.nextLine();
                if (number >= lowerBound && number <= upperBound) {
                    isDone = true;
                    //return number;
                } else {
                    System.out.println("Number has to be between " + lowerBound + " and " + upperBound);
                }
            } catch (InputMismatchException ignored) {
                System.out.println("Error! not a number!");
            }
        }
        return number;
    }
    @Deprecated
    public static void clearTheScreen() {
        System.out.println(System.lineSeparator().repeat(60));
    }
}
