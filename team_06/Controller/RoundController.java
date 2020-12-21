package edu.team_06.Controller;

import edu.team_06.Boundry.ConsoleOutput;
import edu.team_06.Entity.Damage;
import edu.team_06.Entity.Player;
import edu.team_06.Entity.Round;
import edu.team_06.Enums.Skills;
import edu.team_06.Interface.Playable;
import edu.team_06.Utilities.Sorter;

import java.util.ArrayList;
import java.util.Collections;

/*
 * RoundController- This is a class to perform necessary operations in order to run a round of the fights.
 * This includes determining who the next player is, calling to take skill choice, calling to output
 * skill choice, decrementing skill recharge times, and seeing if the fight is over.
 */
public class RoundController
{
    private final int MAX_NUMBER_PLAYABLES = 50;
    private final int MAX_NUMBER_ROUNDS = 500;
    private int numberOfRounds;
    private Round currentRound;
    private DamageCalculator damageCalculator;
    private Round[] rounds = new Round[MAX_NUMBER_ROUNDS];
    private int seed;
    private double[] randomDamageTaken = new double[MAX_NUMBER_PLAYABLES];
    private double[] randomDamageDealt = new double[MAX_NUMBER_PLAYABLES];

    //Original Information
    private Playable[] originalPlayables = new Playable[MAX_NUMBER_PLAYABLES];
    private int originalSize;

    /*
    Constructor
    Creates a Round Controller
    @param Playable[], int, int
    @return RoundController
     */
    public RoundController(Playable[] playables, int size, int seed)
    {
        numberOfRounds = 0;
        this.seed = seed;
        damageCalculator = new DamageCalculator(seed);
        originalPlayables = playables;
        originalSize = size;
    }

    /*
    Runs the Round
        - Gets the skill choices from each playable
        - Displays each skill choice
        - Applies the damage to each playable
        - Decrements recharge times
        - Checks if the fight has ended
    Returns the last Playable alive -- if only one Playable is alive
    Returns the Playable with the highest HP -- if all Playables have died
    Returns NULL if there are multiple Playables alive
    @return Playable
     */
    public Playable runRound()
    {
        Round round;
        try
        {
            round = new Round(rounds[numberOfRounds].getPlayables(), rounds[numberOfRounds].getSize(), numberOfRounds);
        }
        catch(Exception e)
        {
            round = new Round(originalPlayables,originalSize,0);
        }
        currentRound = round;
        currentRound.getAlive();
        //decrementRechargeTimes();
        ConsoleOutput.outputRoundNumber(numberOfRounds);

        outputPets();

        //get skills
        getSkills();

        //which skill is chosen
        displaySkillChoices();

        //calculate/Apply edu.team_06.Entity.Damage
        calculateDamage();
        printDamage();
        //decrementRechargeTimes();
        currentRound.saveState();
        decrementRechargeTimes();
        setRechargeTimes();
        applyDamage();

        rounds[numberOfRounds] = currentRound;

        //numberOfRounds++;

        currentRound.setRoundNumber(numberOfRounds++);

        //Check if fight has ended
        return isFightDone();
    }

    /*
    Gets the Skill Choice for each Playable
     */
    private void getSkills()
    {
        int i = 0;
        while (currentRound.isNextPlayable())
        {
            ConsoleOutput.outputSkillChoices(currentRound.getCurrentPlayable().getPlayerName());
            currentRound.setSkillsChosenAt(i, currentRound.getCurrentPlayable().chooseSkill());
            if(currentRound.getSkillsChosenAt(i) == Skills.SHOOT_THE_MOON)
            {
                Player predict = (Player)currentRound.getCurrentPlayable();
                predict.setSkillPrediction();
            }
            i++;
            currentRound.nextPlayable();
        }
        ConsoleOutput.outputSkillChoices(currentRound.getCurrentPlayable().getPlayerName());
        currentRound.setSkillsChosenAt(i, currentRound.getCurrentPlayable().chooseSkill());
        if(currentRound.getSkillsChosenAt(i) == Skills.SHOOT_THE_MOON)
        {
            Player predict = (Player)currentRound.getCurrentPlayable();
            predict.setSkillPrediction();
        }
        i++;
        currentRound.nextPlayable();
    }

    /*
    Displays the Skill Choice for each Playable
     */
    private void displaySkillChoices()
    {
        int i = 0;
        while (currentRound.isNextPlayable())
        {
            ConsoleOutput.outputChosenSkill(currentRound.getCurrentPlayable().getPlayerName(),
                    currentRound.getSkillsChosenAt(i));
            i++;
            currentRound.nextPlayable();
        }
        ConsoleOutput.outputChosenSkill(currentRound.getCurrentPlayable().getPlayerName(),
                currentRound.getSkillsChosenAt(i));
        i++;
        currentRound.nextPlayable();
    }

    /*
    Applies the Damage to each Playable
     */
    private void calculateDamage()
    {
        int i = 0;
        int j = 1;
        //Damage temp;
        while (currentRound.isNextPlayable())
        {
            //DamageCalculator damageCalculator = new DamageCalculator(seed);
            Damage temp = damageCalculator.calculateDamage(currentRound.getCurrentPlayable(), currentRound.getNextPlayable(),
                    currentRound.getSkillsChosenAt(i), currentRound.getSkillsChosenAt(j),
                    getRandomDamageTakenAt(i), getRandomDamageDealtAt(i));
            currentRound.setDamageDoneAt(i, temp);

            setRandomDamageDealtAt(i, temp.getRandomDamage());
            setRandomDamageTakenAt(j, temp.getRandomDamage());

            i++;
            j++;
            currentRound.nextPlayable();
        }
        //DamageCalculator damageCalculator = new DamageCalculator(seed);
        Damage temp = damageCalculator.calculateDamage(currentRound.getCurrentPlayable(), currentRound.getNextPlayable(),
                currentRound.getSkillsChosenAt(i), currentRound.getSkillsChosenAt(0),
                getRandomDamageTakenAt(i), getRandomDamageDealtAt(i));
        currentRound.setDamageDoneAt(i, temp);

        setRandomDamageDealtAt(i, temp.getRandomDamage());
        setRandomDamageTakenAt(0, temp.getRandomDamage());

        i++;
        j++;
        //currentRound.nextPlayable();
    }

    /*
    Displays the Damage for a round
     */
    public void printDamage()
    {
        ConsoleOutput.outputDamage(currentRound.getNextPlayable().getPetName(), currentRound.getDamageDoneAt(currentRound.getSize() - 1));
        currentRound.nextPlayable();
        int i = 0;
        while(currentRound.isNextPlayable())
        {
            ConsoleOutput.outputDamage(currentRound.getNextPlayable().getPetName(), currentRound.getDamageDoneAt(i));
            i++;
            currentRound.nextPlayable();
        }
        currentRound.nextPlayable();
    }

    /*
    Sets the Recharge times for the round
     */
    public void setRechargeTimes()
    {
        int i = 0;
        while (currentRound.isNextPlayable())
        {
            if (currentRound.getSkillsChosenAt(i) == Skills.REVERSAL_OF_FORTUNE)
            {
                currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 5);
            }
            else if (currentRound.getSkillsChosenAt(i) == Skills.SHOOT_THE_MOON)
            {
                currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 5);
            }
            else
            {
                currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 1);
            }
            i++;
            currentRound.nextPlayable();
        }
        if (currentRound.getSkillsChosenAt(i) == Skills.REVERSAL_OF_FORTUNE)
        {
            currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 5);
        }
        else if (currentRound.getSkillsChosenAt(i) == Skills.SHOOT_THE_MOON)
        {
            currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 5);
        }
        else
        {
            currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 1);
        }
        //currentRound.getCurrentPlayable().setRechargeTime(currentRound.getSkillsChosenAt(i), 1);
        currentRound.nextPlayable();
    }

    /*
    applies the damage to all of the playables
     */
    public void applyDamage()
    {
        int i = 0;
         while (currentRound.isNextPlayable())
         {
             currentRound.applyDamage(currentRound.getDamageDoneAt(i), currentRound.getNextPlayable());
             currentRound.nextPlayable();
             i++;
         }
        currentRound.applyDamage(currentRound.getDamageDoneAt(i), currentRound.getNextPlayable());
        currentRound.nextPlayable();
        i++;
    }

    /*
    Decrements the Recharge Times for Each Playable
     */
    private void decrementRechargeTimes()
    {
        while (currentRound.isNextPlayable())
        {
            currentRound.getCurrentPlayable().decrementRechargeTimes();
            currentRound.nextPlayable();
        }
        currentRound.getCurrentPlayable().decrementRechargeTimes();
        currentRound.nextPlayable();
    }

    /*
    Determines if the fight is over
    If only one playable is alive -- Returns Playable
    If all Playables are dead -- Returns Playable with highest HP
    If multiple Playables are alive -- Returns NULL
    @return Playable
     */
    private Playable isFightDone()
    {
        currentRound.getAlive();
        Playable playable = null;
        if (currentRound.getSize() == 0)
        {
            return getHighestHPPlayable();
        }
        if (currentRound.getSize() == 1)
        {
            for (int i = 0; i < originalSize; i++)
            {
                if(originalPlayables[i].getPlayableId() == currentRound.getCurrentPlayable().getPlayableId())
                    playable = currentRound.getCurrentPlayable();
            }
            return playable;
        }
        else
        {
            return null;
        }
    }

    /*
    Determines the Playable with the highest HP
    @return Playable
     */
    public Playable getHighestHPPlayable()
    {
        double max = originalPlayables[0].getCurrentHp();
        int maxIndex = 0;
        for (int i = 0; i < originalSize; i++)
        {
            if (originalPlayables[i].getCurrentHp() > max)
            {
                max = originalPlayables[i].getCurrentHp();
                maxIndex = i;
            }
        }
        return originalPlayables[maxIndex];
    }

    /*
    Returns the NumberOfRounds
    @return int
     */
    public int getNumberOfRounds()
    {
        return numberOfRounds;
    }

    /*
    Sets the number of Rounds to the given int
    @param int
     */
    public void setNumberOfRounds(int numberOfRounds)
    {
        this.numberOfRounds = numberOfRounds;
    }

    /*
    Returns the array of rounds
    @return Round[]
     */
    public Round[] getRounds()
    {
        return rounds;
    }

    private void outputPets()
    {
        Sorter sort = new Sorter();

        ArrayList<Player> tempArrayList = sort.sort(currentRound.getPlayables());

        /*
        for(int lcv = 0; lcv < currentRound.getPlayables().length;lcv++)
        {
            if (currentRound.getPlayables()[lcv] != null)
            {
                tempArrayList.add((Player) currentRound.getPlayables()[lcv]);
            }
        }
         */
        //Collections.sort(tempArrayList);
        for (Player p: tempArrayList)
        {
            ConsoleOutput.outputPetInfo(p.getPlayerPet());
        }

    }

    /*
  returns the Random Damage Taken at the given index
  @param int
  @return double
   */
    public double getRandomDamageTakenAt(int index)
    {
        return randomDamageTaken[index];
    }

    /*
    sets the Random Damage Taken at the given index
    @param int, double
     */
    public void setRandomDamageTakenAt(int index,double randomDamageTaken)
    {
        this.randomDamageTaken[index] = randomDamageTaken + this.randomDamageTaken[index];
    }

    /*
    Returns the Random Damage Dealt at the given index
    @param int
    @return double
     */
    public double getRandomDamageDealtAt(int index)
    {
        return randomDamageDealt[index];
    }

    /*
    Sets the Random Damage Dealt at the given index
    @param int, double
     */
    public void setRandomDamageDealtAt(int index, double randomDamageDealt)
    {
        this.randomDamageDealt[index] = randomDamageDealt + this.randomDamageDealt[index];
    }
}
