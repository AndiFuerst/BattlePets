package edu.team_06.Entity;

import edu.team_06.Enums.PetTypes;
import edu.team_06.Enums.Skills;
/*
 * This stores all necessary pet information and returns it.
 */
public class Pet
{
    private PetTypes petType;
    private double currentHP;
    private double startingHP;
    private SkillInfo skillRechargeTimes;
    private String petName;

    /*
     * Constructor for edu.team_06.Entity.Pet class.
     */
    public Pet(Double startHP, String name, PetTypes type)
    {
        startingHP = startHP;
        currentHP = startingHP;
        petName = name;
        petType = type;
        skillRechargeTimes = new SkillInfo();
    }
    /*
    Constructor
    @param double, double, String, PetTypes, int, int, int, int, int
     */
    public Pet(double startHP, double currentHPIn, String name, PetTypes type, int rechRock, int rechSci, int rechPap, int rechSho, int rechRev)
    {
        startingHP = startHP;
        currentHP = currentHPIn;
        petName = name;
        petType = type;
        SkillInfo skillInfo = new SkillInfo();
        skillInfo.setSkillRechargeTime(Skills.ROCK_THROW, rechRock);
        skillInfo.setSkillRechargeTime(Skills.PAPER_CUT, rechPap);
        skillInfo.setSkillRechargeTime(Skills.SCISSORS_POKE, rechSci);
        skillInfo.setSkillRechargeTime(Skills.SHOOT_THE_MOON, rechSho);
        skillInfo.setSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE, rechRev);
        skillRechargeTimes = skillInfo;
    }
    /*
     * Decrements all recharge times above zero by one per turn.
     */
    public void decrementRechargeTimes()
    {
        skillRechargeTimes.decrementRechargeTime();
    }

    /*
     * Returns current HP.
     * @return double
     */
    public double getCurrentHP()
    {
        return currentHP;
    }

    /*
     * Returns pet name.
     * @return String
     */
    public String getPetName()
    {
        return petName;
    }

    /*
     * Returns pet type.
     * @return PetTypes
     */
    public PetTypes getPetType()
    {
        return petType;
    }

    /*
     * Returns the recharge time of the given skill.
     * @return int
     */
    public int getSkillRechargeTime(Skills skill)
    {
        int returnableCooldown = skillRechargeTimes.getSkillRechargeTime(skill);
        return returnableCooldown;
    }

    /*
     * Sets starting HP to given value.
     * @return double
     */
    public double getStartingHP()
    {
        return startingHP;
    }

    /*
     * This boolean calculates if a pet is awake and returns true if it is.
     * @return boolean
     */
    public boolean isAwake()
    {
        return currentHP > 0;
    }

    /*
     * Resets Current HP to Starting HP.
     */
    public void resetHp()
    {
        currentHP = startingHP;
    }

    /*
     * Resets skill recharge time at the beginning of a fight.
     */
    public void resetRechargeTime()
    {
        skillRechargeTimes.resetRechargeTime();
    }

    /*
     * Sets current HP to given value.
     */
    public void setCurrentHP(double currentHp)
    {
        currentHP = currentHp;
    }

    /*
     * Sets the recharge time of a given skill to given integer value.
     */
    public void setRechargeTime(Skills skill, int rechargeTime)
    {
        skillRechargeTimes.setSkillRechargeTime(skill, rechargeTime);
    }
}
