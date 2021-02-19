package com.company.utils;

import com.company.game.GameState;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GameStateHandler {

    private static String SAVE_FOLDER = "saves";
    private static File saveFolder = new File(SAVE_FOLDER);

    private static String fullPathTo(String saveFile) {
        Path filePath = Paths.get(SAVE_FOLDER, saveFile);
        return filePath.toAbsolutePath().toString();
    }

    public static boolean createSaveFolder() {
        boolean folderCreated;
        folderCreated = saveFolder.mkdir();
        return folderCreated;
    }

    public static ArrayList<String> getSavedGames() {
        ArrayList<String> savedGames = new ArrayList<>();
        if(!saveFolder.exists()) {
            if(!createSaveFolder()) {
                return savedGames;
            }
        }
        String[] saveFiles = saveFolder.list();

        if(saveFiles != null) {
            Collections.addAll(savedGames, saveFiles);
        }

        return savedGames;
    }


    public static boolean saveGame(GameState state, String fileName) {
        if(!saveFolder.exists()) {
            if(!createSaveFolder()) {
                return false;
            }
        }
        String path = fullPathTo(fileName + ".sav");
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            OutputHandler.printError("Could not save game state!");
            return false;
        }
        return true;
    }

    public static GameState loadGame(String fileName) {
        if(!saveFolder.exists()) {
            if(!createSaveFolder()) {
                return null;
            }
        }
        String path = fullPathTo(fileName);

        try{
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GameState state = (GameState) in.readObject();
                return state;

        } catch (IOException | ClassNotFoundException e) {
            OutputHandler.printError("Error while loading state: " + e.getMessage());
        }

        return null;
    }

}
