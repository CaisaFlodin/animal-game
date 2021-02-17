package com.company.game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState{

    private int currentTurn;
    private int nbrTurns;

    private ArrayList<Player> players;

    public GameState(ArrayList<Player> players, int nbrTurns) {
        currentTurn = 1;
        this.nbrTurns = nbrTurns;
        this.players = players;
    }

    public void nextRound() {
        for(Player p: players) {
            p.roundEnded();
        }
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

}
