package edu.team_06.Interface;

import edu.team_06.Entity.Pet;
import edu.team_06.Entity.Player;
import edu.team_06.Enums.Skills;

public interface Inputable
{
    int inputFightNumber();

    Pet inputPet();

    Player inputPlayer(int id);

    Skills inputSkillChoice(int rockCD, int paperCD, int scissorCD, int reversalOfFortuneCD, int shootTheMoonCD);

}