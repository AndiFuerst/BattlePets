package edu.team_06.Controller;

import edu.team_06.Boundry.ConsoleInput;
import edu.team_06.Entity.Battle;
import edu.team_06.Entity.Player;
import edu.team_06.Interface.Playable;
/*
 * This class is made to handle running battles and resetting information after battles.
 * It also asks the player if they want to play another battle with the same settings.
 */
public class GameController
{
    public DamageCalculator damageCalc;
    int size;
    BattleController bttlCtlr;
    private Playable[] players;

    /*
     *Constructor for edu.team_06.Controller.GameController
     */
    public GameController(Playable[] inPlayers, int size)
    {
        players = inPlayers;
        this.size = size;
        bttlCtlr = new BattleController(players, size);
    }

    /*
     * Resets the players information for all players in the players list.
     */
    public void resetPlayerInfo()
    {
        for (int i = 0; i < size; i++)
        {
            players[i].reset();
            Player resetWins = (Player) players[i];
            resetWins.resetFightWins();
        }
    }

    /*
     * Takes in a seed and number of fights.
     * Runs a battle with the given seed and number of fights. Checks if player wants to play the game again.
     */
    public void runGame(int seed)
    {
        ConsoleInput input = new ConsoleInput();
        boolean playAgain = true;
        while (playAgain)
        {
            resetPlayerInfo();

            int numOfFights = input.inputFightNumber();

            Battle battle = bttlCtlr.runBattle(numOfFights, seed);
            String battleWinner = battle.getBattleWinner();

            if(input.inputReplay())
            {
                ReplayController replayController = new ReplayController(bttlCtlr.getCaretaker());
                replayController.runReplay(battleWinner);
            }


            playAgain = input.inputPlayAgain();
        }

    }
}
