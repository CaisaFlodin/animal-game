package com.company.utils;

import com.company.game.GameState;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GameStateHandler {


    private static final File saveFolder = new File("saves");

    /**
     * Returns the full path to the save file.
     * @param saveFile
     * @return
     */
    private static String fullPathTo(String saveFile) {
        Path filePath = Paths.get(saveFolder.getPath(), saveFile);
        return filePath.toAbsolutePath().toString();
    }

    /**
     * Tries the create the save folder if it does not exist
     * @return true if the folder exists or was created successfully
     */
    private static boolean createSaveFolder() {
        if(!saveFolder.exists()) {
            boolean folderCreated = saveFolder.mkdir();
            return folderCreated;
        }
        return true;
    }

    /**
     * Looks for saved games in the folder "saves" in the project folder,
     * and returns them as an ArrayList.
     *
     * If the folder doesn't exist, or couldn't be created, returns an empty list.
     * @return
     */
    public static ArrayList<String> getSavedGames() {
        ArrayList<String> savedGames = new ArrayList<>();
        if(!createSaveFolder()) {
            return savedGames;
        }
        String[] saveFiles = saveFolder.list();


        if(saveFiles != null) {
            Collections.addAll(savedGames, saveFiles);
        }

        return savedGames;
    }


    /**
     * Attempts to write the GameState to a file given by fileName. The method
     * automatically adds the suffix .sav to the file and places it in the saves folder.
     *
     * @param state The GameState to be saved
     * @param fileName the name of the save file, without file extension
     * @return true if the save file was successfully written, false otherwise.
     */
    public static boolean saveGame(GameState state, String fileName) {
        if(!createSaveFolder()) {
            OutputHandler.printError("Error! Could not create saves folder.");
            return false;
        }
        String path = fullPathTo(fileName + ".sav");
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            OutputHandler.printError("Error! Could not save game: " + error);
            return false;
        }
        return true;
    }

    /**
     * Attempts to read a previously saved GameState object.
     * If successful, returns the GameState object pointed to by fileName.
     *
     * If there are any issues, such as the saves-folder not existing, or the InputStreams fail,
     * the method returns null.
     *
     * @param fileName name of the saved game.
     * @return the GameState object read from fileName, null if it fails.
     */
    public static GameState loadGame(String fileName) {
        if(!createSaveFolder()) {
            OutputHandler.printError("Error! The saves folder could not be created.");
            return null;
        }
        String path = fullPathTo(fileName);
        GameState state;
        try{
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            state = (GameState) in.readObject();
            in.close();
            fileIn.close();
            return state;
        } catch (IOException | ClassNotFoundException e) {
            OutputHandler.printError("Error while loading state in file " + fileName + " : " + e.getMessage());
            return null;
        }
    }

}
