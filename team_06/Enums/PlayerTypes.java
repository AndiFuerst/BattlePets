package edu.team_06.Enums;

import edu.team_06.Utilities.Utils;

/*
DONT CHANGE
 */
public enum PlayerTypes
{
    HUMAN,
    COMPUTER;

    @Override
    public String toString()
    {
        return Utils.convertEnumString(this.name());
    }
}
