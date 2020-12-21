package edu.team_06.Entity;

/*
DONT CHANGE
 */
public class Damage
{
    private double randomDamage;
    private double conditionalDamage;

    public Damage(double randomDamage, double conditionalDamage)
    {
        this.randomDamage = randomDamage;
        this.conditionalDamage = conditionalDamage;
    }

    public double calculateTotalDamage()
    {
        return randomDamage + conditionalDamage;
    }

    public double getConditionalDamage()
    {
        return conditionalDamage;
    }

    public double getRandomDamage()
    {
        return randomDamage;
    }

    public void setConditionalDamage(double conditionalDamage)
    {
        this.conditionalDamage = conditionalDamage;
    }

    public void setRandomDamage(double randomDamage)
    {
        this.randomDamage = randomDamage;
    }

}
