package com.company.game;

import com.company.game.animals.*;
import com.company.game.food.Food;
import com.company.utils.GameStateHandler;
import com.company.utils.InputHandler;
import com.company.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;


public class Game implements Serializable {

    private final static int MIN_PLAYERS = 1, MAX_PLAYERS = 4;
    private final static int MIN_TURNS = 5, MAX_TURNS = 30;

    private final GameState state;

    public Game() {
        OutputHandler.printMessage("\nWelcome to Fantazoo!");
        OutputHandler.printMessage("""
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
            if(loadedState != null) {
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

        OutputHandler.printMessage("\nHow many players (1-4)?");

        int numberOfPlayers = InputHandler.parseUserNumberInput(MIN_PLAYERS, MAX_PLAYERS);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            OutputHandler.printMessage("What's the name of player " + (i + 1) + "?");
            String name = InputHandler.getString();
            players.add(new Player(name));
        }
        OutputHandler.printMessage("How many turns (5-30)?");

        int numberOfTurns = InputHandler.parseUserNumberInput(MIN_TURNS, MAX_TURNS);

        OutputHandler.printMessage("\nLet's go!");
        return new GameState(players, numberOfTurns);
    }


    private void startGame() {

        OutputHandler.clearTheScreen();
        ArrayList<Player> players = state.getPlayers();
        while (!state.gameIsOver()) {
            OutputHandler.printMessage("Round " + state.getCurrentTurn());
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
        endGame();

    }

    private void endGame() {
        ArrayList<Player> players = state.getPlayers();
        if(players.isEmpty()) {
            OutputHandler.printMessage("No one won!");
            return;
        }
        Player bestPlayer = null;
        int mostMoney = -1;
        for(Player p: players) {
            for(Animal a: p.getAnimalList()) {
                p.addProfit(a.getPrice());
            }
            // removes all animals after selling them
            p.getAnimalList().clear();

            if(p.getMoney() > mostMoney) {
                mostMoney = p.getMoney();
                bestPlayer = p;
            }
        }

        OutputHandler.printMessage("Winner: " + bestPlayer.getName() + " with " + bestPlayer.getMoney() + " money!");

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
            OutputHandler.printMessage("What do you want to do " + player.getName() + "?" +
                    """
                1. Go to store
                2. Feed my animals
                3. Breed animals
                4. Check my status
                5. Save game
                6. Finish my turn
                7. Exit game        
                    """);
            selection = InputHandler.parseUserNumberInput(1, 7);
            switch (selection) {
                case 1:
                    showStoreMenu(player);
                    break;
                case 2:
                    showFeedMenu(player);
                    break;
                case 3:
                    attemptBreeding(player);
                    break;
                case 4:
                    OutputHandler.displayPlayerStatus(player);
                    break;
                case 5: //Save
                    saveGame();
                    break;
                case 6: // Finish
                    break;
                case 7:
                    OutputHandler.printMessage("Exiting game");
                    break;
                    //throw new Exception("Finished");
            }
        } while(selection < 6);

        return selection != 7;
    }

    private void saveGame() {
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
        if(player.getAnimalList().isEmpty()) {
            OutputHandler.printError("You have no animals!");
        } else if(player.getFoodList().isEmpty()) {
            OutputHandler.printError("You have no food!");
        } else {
            OutputHandler.printMessage("Which animal would you like to feed?");
            OutputHandler.displayAnimals(player);
            int selection = InputHandler.parseUserNumberInput(player.getAnimalList().size()) - 1;
            Animal animal = player.getAnimal(selection);
            OutputHandler.printMessage("What type of food should you give it?");
            OutputHandler.displayPlayerFoods(player);
            selection = InputHandler.parseUserNumberInput(player.getFoodList().size()) - 1;
            Food food = player.getFood(selection);
            if(animal.canEat(food) && food.foodLeft()) {
                OutputHandler.printMessage("You feed " + animal.getName());
                animal.feed(food);
            }
            else if(!food.foodLeft()) {
                OutputHandler.printError("No food left!");
            } else {
                OutputHandler.printError("This animal cannot eat this food!");
            }

        }
    }

    /**
     * Locks the player in the store selection prompt until they decide to leave.
     * @param player the current player
     */
    private void showStoreMenu(Player player) {

        OutputHandler.printMessage("Welcome to the store!");
        int selection = -1;
        do {
            OutputHandler.printMessage("""                         
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
        int money = player.getMoney();
        Store store = state.getStore();
        ArrayList<Animal> affordedAnimals = store.getAffordedAnimals(money);
        if (affordedAnimals.size() > 0) {
            OutputHandler.printMessage("What animal would you like to buy?");
            OutputHandler.displayAnimals(affordedAnimals);

            int selection = InputHandler.parseUserNumberInput(affordedAnimals.size()) - 1;
            Animal animal = affordedAnimals.get(selection);

            OutputHandler.printMessage("Oh, a " + animal.getType() + "! Which gender?");
            OutputHandler.printMessage("1. Male");
            OutputHandler.printMessage("2. Female");
            selection = InputHandler.parseUserNumberInput(2);
            if (selection == 1) {
                animal.setGender(Animal.MALE);
                OutputHandler.printMessage("You chose a male! What do you want to call him?");
            } else {
                animal.setGender(Animal.FEMALE);
                OutputHandler.printMessage("You chose a female! What do you want to call her?");
            }
            String name = InputHandler.getUniqueAnimalName(player);

            animal.setName(name);


            player.addAnimal(animal);
            player.subtractExpense(animal.getPrice());
            store.replenishAnimal(animal);
        } else {
            OutputHandler.printMessage("You cannot afford any more animals!");
        }
    }
    private void buyAnimalFood(Player player) {

        int money = player.getMoney();
        Store store = state.getStore();
        ArrayList<Food> affordedFoods = store.getAffordedFood(money);
        if(affordedFoods.size() > 0) {
            OutputHandler.printMessage("What food would you like to buy?");
            OutputHandler.displayFoods(affordedFoods);

            int selection = InputHandler.parseUserNumberInput(affordedFoods.size())-1;

            Food food = affordedFoods.get(selection);

            int maximum = player.getMaxQuantity(food);
            if(maximum == 0) {
                OutputHandler.printError("You cannot afford it!");
                return;
            }

            OutputHandler.printMessage("How much food do you want? (Maximum " + maximum + ")");
            int quantity = InputHandler.parseUserNumberInput(1, maximum);

            food.setQuantity(quantity);
            player.subtractExpense(food.getUnitPrice() * food.getQuantity());
            player.addFood(food);

            store.replenishFood(food);

        } else{
            OutputHandler.printError("You cannot afford any food!");
        }

    }

    private void sellAnimal(Player player) {
        if (player.getAnimalList().size() != 0) {
            OutputHandler.printMessage("Which animal do you want to sell?");
            OutputHandler.displayAnimals(player);
            int selection = InputHandler.parseUserNumberInput(player.getAnimalList().size()) - 1;

            Animal animal = player.getAnimal(selection);
            OutputHandler.printMessage("Selling " + animal.getType() + " " + animal.getName()
            + " for " + animal.getPrice() + " money!");
            player.addProfit(animal.getPrice());

            player.removeAnimal(animal);

        } else {
            OutputHandler.printError("You have no animals!");
        }

    }

    private void attemptBreeding(Player player) {
        if(!player.getCanBreedAnimals()) {
            OutputHandler.printError("Your animals cannot breed this round!");
        }
        else{
            ArrayList<Animal> animalList = player.getAnimalList();
            ArrayList<Animal> newAnimals = new ArrayList<>();
            ArrayList<String> usedNames = player.getUsedAnimalNames();
            for(int i = 0; i < animalList.size(); i++) {
                for(int j = i + 1; j < animalList.size(); j++) {

                    Animal a1 = animalList.get(i);
                    Animal a2 = animalList.get(j);

                    if(a1.canBreedWith(a2)) {
                        OutputHandler.printMessage("Attempting to breed " + a1.getType() + " " + a1.getName() +
                                " with " + a2.getType() + " " + a2.getName());
                        ArrayList<Animal> children = a1.attemptBreed(a2);
                        if (!children.isEmpty()) {
                            OutputHandler.printMessage("Hooray! You have " + children.size() + " new " +
                                    a1.getType() + "s!");
                            for (int k = 0; k < children.size(); k++) {
                                OutputHandler.printMessage("What do you want to call " + a1.getType() + " baby " + (k + 1) + "?");
                                String name = InputHandler.getUniqueAnimalName(usedNames);
                                usedNames.add(name);
                                Animal newAnimal = children.get(k);
                                newAnimal.setName(name);
                                newAnimals.add(newAnimal);
                            }
                        }
                        else{
                            OutputHandler.printMessage("No luck! " + a1.getName() + " and " + a2.getName() +
                                    " did not produce any offspring.");
                        }

                    }
                }
            }
            for(Animal a: newAnimals) {
                player.addAnimal(a);
            }
            //Prevents the player from breeding animals again this round.
            player.setAnimalsCanBreed(false);
        }
    }
}
