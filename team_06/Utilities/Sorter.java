package edu.team_06.Utilities;

import edu.team_06.Entity.Player;
import edu.team_06.Interface.Playable;

import java.util.ArrayList;
import java.util.Collections;

/*
A class to sort a list of playables
 */
public class Sorter
{
    /*
    Sorts a list of playables by highest hp
    @return ArrayList<Player>
     */
    public ArrayList<Player> sort(Playable[] playables)
    {
        ArrayList<Player> ans = new ArrayList<>();
        for(int lcv = 0; lcv<playables.length;lcv++)
        {
            if (playables[lcv] != null)
            {
                ans.add((Player) playables[lcv]);
            }
        }

        Collections.sort(ans);

        return ans;
    }
}
