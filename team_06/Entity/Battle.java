package edu.team_06.Entity;

import edu.team_06.Interface.Playable;
/*
 * This class holds all necessary battle information and handles the winner.
 */
public class Battle
{
    private Playable[] players;
    private int numberOfFights;
    private Fight[] fightList;

    /*
     * Default constructor for battle.
     */
    public Battle()
    {

    }

    /*
     * Figures out the winner of the battle and returns the name of the winner.
     * @return String
     */
    public String getBattleWinner()
    {
        Player playerA = (Player) players[0];
        Player playerB = (Player) players[1];
        String winner;
        if (playerA.getFightWins() > playerB.getFightWins())
        {
            winner = playerA.getPlayerName();
        }
        else if (playerA.getFightWins() < playerB.getFightWins())
        {
            winner = playerB.getPlayerName();
        }
        else
        {
            winner = playerA.getPlayerName();
        }
        return winner;
    }

    /*
     * Returns the number of fights.
     * @return int
     */
    public int getNumberOfFights()
    {
        return numberOfFights;
    }

    /*
     * Sets the number of fights.
     */
    public void setNumberOfFights(int fightNumbers)
    {
        fightList = new Fight[fightNumbers];
        numberOfFights = fightNumbers;
    }

    /*
     * Sets the players list.
     */
    public void setPlayers(Playable[] listOfPlayers)
    {
        players = listOfPlayers;
    }

    /*
     * Sets the fight list.
     */
    public void setFightList(Fight[] listOfFights) { fightList = listOfFights;}
    /*
     * Returns a fight from the fight list at the given index.
     */
    public Fight returnFightAtIndex(int index)
    {
        return fightList[index];
    }

}
