package edu.team_06;

import edu.team_06.Boundry.ConsoleInput;
import edu.team_06.Controller.GameController;
import edu.team_06.Interface.Playable;
import edu.team_06.Utilities.Settings;

import java.util.InputMismatchException;
/*
 * This is the main body of the program.
 * This program simulates battles between players and their pets. Players choose their
 * pets and the skills to use, and the game runs through all necessary information to see who wins the battles.
 */
public class Main
{
    /*
     * main method to start up the system.
     */
    public static void main(String[] args)
    {
        int seed = 7;
        ConsoleInput cInput = new ConsoleInput();
        try {
            seed = cInput.inputSeed();
            //input number of players
            Settings.SettingsBuilder plyNm = new Settings.SettingsBuilder();
            int size = plyNm.getNumberOfPlayers();
            Playable[] players = new Playable[50];
            for (int i = 0; i < size; i++) {
                players[i] = cInput.inputPlayer(i);
            }
            GameController gameControls = new GameController(players, size);
            gameControls.runGame(seed);
        }
        catch (InputMismatchException e)
        {
            int validSize = cInput.inputInvalidInt();
            while (validSize < 2) {
                validSize = cInput.inputInvalidNumOfPlayers();
            }
            Playable[] players = new Playable[50];
            for (int i = 0; i < validSize; i++) {
                players[i] = cInput.inputPlayer(i);
            }

            GameController gameControls = new GameController(players, validSize);
            gameControls.runGame(seed);
        }
    }
}
