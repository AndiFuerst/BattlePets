package edu.team_06.Controller;

import edu.team_06.Boundry.ConsoleOutput;
import edu.team_06.Entity.Fight;
import edu.team_06.Entity.Player;
import edu.team_06.Interface.Playable;
import edu.team_06.Controller.RoundController;
import edu.team_06.Controller.Caretaker;
/*
 * This class maintains fights and runs fights. Also handles who the winner of a fight is and
 * increments a player's wins.
 */
public class FightController
{
    private int numberOfFights;
    private RoundController roundController;
    private Fight currentFight;
    private Playable[] playables;
    private int size;
    private int seed;
    private Caretaker caretaker;
    /*
     *Constructor for edu.team_06.Controller.FightController
     */
    public FightController(Playable[] playables, int size, int seed)
    {
        this.numberOfFights = 0;
        this.playables = playables;
        this.size = size;
        this.seed = seed;
        caretaker = new Caretaker();
    }

    /*
    returns the Caretaker object
    @return Caretaker
     */
    public Caretaker getCaretaker()
    {
        return caretaker;
    }

    /*
     * Returns the number of fights to the battle.
     * @return int
     */
    public int getNumberOfFights()
    {
        return numberOfFights;
    }

    /*
     * Increments winner fight wins and outputs the winner of the fight.
     */
    public void handleWin(Player winner)
    {
        currentFight.setWinnerName(winner.getPlayerName());
        winner.incrementFightWins();
        Fight currentFight = new Fight(numberOfFights);
        ConsoleOutput.outputFight(currentFight, winner);
    }

    /*
     * Sets up every round in a fight. Handles who the winner of the fight is.
     * @return Fight
     */
    public Fight runFight()
    {
        for (Playable p: this.playables)
        {
            if(p != null)
            {
                p.reset();
            }

        }
        roundController = new RoundController(playables, size, seed);
        currentFight = new Fight(numberOfFights + 1);
        ConsoleOutput.outputFightNum(numberOfFights + 1);//+1 to start at 1.
        Playable winner = roundController.runRound();
        while (winner == null)
        {
            winner = roundController.runRound();
            ConsoleOutput.outputPrintLn();
        }
        currentFight.setRounds(roundController.getRounds(), roundController.getNumberOfRounds());
        handleWin((Player) winner);
        numberOfFights++;
        caretaker.addMemento(currentFight.saveState());
        return currentFight;
    }
}
