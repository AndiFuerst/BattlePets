package edu.team_06.Boundry;

import edu.team_06.Entity.*;
import edu.team_06.Enums.PetTypes;
import edu.team_06.Enums.Skills;
import edu.team_06.Interface.Outputable;
import edu.team_06.Interface.Playable;
import edu.team_06.Utilities.Sorter;
import edu.team_06.Utilities.Utils;

import java.util.ArrayList;

/*
 * This class handles all output for the program.
 */
public class ConsoleOutput implements Outputable
{
    /*
     * outputBattle- This method outputs the basic information at the end of a battle.
     */
    public static void outputBattle(Battle battle)
    {
        System.out.println("Fights in this battle: " + battle.getNumberOfFights());
        System.out.println("Winner of this battle: " + battle.getBattleWinner());
        System.out.println();
    }

    /*
     * outputBattle- This method outputs the basic information at the end of a battle.
     */
    public static void outputBattle(String winner, int fights)
    {
        System.out.println("Fights in this battle: " + fights);
        System.out.println("Winner of this battle: " + winner);
        System.out.println();
    }
    /*
     * outputChosenSkill- This method outputs what skill a given player has chosen to select.
     */
    public static void outputChosenSkill(String name, Skills skillChoice)
    {
        System.out.println();
        System.out.println("Player " + name + " chose " + Utils.convertEnumString(skillChoice.toString()));
    }
    /*
     * outputDamage- This method outputs the damage taken by a pet in a given round.
     */
    public static void outputDamage(String petName, Damage damage)
    {
        System.out.println();
        double totalDmg = damage.getConditionalDamage() + damage.getRandomDamage();

        System.out.println("Total damage done to " + petName + " is " + roundToTwoPlaces(totalDmg));
        System.out.println("Conditional damage: " + roundToTwoPlaces(damage.getConditionalDamage()));
        System.out.println("Random damage: " + roundToTwoPlaces(damage.getRandomDamage()));
    }
    /*
    Takes in a round object and outputs all relevant data without prompts
    @param Round
     */
    public static void outputEntireRound(Round round)
    {
        System.out.println();
        Sorter sorter = new Sorter();
        Playable [] playables = round.getDeepCopyPlayables();
        ArrayList<Player> playableArrayList = sorter.sort(playables);
        Skills [] skillsChosen = round.getDeepCopySkillsChosen();
        Damage [] damages = round.getDeepCopyDamageDone();
        //output round number
        outputRoundNumber(round.getRoundNumber());

        //output pet info
        for (int lcv = 0; lcv<playableArrayList.size();lcv++)
        {
                outputPetInfo(
                        playableArrayList.get(lcv).getPetName(),
                        playableArrayList.get(lcv).getPetType(),
                        playableArrayList.get(lcv).getCurrentHp(),
                        playableArrayList.get(lcv).getStartingHp(),
                        playableArrayList.get(lcv).getSkillRechargeTime(Skills.ROCK_THROW),
                        playableArrayList.get(lcv).getSkillRechargeTime(Skills.SCISSORS_POKE),
                        playableArrayList.get(lcv).getSkillRechargeTime(Skills.PAPER_CUT),
                        playableArrayList.get(lcv).getSkillRechargeTime(Skills.SHOOT_THE_MOON),
                        playableArrayList.get(lcv).getSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE));
        }

        //output skills chosen
        for(int lcv = 0;lcv<playableArrayList.size(); lcv++)
        {
                outputChosenSkill(playables[lcv].getPlayerName(), skillsChosen[lcv]);
        }

        //output damage taken
        outputDamage(playables[0].getPetName(), damages[playableArrayList.size()-1]);
        for(int lcv = 0;lcv<playableArrayList.size()-1; lcv++)
        {
                outputDamage(playables[lcv+1].getPetName(), damages[lcv]);
        }

    }
    /*
     * outputFight- This method outputs the basic information at the end of a fight.
     */
    public static void outputFight(Fight fight, Player winner)
    {
        System.out.println();
        System.out.println("Fight Number: " + (fight.getCurrentFightNumber() + 1));
        System.out.println("Fight Winner: " + winner.getPlayerName());
        System.out.println();
    }
    /*
     * outputFight- This method outputs the basic information at the end of a fight.
     */
    public static void outputFight(Fight fight, String winner)
    {
        System.out.println();
        System.out.println("Fight Number: " + (fight.getCurrentFightNumber()));
        System.out.println();
        System.out.println("Fight Winner: " + winner);
        System.out.println();
    }
    /*
     * outputFightNum- This outputs the fight number.
     */
    public static void outputFightNum(int fightNumber)
    {
        System.out.println("Fight " + fightNumber + ".");
       // System.out.println();
    }
    /*
     * outputInvalidNumberOfArguments- This appears to warn the player when there is an inappropriate
     * number of arguments.
     */
    public void outputInvalidNumberOfArguments()
    {
        System.out.println("Invalid number of arguments! There must be 1 argument, or 0 arguments, assuming a random" +
                "seed of 7.");
    }

    /*
    Tells the user there is no previous round
     */
    public static void outputNoPreviousRound()
    {
        System.out.println("There is no previous round.");
    }

    /*
     * outputPetInfo- ONLY USE THIS VARIANT IF THE OTHER IS NOT POSSIBLE outputs the info of a pet
     * REFACTOR NEXT ITERATION
     */
    public static void outputPetInfo(String petName, PetTypes petType, double CurrentHp, double StartingHp, int rockThrowCD, int scissorsPokeCD, int paperCutCD, int shootTheMoonCD, int reversalOfFortuneCD)
    {
        System.out.println();
        System.out.println("Pet: " + petName);
        System.out.println("PetType: " + Utils.convertEnumString(petType.toString()));
        System.out.println("Current Hp: " + roundToTwoPlaces(CurrentHp));
        System.out.println("Starting Hp: " + roundToTwoPlaces(StartingHp));
        System.out.println("Rock Throw Cooldown: " + rockThrowCD);
        System.out.println("Scissors Poke Cooldown: " + scissorsPokeCD);
        System.out.println("Paper Cut Cooldown: " + paperCutCD);
        System.out.println("Shoot The Moon Cooldown: " + shootTheMoonCD);
        System.out.println("Reversal Of Fortune Cooldown: " + reversalOfFortuneCD);
        System.out.println();
    }

    /*
     * outputPetInfo- This is made to output information revolving around a pet.
     */
    public static void outputPetInfo(Pet p)
    {
        System.out.println();
        System.out.println("Pet: " + p.getPetName());
        System.out.println("PetType: " + Utils.convertEnumString(p.getPetType().toString()));
        System.out.println("Current Hp: " + roundToTwoPlaces(p.getCurrentHP()));
        System.out.println("Starting Hp: " + roundToTwoPlaces(p.getStartingHP()));
        System.out.println("Rock Throw Cooldown: " + p.getSkillRechargeTime(Skills.ROCK_THROW));
        System.out.println("Scissors Poke Cooldown: " + p.getSkillRechargeTime(Skills.SCISSORS_POKE));
        System.out.println("Paper Cut Cooldown: " + p.getSkillRechargeTime(Skills.PAPER_CUT));
        System.out.println("Shoot The Moon Cooldown: " + p.getSkillRechargeTime(Skills.SHOOT_THE_MOON));
        System.out.println("Reversal Of Fortune Cooldown: " + p.getSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE));
        System.out.println();
    }
    /*
     * outputPrintLn- This is made to add an extra line in the output when necessary.
     */
    public static void outputPrintLn()
    {
        System.out.println();
    }
    /*
     * outputResults- This isn't supported, but would presumably output the winner of the game..
     */
    public static void outputResults()
    {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    /*
     * outputRoundNumber- This method states which round number the fight is on.
     */
    public static void outputRoundNumber(int numberOfRound)
    {
        System.out.println("Round Number: " + (numberOfRound + 1));//+1 to start at 1.
    }
    /*
     * outputSkillChoices- This method outputs the skill choice of a player.
     */
    public static void outputSkillChoices(String playerName)
    {
        System.out.println();
        System.out.println("Player " + playerName + ", choose your weapon...");
    }
    /*
      outputs the start of the replay
     */
    public static void outputStartOfReplay()
    {
        System.out.println();
        System.out.println("===REPLAY===");
    }

    /*
     * roundToTwoPlaces- This method rounds a double to two decimal places.
     */
    public static double roundToTwoPlaces(double value)
    {
        return Math.round(value * 100.0) / 100.0;
    }
}
