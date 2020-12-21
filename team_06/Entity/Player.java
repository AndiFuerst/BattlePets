package edu.team_06.Entity;

import edu.team_06.Boundry.ConsoleInput;
import edu.team_06.Enums.PetTypes;
import edu.team_06.Enums.PlayerTypes;
import edu.team_06.Enums.Skills;
import edu.team_06.Interface.Playable;

import java.util.Random;

public class Player implements Playable , Comparable
{
    private String name;
    private PlayerTypes playerType;
    private int fightWins;
    private Pet playerPet;
    private int playableId;
    private Skills predicted = null;

    //Constructor for edu.team_06.Entity.Player class
    public Player(String playerName, Pet playPet, PlayerTypes playType, int Id)
    {
        name = playerName;
        playerPet = playPet;
        playerType = PlayerTypes.HUMAN;
        playableId = Id;
    }

    //This calculates the HP Percentage based on current and starting HP.
    @Override
    public double calculateHpPercent()
    {
        double currentHp = getCurrentHp();
        double startingHp = getStartingHp();
        double percent = currentHp / startingHp;
        percent = percent * 100;
        return percent;
    }

    //This calls upon the input class to get the skill that a player chooses and returns which skill has been chosen.
    @Override
    public Skills chooseSkill()
    {
        ConsoleInput input = new ConsoleInput();
        int RockCD = this.playerPet.getSkillRechargeTime(Skills.ROCK_THROW);
        int PaperCD = this.playerPet.getSkillRechargeTime(Skills.PAPER_CUT);
        int ScissorCD = this.playerPet.getSkillRechargeTime(Skills.SCISSORS_POKE);
        int ReversalOfFortuneCD = this.playerPet.getSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE);
        int ShootTheMoonCD = this.playerPet.getSkillRechargeTime(Skills.SHOOT_THE_MOON);
        Skills chosenSkill = input.inputSkillChoice(RockCD, PaperCD, ScissorCD, ReversalOfFortuneCD, ShootTheMoonCD);
        return chosenSkill;
    }

    @Override
    public int compareTo(Object o)
    {
        double compareHP = ((Player)o).getCurrentHp();
        return (int) (compareHP - this.getCurrentHp());
    }

    //This decrements each of the recharge times above 0 by 1 per round.
    @Override
    public void decrementRechargeTimes()
    {
        playerPet.decrementRechargeTimes();
    }

    //This returns current HP.
    @Override
    public double getCurrentHp()
    {
        return playerPet.getCurrentHP();
    }

    //This returns the number of fight wins.
    public int getFightWins()
    {
        return fightWins;
    }

    //This returns the pet name.
    @Override
    public String getPetName()
    {
        return playerPet.getPetName();
    }

    //This returns the pet type.
    @Override
    public PetTypes getPetType()
    {
        return playerPet.getPetType();
    }
    //This returns the playable ID.
    @Override
    public int getPlayableId()
    {
        return this.playableId;
    }

    //This returns the player name.
    @Override
    public String getPlayerName()
    {
        return name;
    }
    //This returns the Player's pet.
    public Pet getPlayerPet() { return this.playerPet;}

    //This returns the player type.
    @Override
    public PlayerTypes getPlayerType()
    {
        return playerType;
    }

    //This predicts the next skill that the opponent is going to use.
    @Override
    public Skills getSkillPrediction() { return predicted; }

    /*
    sets the skill prediction
     */
    public void setSkillPrediction()
    {
        ConsoleInput cIN = new ConsoleInput();
        predicted = cIN.predictSkillChoice();
    }

    //This returns the skill recharge time for the given skill.
    @Override
    public int getSkillRechargeTime(Skills skill)
    {
        return playerPet.getSkillRechargeTime(skill);
    }

    //This gets the starting HP amount from the edu.team_06.Entity.Pet.
    @Override
    public double getStartingHp()
    {
        return playerPet.getStartingHP();
    }

    //This increments the player's fight wins by one when they win a fight.
    public void incrementFightWins()
    {
        fightWins++;
    }

    //This checks if the pet is awake and returns false if the pet is awake.
    @Override
    public boolean isAwake()
    {
        return playerPet.isAwake();
    }

    //This resets the pet's HP and Skill Cooldown times.
    @Override
    public void reset()
    {
        resetHp();
        resetCooldowns();
    }

    //This resets the pet's cooldowns.
    private void resetCooldowns()
    {
        playerPet.resetRechargeTime();
    }

    //This resets the fight wins to 0 after a battle.
    public void resetFightWins()
    {
        fightWins = 0;
    }



    //This calls upon the pet to reset HP to the starting amount.
    @Override
    public void resetHp()
    {
        playerPet.resetHp();
    }

    /*
    Sets the playableId
    @param int
     */
    public void setPlayableId(int playableId)
    {
        this.playableId = playableId;
    }

    //This sets the recharge time for the given skill to be the given integer.
    @Override
    public void setRechargeTime(Skills skill, int rechargeTime)
    {
        playerPet.setRechargeTime(skill, rechargeTime);
    }

    //This subtracts the given HP from Current HP of a pet, and then updates Current HP to that amount.
    @Override
    public void updateHp(double hp)
    {
        double currentHP = playerPet.getCurrentHP();
        currentHP = currentHP - hp;
        this.playerPet.setCurrentHP(currentHP);
    }
}
