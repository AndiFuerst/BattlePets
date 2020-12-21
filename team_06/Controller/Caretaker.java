package edu.team_06.Controller;

import edu.team_06.Entity.Fight;
/*
 * This class takes care of the mementos in the program for the
 * replay function to work properly.
 */
public class Caretaker
{
    private final int STARTING_LENGTH = 50;
    private final int ADDING_LENGTH = 3;
    private Fight.Memento mementos[];
    private int size;

    /*
    Constructor
     */

    public Caretaker()
    {
        mementos = new Fight.Memento[STARTING_LENGTH];
        size = 0;
    }

    /*
    adds the given memento object to the array
    @param Memento
     */
    public void addMemento(Fight.Memento memento)
    {
        if (size >= mementos.length )
            grow();
        mementos[size] = memento;
        size++;

    }

    /*
    returns the memento at a given index
    @param int
    @return Memento
     */
    public Fight.Memento getMemento(int index)
    {
        return mementos[index];
    }

    /*
    removes a memento object and returns the memento object at the given index
    @param int
    @return Memento
     */
    public Fight.Memento removeMemento(int index)
    {
        Fight.Memento temp = mementos[index];
        for (int i = index; i < mementos.length; i++)
        {
            mementos[i] = mementos[i+1];
        }
        size--;
        return temp;
    }

    /*
    Increases the size of the array
     */
    private void grow()
    {
        Fight.Memento temp[] = new Fight.Memento[mementos.length + ADDING_LENGTH];
        for (int i = 0; i < mementos.length; i++)
        {
            temp[i] = mementos[i];
        }
        mementos = temp;
    }

    /*
    returns the size of the Caretaker's array of Mementos
    @return int
     */
    public int getSize()
    {
        return size;
    }
}