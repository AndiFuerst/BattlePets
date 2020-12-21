package edu.team_06.Enums;

import edu.team_06.Utilities.Utils;

/*
DONT CHANGE
 */
public enum PetTypes
{
    POWER,
    SPEED,
    INTELLIGENCE;

    @Override
    public String toString()
    {
        return Utils.convertEnumString(this.name());
    }
}
