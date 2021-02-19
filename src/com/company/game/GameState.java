package com.company.game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private int currentTurn;
    private int nbrTurns;
    private int currentPlayer;
    private ArrayList<Player> players;


    public GameState(ArrayList<Player> players, int nbrTurns) {
        currentTurn = 1;
        currentPlayer = 0;
        this.nbrTurns = nbrTurns;
        this.players = players;
    }

    public void nextRound() {
        for(Player p: players) {
            p.roundEnded();
        }
        currentPlayer = 0;
        currentTurn++;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean gameIsOver() {
        return this.currentTurn > nbrTurns;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setNextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}
