package edu.team_06.Entity;

import edu.team_06.Entity.Round;
/*
 * This holds and handles fight information.
 */
public class Fight
{
    private int currentFightNumber;

    private final int MAX_NUMBER_OF_ROUNDS = 500;

    private Round[] rounds = new Round[MAX_NUMBER_OF_ROUNDS];
    private int size;
    private String winnerName;

    /*
    returns the winner's name
    @return String
     */
    public String getWinnerName()
    {
        return winnerName;
    }

    /*
    sets the winner's name
    @param String
     */
    public void setWinnerName(String winnerName)
    {
        this.winnerName = winnerName;
    }

    /*
     * Constructor for Fight
     */
    public Fight(int currentFightNumber)
    {
        this.currentFightNumber = currentFightNumber;
    }
    /*
     * Returns the current fight number.
     * @return int
     */
    public int getCurrentFightNumber()
    {
        return currentFightNumber;
    }
    /*
     * This sets the current fight number.
     */
    public void setCurrentFightNumber(int currentFightNumber)
    {
        this.currentFightNumber = currentFightNumber;
    }
    /*
     * This sets an array of rounds for replay purposes.
     */
    public void setRounds(Round[] inRounds, int size)
    {
        int i;
        for(i = 0; i < inRounds.length; i++)
        {
            rounds[i] = inRounds[i];
        }
        this.size = size;
    }
    /*
   saves the information into a memento object
   @return Memento
    */
    public Memento saveState()
    {
        Memento memento = new Memento(currentFightNumber, rounds, size, winnerName);
        return memento;
    }

    /*
    Class that saves the information from Round
    */
    public static class Memento
    {
        //information
        private int currentFightNumber;
        private Round[] rounds;
        private int size;
        private String winnerName;

        /*
        Returns the winner's name
        @return String
         */
        public String getWinnerName()
        {
            return winnerName;
        }

        /*
        Constructor
        @param int, Round[]
         */
        private Memento(int currentFightNumber, Round[] rounds, int size, String winnerName)
        {
            this.currentFightNumber = currentFightNumber;
            this.rounds = rounds;
            this.size = size;
            this.winnerName = winnerName;
        }

        /*
        returns the list of Rounds[]
        @return Round[]
         */
        public Round[] getRounds()
        {
            return rounds;
        }

        /*
        returns the size of the array of rounds
        @return int
         */
        public int getSize()
        {
            return size;
        }

    }

}
