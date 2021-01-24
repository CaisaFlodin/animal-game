package com.company.game;

import com.company.game.animals.Animal;
import com.company.game.food.Food;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private int currentTurn;

    public Game() {
        startGame();
    }

    private void startGame() {

        //// Set up game rules and players ////
        Rules rules = new Rules();
        Store store = new Store();


        System.out.println("Välkommern till de coola spelet!");

        System.out.println("Hur många spelare (1-4)?");

        int numberOfPlayers = parseUserNumberInput(Rules.MAX_PLAYERS);
        numberOfPlayers = rules.checkNumberOfPlayers(numberOfPlayers);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Vad heter spelare " + (i + 1) + "?");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
        System.out.println("Hur många rundor (5-30)?");

        int numberOfTurns = parseUserNumberInput(Rules.MAX_TURNS);
        numberOfTurns = rules.checkNumberOfTurns(numberOfTurns);

        System.out.println("\nLet's go!\n");

        //// The turns ////

        currentTurn = 1;
        boolean isRunning = true;
        while (isRunning && currentTurn <= numberOfTurns) {
            System.out.println("\nRound " + currentTurn);

            // Spellogik
            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = players.get(i);
                System.out.printf("""
                        What do you want to do %s?:
                        1. Go to store
                        2. Feed my animals
                        3. Breed animals
                        4. How to play //TA BORT??
                        5. Exit game
                        """, player.getName());

                int selection = parseUserNumberInput(5);
                switch (selection) {
                    case 1:
                        System.out.println("Welcome to the store! \n");
                        System.out.print("""
                                What do you want to buy?:
                                1. Animals
                                2. Animal food
                                """);
                        selection = parseUserNumberInput(2);
                        if (selection == 1) {
                            System.out.print("""
                                    What animal would you like to buy?
                                    1.Horse
                                    2.Cow
                                    3.Sheep
                                    4.Pig
                                    5.Hen
                                    """);

                            selection = parseUserNumberInput(5);

                            Animal animal = switch (selection) {
                                case 1 -> store.buyAHorse();
                                case 2 -> store.buyACow();
                                case 3 -> store.buyASheep();
                                case 4 -> store.buyAPig();
                                case 5 -> store.buyAHen();
                                default -> null;
                            };
                            System.out.print("""
                                    Which gender?
                                    1. Male
                                    2. Female
                                    """);

                            selection = parseUserNumberInput(2);
                            String name = "";
                            if (selection == 1) {
                                System.out.println("You chose a male! What do you want to call him?");
                            } else {
                                System.out.println("You chose a female! What do you want to call her?");
                            }

                            name = scanner.nextLine();
                            if (player.nameExists(name)) {
                                System.out.println("You already have an animal called choose another name.");
                                name = scanner.nextLine();
                            }

                            animal.setName(name);
                            player.addAnimal(animal);

                        } else if (selection == 2) {
                            System.out.println("""
                                    What food would you like to buy?
                                    1.Horse food
                                    2.Cow food
                                    3.Sheep food
                                    4.Pig food
                                    5.Hen food
                                    """);

                            selection = parseUserNumberInput(5);

                            Food food = switch (selection) {
                                case 1 -> store.buyHorseFood();
                                case 2 -> store.buyCowFood();
                                case 3 -> store.buySheepFood();
                                case 4 -> store.buyPigFood();
                                case 5 -> store.buyHenFood();
                                default -> null;
                            };

                            player.addFood(food);

                        }
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Spelet avslutas, tack för den här gången!");
                        isRunning = false;
                        break;
                }
            }

            currentTurn++;
        }
    }

    private int parseUserNumberInput(int numberOfAlternatives) {
        try {
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            if (number > 0 || number <= numberOfAlternatives) {
                return number;
            } else {
                System.out.println("Enter a valid number!");
                return parseUserNumberInput(numberOfAlternatives);
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a number!");
            return parseUserNumberInput(numberOfAlternatives);
        }
    }
}
