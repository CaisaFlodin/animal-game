package com.company.game;

import com.company.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private int currentTurn;
    private int nbrTurns;
    private int currentPlayer;
    private Store store;
    private ArrayList<Player> players;

    public GameState(ArrayList<Player> players, int nbrTurns) {
        currentTurn = 1;
        currentPlayer = 0;
        this.nbrTurns = nbrTurns;
        this.players = players;
        this.store = new Store();
    }

    public void nextRound() {

        for(Player p: players) {
            p.nextRound();
        }

        ArrayList<Player> deletedPlayers = new ArrayList<>();
        for(Player p: players) {
            if(!p.canContinue(store)){
                deletedPlayers.add(p);
            }
        }
        for(Player p: deletedPlayers){
            OutputHandler.printMessage("Player " + p.getName() + " has lost!");
            this.players.remove(p);
        }

        currentPlayer = 0;
        currentTurn++;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean gameIsOver() {
        return this.currentTurn > nbrTurns || players.size()==0;
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

    public Store getStore() {
        return store;
    }
}
