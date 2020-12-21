package edu.team_06.Utilities;

import edu.team_06.Boundry.ConsoleInput;
import edu.team_06.Enums.PlayerTypes;
/*
 * This class holds important settings for the game.
 */
public class Settings
{
    private final PlayerTypes defaultPlayerTwoType = PlayerTypes.HUMAN;
    private final int defaultNumberOfFights = 25;
    private PlayerTypes playerTwoType;
    private int numberOfFights;
    /*
     * Constructor for Settings
     */
    private Settings(SettingsBuilder settingsBuilder)
    {
        //check for bad values and assign
        if (settingsBuilder.numberOfFights < 1)
        {
            this.numberOfFights = defaultNumberOfFights;
        }
        else
        {
            this.numberOfFights = settingsBuilder.numberOfFights;
        }

        if (settingsBuilder.playerTwoType == null)
        {
            this.playerTwoType = defaultPlayerTwoType;
        }
        else
        {
            this.playerTwoType = settingsBuilder.playerTwoType;
        }

    }
    /*
     * This returns the number of fights in a battle.
     * @return int
     */
    public int getNumberOfFights()
    {
        return numberOfFights;
    }
    /*
     * This returns the player type of player 2.
     * @return PlayerTypes
     */
    public PlayerTypes getPlayerTwoType()
    {
        return playerTwoType;
    }
    /*
     * This is the settings builder class that makes all the settings.
     */
    public static class SettingsBuilder
    {
        private PlayerTypes playerTwoType;
        private int numberOfFights;
        /*
         * Constructor for SettingsBuilder
         */
        public SettingsBuilder()
        {
        }
        /*
         * This builds the settings
         * @return Settings
         */
        public Settings build()
        {
            return new Settings(this);
        }
        /*
         * This returns the number of fights
         * @return int
         */
        public int getNumberOfFights()
        {
            return numberOfFights;
        }
        /*
         * This returns the player type of player 2
         * @return PlayerTypes
         */
        public PlayerTypes getPlayerTwoType()
        {
            return playerTwoType;
        }
        /*
         * This returns the settings builder with the given number of fights.
         * @return SettingsBuilder
         */
        public SettingsBuilder withNumberOfFights(int numFights)
        {
            this.numberOfFights = numFights;
            return this;
        }
        /*
         * This returns the settings builder with the given type for player 2.
         * @return SettingsBuilder
         */
        public SettingsBuilder withPlayerTwoType(PlayerTypes pType)
        {
            this.playerTwoType = pType;
            return this;
        }

        /*
        returns the number of players
        @returns int
         */
        public int getNumberOfPlayers()
        {
            ConsoleInput cInpt = new ConsoleInput();
            int size = cInpt.inputNumberOfPlayers();
            while (size < 2) {
                size = cInpt.inputInvalidNumOfPlayers();
            }
            return size;
        }
    }
}
