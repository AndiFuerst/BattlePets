package edu.team_06.Controller;

import edu.team_06.Boundry.ConsoleOutput;
import edu.team_06.Entity.Battle;
import edu.team_06.Entity.Fight;
import edu.team_06.Interface.Playable;
/*
 * This class handles battle information, runs rounds, and determines the battle winner.
 */
public class BattleController
{
    private Playable[] playerList;
    private int size;
    private Battle battle;
    private Fight[] fightList;
    private int currentFightSent;
    private FightController fights;


    /*
     * Constructor for battle controller.
     */
    public BattleController(Playable[] players, int size)
    {
        playerList = players;
        this.size = size;
        battle = new Battle();
        currentFightSent = 0;

    }

    /*
    returns the caretaker object
    @return Caretaker
     */
    public Caretaker getCaretaker()
    {
        return this.fights.getCaretaker();
    }


    /*
     * This method will figure out which player has won and then output the results.
     */
    public void handleWin()
    {
        battle.setPlayers(playerList);
        ConsoleOutput.outputBattle(battle);
    }

    /*
     * This method will run all of the fights that are involved within the battle.
     */
    public Battle runBattle(int numberOfFights, int seed)
    {
        battle.setNumberOfFights(numberOfFights);
        fightList = new Fight[numberOfFights];
        fights = new FightController(playerList, size, seed);
        for (int i = 0; i < numberOfFights; i++)
        {
            fightList[i] = fights.runFight();
        }
        battle.setFightList(fightList);
        handleWin();
        return battle;
    }
    /*
     * This method tells the game controller whether there is another fight in the fight list.
     * @return boolean
     */
    public boolean hasNextFight()
    {
        boolean hasFight = false;
        if(currentFightSent < battle.getNumberOfFights() - 1)
            hasFight = true;
        return hasFight;
    }
    /*
     * This method sends the next fight in the fight list to the game controller.
     * @return Fight
     */
    public Fight nextFight()
    {
        Fight toReturn;
        if(hasNextFight())
        {
            toReturn = battle.returnFightAtIndex(currentFightSent);
            currentFightSent++;
        }
        else{
            currentFightSent = 0;
            toReturn = battle.returnFightAtIndex(currentFightSent);
        }
        return toReturn;
    }
}
