package edu.team_06.Entity;

import edu.team_06.Enums.Skills;

import java.util.EnumMap;
import java.util.Map;
/*
 * This holds and handles all skill recharge information for the Pet class.
 */
public class SkillInfo {
    private Map<Skills, Integer> skillRechargeTime;
    /*
     * Constructor for SkillInfo
     */
    public SkillInfo()
    {
        skillRechargeTime = new EnumMap<Skills, Integer>(Skills.class);
        skillRechargeTime.put(Skills.PAPER_CUT, 0);
        skillRechargeTime.put(Skills.SCISSORS_POKE, 0);
        skillRechargeTime.put(Skills.ROCK_THROW, 0);
        skillRechargeTime.put(Skills.SHOOT_THE_MOON, 0);
        skillRechargeTime.put(Skills.REVERSAL_OF_FORTUNE, 0);
    }
    /*
     * Sets the given skill's recharge time.
     */
    public void setSkillRechargeTime(Skills settingSkill, int rechargeTime)
    {
        skillRechargeTime.replace(settingSkill, rechargeTime);
    }
    /*
     * Returns the recharge time of the given skill.
     * @return int
     */
    public int getSkillRechargeTime(Skills gettingSkill)
    {
        int toReturn = skillRechargeTime.get(gettingSkill);
        return toReturn;
    }
    /*
     * This resets the recharge times.
     */
    public void resetRechargeTime()
    {
        skillRechargeTime.replace(Skills.PAPER_CUT, 0);
        skillRechargeTime.replace(Skills.SCISSORS_POKE, 0);
        skillRechargeTime.replace(Skills.ROCK_THROW, 0);
        skillRechargeTime.replace(Skills.SHOOT_THE_MOON, 0);
        skillRechargeTime.replace(Skills.REVERSAL_OF_FORTUNE, 0);
    }
    /*
     * This decrements any recharge time over 0 by 1 every turn.
     */
    public void decrementRechargeTime()
    {
        int rechargeTime = skillRechargeTime.get(Skills.PAPER_CUT);
        if(rechargeTime > 0)
        {
            rechargeTime--;
            skillRechargeTime.replace(Skills.PAPER_CUT, rechargeTime);
        }
        rechargeTime = skillRechargeTime.get(Skills.SCISSORS_POKE);
        if(rechargeTime > 0)
        {
            rechargeTime--;
            skillRechargeTime.replace(Skills.SCISSORS_POKE, rechargeTime);
        }
        rechargeTime = skillRechargeTime.get(Skills.ROCK_THROW);
        if(rechargeTime > 0)
        {
            rechargeTime--;
            skillRechargeTime.replace(Skills.ROCK_THROW, rechargeTime);
        }
        rechargeTime = skillRechargeTime.get(Skills.SHOOT_THE_MOON);
        if(rechargeTime > 0)
        {
            rechargeTime--;
            skillRechargeTime.replace(Skills.SHOOT_THE_MOON, rechargeTime);
        }
        rechargeTime = skillRechargeTime.get(Skills.REVERSAL_OF_FORTUNE);
        if(rechargeTime > 0)
        {
            rechargeTime--;
            skillRechargeTime.replace(Skills.REVERSAL_OF_FORTUNE, rechargeTime);
        }

    }
}
