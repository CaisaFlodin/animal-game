package com.company.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private int currentTurn;

    public Game() {
        startGame();
    }

    private void startGame() {
        Rules rules = new Rules();

        System.out.println("Välkommern till de coola spelet!");
        System.out.println("Hur många rundor (5-30)?");

        int numberOfTurns = parseIntInput();
        numberOfTurns = rules.checkNumberOfTurns(numberOfTurns);

        System.out.println("Hur många spelare (1-4)?");

        int numberOfPlayers = parseIntInput();
        numberOfPlayers = rules.checkNumberOfPlayers(numberOfPlayers);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Vad heter spelare " + (i + 1) + "?");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        System.out.println("Då kör vi igång!");

        while (currentTurn <= numberOfTurns) {
            System.out.println("Runda " + currentTurn);

            // Spel logik
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.printf("""
                        Vad vill du göra %s:
                        1. Köpa djur
                        2. Köpa mat
                        3. Mata mina djur
                        4. Para djur
                        5. Avsluta spelet
                        %n""", players.get(i).getName());
                int selection = parseIntInput();


            }

            currentTurn++;
        }
    }

    private int parseIntInput() {
        int intInput;
        try {
            intInput = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Skriv en siffra mellan 5-30!");
            intInput = parseIntInput();
        }
        return intInput;
    }
}
