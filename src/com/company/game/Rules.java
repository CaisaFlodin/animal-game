package com.company.game;

public class Rules {

    public static final int STARTING_MONEY = 1000;
    public static final int MIN_PLAYERS = 1;
    public static final int MAX_PLAYERS = 4;
    public static final int MIN_TURNS = 5;
    public static final int MAX_TURNS = 30;

    public int checkNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers > MAX_PLAYERS) {
            numberOfPlayers = MAX_PLAYERS;
        } else if (numberOfPlayers < MIN_PLAYERS) {
            numberOfPlayers = MIN_PLAYERS;
        }
        return numberOfPlayers;
    }

    public int checkNumberOfTurns(int numberOfTurns) {
        if (numberOfTurns > MAX_TURNS) {
            numberOfTurns = MAX_TURNS;
        } else if (numberOfTurns < MIN_TURNS) {
            numberOfTurns = MIN_TURNS;
        }
        return numberOfTurns;
    }
}
