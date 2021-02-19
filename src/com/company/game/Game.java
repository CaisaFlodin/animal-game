package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.Food;
import com.company.utils.GameStateHandler;
import com.company.utils.InputHandler;
import com.company.utils.OutputHandler;
import java.util.ArrayList;


public class Game {

    /**
     * These constants where previously located in the class Rules, but were moved here.
     */
    private final static int MIN_PLAYERS = 1, MAX_PLAYERS = 4;
    private final static int MIN_TURNS = 5, MAX_TURNS = 30;

    private final Store store = new Store();
    private GameState state;

    public Game() {
        System.out.println("\nWelcome to Fantazoo!");
        System.out.println("""
                Select an option:
                1. New game
                2. Load game""");
        int selection = InputHandler.parseUserNumberInput(2);
        if(selection == 1) {
            state = createNewGame();
        }else{
            state = loadGame();

        }
        startGame();
    }

    public GameState loadGame() {
        ArrayList<String> savedGames = GameStateHandler.getSavedGames();
        if(savedGames.size() > 0) {
            OutputHandler.printMessage("Select save file:");
            for(int i = 0; i < savedGames.size(); i++) {
                OutputHandler.printMessage((i + 1) + ". " + savedGames.get(i));
            }
            int selection = InputHandler.parseUserNumberInput(savedGames.size()) - 1;
            GameState loadedState = GameStateHandler.loadGame(savedGames.get(selection));
            if(loadedState!=null) {
                OutputHandler.printMessage("Successfully loaded saved game!");
                return loadedState;
            }else {
                OutputHandler.printError("An error has occured while loading the game. Starting a new one...");
                return createNewGame();
            }
        }
        else{
            OutputHandler.printError("Error! No saved games exist! Starting a new game...");
            return createNewGame();
        }
    }

    public GameState createNewGame() {

        System.out.println("\nHow many players (1-4)?");

        int numberOfPlayers = InputHandler.parseUserNumberInput(MIN_PLAYERS, MAX_PLAYERS);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("What's the name of player " + (i + 1) + "?");
            String name = InputHandler.getString();
            players.add(new Player(name));
        }
        System.out.println("How many turns (5-30)?");

        int numberOfTurns = InputHandler.parseUserNumberInput(MIN_TURNS, MAX_TURNS);

        System.out.println("\nLet's go!");
        return new GameState(players, numberOfTurns);
    }


    private void startGame() {



        OutputHandler.clearTheScreen();

        //// The turns ////
        ArrayList<Player> players = state.getPlayers();
        while (!state.gameIsOver()) {
            System.out.println("\n\nRound " + state.getCurrentTurn());
            for(int i = state.getCurrentPlayer(); i < players.size(); i++) {
                Player player = players.get(i);
                OutputHandler.displayPlayerStatus(player);
                boolean gameContinues = showMainMenu(player);
                if(!gameContinues) {
                    return;
                }
                OutputHandler.clearTheScreen();
                state.setNextPlayer();
            }
            state.nextRound();
        }
    }

    /**
     * Locks the player in a selection prompt until they decide the round has ended,
     * or want to quit the game.
     * @param player the current player
     * @return true if game continues, false otherwise
     */
    private boolean showMainMenu(Player player) {
        int selection = -1;
        do {
            System.out.printf("""         
                What do you want to do %s?:
                1. Go to store
                2. Feed my animals
                3. Breed animals
                4. Sell animals
                5. Check my status
                6. Save game
                7. Finish my turn
                8. Exit game
                """, player.getName());
            selection = InputHandler.parseUserNumberInput(1, 8);
            switch (selection) {
                case 1:
                    showStoreMenu(player);
                    break;
                case 2:
                    showFeedMenu(player);
                    break;
                case 3:
                    //TODO! Implement animal breeding
                    System.err.println("Not yet implemented!");
                    break;
                case 4:
                    //TODO! Implement animal selling
                    System.err.println("Not yet implemented!");
                    break;
                case 5:
                    OutputHandler.displayPlayerStatus(player);
                    break;
                case 6: //Save
                    saveGame(state);
                    break;
                case 7: // Finish
                    break;
                case 8:
                    System.out.println("Spelet avslutas, tack för den här gången!");
                    break;
                    //throw new Exception("Finished");
            }
        } while(selection < 7);

        return selection != 8;
    }

    private void saveGame(GameState state) {
        OutputHandler.printMessage("Enter a save file name:");
        String saveFile = InputHandler.getString();
        if(GameStateHandler.saveGame(state, saveFile)) {
            OutputHandler.printMessage("Game saved successfully!");
        }
        else{
            OutputHandler.printError("Error! Could not save.");
        }
    }

    private void showFeedMenu(Player player) {
        if (player.ownedFoods.size() != 0) {
            System.out.println("\nWhich animal would you like to feed?");
            OutputHandler.displayAnimals(player);
            int selection = InputHandler.parseUserNumberInput(player.ownedAnimals.size());
            Animal animal = player.getAnimal(selection);
            System.out.println("\nWhat type of food should you give it?");
            OutputHandler.displayPlayerFoods(player);
            selection = InputHandler.parseUserNumberInput(player.ownedFoods.size());
            Food food = player.getFood(selection);
            animal.feed(food);
        } else {
            System.out.println("\nYou have no food!");
        }
    }

    /**
     * Locks the player in the store selection prompt until they decide to leave.
     * @param player the current player
     */
    private void showStoreMenu(Player player) {

        System.out.println("Welcome to the store!");
        int selection = -1;
        do {
            System.out.println("""                         
                 What do you want to do?:
                1. Buy animals
                2. Buy animal food
                3. Sell animal
                4. Go back""");
            selection = InputHandler.parseUserNumberInput(1, 4);
            switch(selection) {
                case 1:
                    buyAnimal(player);
                    break;
                case 2:
                    buyAnimalFood(player);
                    break;
                case 3:
                    sellAnimal(player);
                    break;
                case 4:
                    break;
            }
        } while(selection != 4);
    }


    /**
     * Lets the player select an animal for purchase, should they have enough funds.
     * @param player the current player
     */
    private void buyAnimal(Player player) {

        ArrayList<Animal> affordedAnimals = player.getAffordedAnimals(store);
        if (affordedAnimals.size() > 0) {
            System.out.println("What animal would you like to buy?");
            OutputHandler.displayAnimals(affordedAnimals);

            int selection = InputHandler.parseUserNumberInput(affordedAnimals.size()) - 1;
            Animal animal = affordedAnimals.get(selection);

            System.out.println("Oh, a " + animal.getType() + "! Which gender?");
            System.out.println("1. Male");
            System.out.println("2. Female");
            selection = InputHandler.parseUserNumberInput(2);
            if (selection == 1) {
                animal.setGender(Animal.MALE);
                System.out.println("You chose a male! What do you want to call him?");
            } else {
                animal.setGender(Animal.FEMALE);
                System.out.println("You chose a female! What do you want to call her?");
            }
            String name = InputHandler.getUniqueAnimalName(player);

            animal.setName(name);


            player.addAnimal(animal);
            player.subtractExpense(animal.getPrice());
            store.replenishAnimal(animal);
        } else {
            OutputHandler.printMessage("You cannot afford any more animals!");
        }
        /*
        if (player.hasEnoughMoney(player)) {
            System.out.println("What animal would you like to buy?");
            store.displayAnimals();

            int selection = InputHandler.parseUserNumberInput(1,5);
            Animal animal = switch (selection) {
                case 1 -> store.buyUnicorn();
                case 2 -> store.buyGryphon();
                case 3 -> store.buyDragon();
                case 4 -> store.buyLlama();
                case 5 -> store.buySloth();
                default -> null;
            };

            System.out.println("Oh, a " + animal.getType() + "! Which gender?");
            System.out.println("1. Male");
            System.out.println("2. Female");
            selection = InputHandler.parseUserNumberInput(2);
            if (selection == 1) {
                animal.setGender(Animal.MALE);
                System.out.println("You chose a male! What do you want to call him?");
            } else {
                animal.setGender(Animal.FEMALE);
                System.out.println("You chose a female! What do you want to call her?");
            }
            String name = InputHandler.getUniqueAnimalName(player);

            animal.setName(name);


            player.addAnimal(animal);
            player.subtractExpense(animal.getPrice());
        } else {
            System.out.println("You don´t have enough money!");
        }
         */
    }
    private void buyAnimalFood(Player player) {
        ArrayList<Food> affordedFoods = player.getAffordedFood(store);
        if(affordedFoods.size() > 0) {
            OutputHandler.printError("Not yet implemented!");
        } else{
            OutputHandler.printError("You cannot afford any food!");
        }
        /*if (player.hasEnoughMoney(player)) {
            System.out.print("\nWhat food would you like to buy?\n");
            store.displayFoods();

            int selection = InputHandler.parseUserNumberInput(5);

            Food food = switch (selection) {
                case 1 -> store.buyStardust();
                case 2 -> store.buyMeat();
                case 3 -> store.buyLeaves();
                case 4 -> store.buyGrass();
                default -> null;
            };

            player.addFood(food);
            player.subtractExpense(food.getPrice());
        } else {
            System.out.println("You don´t have enough money!");
        }
        */

    }
    private void sellAnimal(Player player) {
        if (player.ownedAnimals.size() != 0) {
            OutputHandler.printMessage("\nWhich animal do you want to sell?");
            OutputHandler.displayAnimals(player);
            int selection = InputHandler.parseUserNumberInput(player.ownedAnimals.size()) - 1;

            Animal animal = player.getAnimal(selection);
            player.addProfit(animal.getPrice());
            player.ownedAnimals.remove(selection);

        } else {
            OutputHandler.printError("You have no animals!");
        }

    }
}
