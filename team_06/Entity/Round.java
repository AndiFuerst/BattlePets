package edu.team_06.Entity;

import edu.team_06.Interface.Playable;
import edu.team_06.Enums.Skills;
/*
 * This contains all necessary round information and performs actions like applying damage.
 */
public class Round
{
    //Constants
    private final int MAX_AMOUNT_OF_PLAYABLES = 50;

    //information
    private Playable[] playables = new Playable[MAX_AMOUNT_OF_PLAYABLES];
    private Skills[] skillsChosen = new Skills[MAX_AMOUNT_OF_PLAYABLES];
    private Damage[] damageDone = new Damage[MAX_AMOUNT_OF_PLAYABLES];

    private int size;
    private Playable currentPlayable;
    private int currentIndex;
    private int roundNumber;
    private Playable[] deepCopyPlayables = new Playable[MAX_AMOUNT_OF_PLAYABLES];
    private Skills[] deepCopySkillsChosen = new Skills[MAX_AMOUNT_OF_PLAYABLES];
    private Damage[] deepCopyDamageDone = new Damage[MAX_AMOUNT_OF_PLAYABLES];

    /*
    Sets the roundNumber
    @param int
     */
    public void setRoundNumber(int roundNumber)
    {
        this.roundNumber = roundNumber;
    }

    /*
    returns the DeepCopyPlayables array
    @return Playable[]
     */
    public Playable[] getDeepCopyPlayables()
    {
        return deepCopyPlayables;
    }

    /*
    returns the deepCopySkillsChosen array
    @return Skills[]
     */
    public Skills[] getDeepCopySkillsChosen()
    {
        return deepCopySkillsChosen;
    }

    /*
    returns the deepCopyDamageDone array
    @return Damage[]
     */
    public Damage[] getDeepCopyDamageDone()
    {
        return deepCopyDamageDone;
    }

    /*
        Constructor
        Creates Round Object
        @param Playable[], int
        @return Round
         */
    public Round(Playable[] playables, int size, int roundNumber)
    {
        this.playables = playables;
        this.currentPlayable = this.playables[0];
        this.size = size;
        this.currentIndex = 0;
        this.roundNumber = roundNumber;
    }

    /*
    Applies damage to the receiving Playable
    @param Damage, Playable
    */
    public void applyDamage(Damage damage, Playable receiver)
    {
        receiver.updateHp(damage.calculateTotalDamage());
    }

    /*
    Updates the Playables array with only the Playables that are alive
    */
    public void getAlive()
    {
        Playable[] temp = new Playable[MAX_AMOUNT_OF_PLAYABLES];
        int i = 0;
        int count = 0;
        while (i < size)
        {
            if (playables[i].isAwake())
            {
                temp[count] = playables[i];
                count++;
            }
            i++;
        }
        playables = temp;
        size = count;
        currentPlayable = playables[0];
        currentIndex = 0;
    }

    /*
    Returns the next Playable in the Playables array
    @return Playable
     */
    public Playable getNextPlayable()
    {
        if (currentIndex + 1 == size)
        {
            return playables[0];
        }
        return playables[currentIndex + 1];
    }

    /*
    Returns the size of the Playable array
    @return int
     */
    public int getSize()
    {
        return size;
    }

    /*
    Returns true if there is a next Playable in the Playables array
    Otherwise returns false
    @return boolean
     */
    public boolean isNextPlayable()
    {
        if(currentIndex + 1 == size)
            return false;
        return true;
    }

    /*
    Sets the currentPlayable to the next Playable in the Playables array
     */
    public void nextPlayable()
    {
        currentIndex++;
        if (currentIndex == size)
        {
            currentIndex = 0;
            currentPlayable = playables[currentIndex];
        }
        currentPlayable = playables[currentIndex];
    }

    /*
    Returns the currentPlayable
    @return Playable
     */
    public Playable getCurrentPlayable()
    {
        return currentPlayable;
    }

    /*
    Returns the array of Playables
    @return Playable[]
     */
    public Playable[] getPlayables()
    {
        return playables;
    }



    /*
    Returns the array of Skills Chosen for the given round
    @return Skills[]
    */
    public Skills[] getSkillsChosen()
    {
        return skillsChosen;
    }

    /*
    Returns the Skill at the given index
    @param int
    @return Skills
     */
    public Skills getSkillsChosenAt(int index)
    {
        return skillsChosen[index];
    }

    /*
    Sets the Skill Chosen at the given index
    @param int, Skills
     */
    public void setSkillsChosenAt (int index, Skills skill)
    {
        skillsChosen[index] = skill;
    }

    /*
    Returns the array of DamageDone
    @param Damage[]
     */
    public Damage[] getDamageDone()
    {
        return damageDone;
    }

    /*
    Returns the DamageDone at the given index
    @param int
    @return Damage
     */
    public Damage getDamageDoneAt(int index)
    {
        return damageDone[index];
    }

    /*
    Sets the DamageDone at the given index
    @param int, Damage
     */
    public void setDamageDoneAt (int index, Damage dam)
    {
        damageDone[index] = dam;
    }

    /*
    Returns the round number
    @return int
     */
    public int getRoundNumber()
    {
        return roundNumber;
    }

    /*
    saves the state of the rounds
     */
    public void saveState()
    {
        deepCopyPlayables = new Playable[MAX_AMOUNT_OF_PLAYABLES];
        deepCopyDamageDone = new Damage[MAX_AMOUNT_OF_PLAYABLES];
        deepCopySkillsChosen = new Skills[MAX_AMOUNT_OF_PLAYABLES];

        for (int i = 0; i < size; i++)
        {
            Pet pet = new Pet(playables[i].getStartingHp(), playables[i].getCurrentHp(), playables[i].getPetName(),
                    playables[i].getPetType(), playables[i].getSkillRechargeTime(Skills.ROCK_THROW),
                    playables[i].getSkillRechargeTime(Skills.SCISSORS_POKE), playables[i].getSkillRechargeTime(Skills.PAPER_CUT),
                    playables[i].getSkillRechargeTime(Skills.SHOOT_THE_MOON), playables[i].getSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE));
            Player player = new Player(playables[i].getPlayerName(), pet, playables[i].getPlayerType(),playables[i].getPlayableId());
            deepCopyPlayables[i] = player;
            Skills skills = skillsChosen[i];
            deepCopySkillsChosen[i] = skills;
            Damage damage = new Damage(damageDone[i].getRandomDamage(), damageDone[i].getConditionalDamage());
            deepCopyDamageDone[i] = damage;
        }


    }

}
