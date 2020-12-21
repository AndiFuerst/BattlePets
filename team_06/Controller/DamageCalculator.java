package edu.team_06.Controller;

import edu.team_06.Entity.Damage;
import edu.team_06.Enums.PetTypes;
import edu.team_06.Enums.Skills;
import edu.team_06.Interface.Playable;

import java.util.Random;
/*
 * This class handles damage to pets, both random and conditional.
 */
public class DamageCalculator
{
    private final double MULTIPLIER = 5;
    private final double ADDITIVE = 10;
    private final double BIG_DAMAGE = 3;
    private final double SMALL_DAMAGE = 2;
    private final double UPPER_BOUND = 75;
    private final double LOWER_BOUND = 25;
    private final double SHOOTING_DAMAGE = 20;
    private Random randomNumberGenerator;
    private Skills skillB;
    private Skills skillA;
    private Damage total;
    private double randomTakenDamageTotal;
    private double randomDealtDamageTotal;


    /*
     * Constructor for DamageCalculator
     */
    public DamageCalculator(int seed)
    {
        randomNumberGenerator = new Random();

        randomNumberGenerator.setSeed(seed);
    }
    /*
    Accepts
            -the player who does damage
            -the player who receives damage
            -skill choice of first player
            -skill choice of second player
    Returns the damage object
     */
    public Damage calculateDamage(Playable playerA, Playable playerB, Skills skillA, Skills skillB, double randTaken, double randDealt)
    {
        this.skillA = skillA;
        this.skillB = skillB;
        total = new Damage(0, 0);
        total.setRandomDamage(randomNumberGenerator.nextDouble() * 5);

        if (skillA == Skills.REVERSAL_OF_FORTUNE)
        {
            total.setConditionalDamage(calculateReversalOfFortuneDamage(playerA, playerB, randTaken, randDealt));
        }
        else if (playerA.getPetType() == PetTypes.POWER)
        {
            total.setConditionalDamage(calculateDamagePower(playerA, playerB, getRandomDamage()));
        }
        else if (playerA.getPetType() == PetTypes.SPEED)
        {
            total.setConditionalDamage(calculateDamageSpeed(playerA, playerB));
        }
        else
        {
            total.setConditionalDamage(calculateDamageIntelligence(playerA, playerB));
        }
        return total;
    }

    /*
    if pet is Intelligence type calculates the random damage
    @return double
     */
    private double calculateDamageIntelligence(Playable playerA, Playable playerB)
    {
        double intelligenceConditionalDamage = 0;
        if (skillA == Skills.ROCK_THROW)
        {
            if (playerB.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            {
                intelligenceConditionalDamage += BIG_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
        }
        if (skillA == Skills.SCISSORS_POKE)
        {
            if (playerB.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            {
                intelligenceConditionalDamage += BIG_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
        }
        if (skillA == Skills.PAPER_CUT)
        {
            if (playerB.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            {
                intelligenceConditionalDamage += BIG_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
            if (playerB.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            {
                intelligenceConditionalDamage += SMALL_DAMAGE;
            }
        }
        if (skillA == Skills.SHOOT_THE_MOON)
        {
            intelligenceConditionalDamage += calculateShootTheMoonDamage(playerA, playerB);
        }
        return intelligenceConditionalDamage;
    }

    /*
    if pet is power type calculates the random damage
    @return double
     */
    private double calculateDamagePower(Playable playerA, Playable playerB, double rand)
    {
        if (skillA == Skills.ROCK_THROW && skillB == Skills.SCISSORS_POKE)
        {
            return rand * MULTIPLIER;
        }
        if (skillA == Skills.SCISSORS_POKE && skillB == Skills.PAPER_CUT)
        {
            return rand * MULTIPLIER;
        }
        if (skillA == Skills.PAPER_CUT && skillB == Skills.ROCK_THROW)
        {
            return rand * MULTIPLIER;
        }
        if (skillA == Skills.SHOOT_THE_MOON)
        {
            return calculateShootTheMoonDamage(playerA, playerB);
        }
        return 0;
    }

    /*
    if pet is Speed type calculates the random damage
    @return double
     */
    private double calculateDamageSpeed(Playable playerA, Playable playerB)
    {
        double perHP  = playerB.calculateHpPercent();
        if (skillA == Skills.ROCK_THROW)
        {
            if (perHP >= UPPER_BOUND)
            {
                if (skillB == Skills.PAPER_CUT || skillB == Skills.SCISSORS_POKE)
                {
                    return ADDITIVE;
                }
            }
        }
        if (skillA == Skills.SCISSORS_POKE)
        {
            if (LOWER_BOUND <= perHP && perHP < UPPER_BOUND)
            {
                if (skillB == Skills.ROCK_THROW || skillB == Skills.PAPER_CUT)
                {
                    return ADDITIVE;
                }
            }
        }
        if (skillA == Skills.PAPER_CUT)
        {
            if (perHP >= 0 && perHP < LOWER_BOUND)
            {
                if (skillB == Skills.SCISSORS_POKE || skillB == Skills.ROCK_THROW)
                {
                    return ADDITIVE;
                }
            }
        }
        if (skillA == Skills.SHOOT_THE_MOON)
        {
            return calculateShootTheMoonDamage(playerA, playerB);
        }
        return 0;
    }

    /*
     * This handles conditional damage calculations for Reversal of Fortune, since all conditional damage is calculated
     * in the same manner no matter the pet type.
     * @return double
     */
    private double calculateReversalOfFortuneDamage(Playable playerA, Playable playerB, double randTaken, double randDealt)
    {
        double difference = 0;
        difference = randDealt - randTaken;
        return difference;
    }

    /*
     * This handles conditional damage for Shoot the Moon, since the conditional damage is calculated
     * in a similar manner in all pet types.
     * @return double
     */
    private double calculateShootTheMoonDamage(Playable playerA, Playable playerB)
    {
        if (skillA == Skills.SHOOT_THE_MOON)
        {
            Skills predicted = playerA.getSkillPrediction();

            if (predicted == skillB)
            {
                return SHOOTING_DAMAGE;
            }
        }
        return 0;
    }

    /*
     * This method returns the random damage stored in this class.
     * @return double
     */
    public double getRandomDamage()
    {
        return total.getRandomDamage();
    }
}
