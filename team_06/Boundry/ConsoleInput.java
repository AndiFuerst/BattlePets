package edu.team_06.Boundry;

import edu.team_06.Entity.Pet;
import edu.team_06.Entity.Player;
import edu.team_06.Enums.PetTypes;
import edu.team_06.Enums.PlayerTypes;
import edu.team_06.Enums.Skills;
import edu.team_06.Interface.Inputable;

import java.util.Scanner;
/*
 *ConsoleInput- A class to hold all methods that involve input
 */
public class ConsoleInput implements Inputable
{
    Scanner inpt = new Scanner(System.in);

    /*
     * Constructor
     */
    public ConsoleInput()
    {
    }

    /*
     * Prompts the user for the number of fights needed
     * Reads in that value
     * @return int
     */
    public int inputFightNumber()
    {
        System.out.println("Please enter the number of fights: ");
        //Scanner fghtNum = new Scanner(System.in);
        int fightNum = inpt.nextInt();

        if (fightNum == 0)
        {
            System.out.println("There must be at least 1 fight, please enter a valid fight number: ");
            fightNum = inpt.nextInt();
        }
        return fightNum;
    }

    /*
     * Prompts the user for a valid entry, used if any value that is not an integer is entered for the player number
     * Reads in that value
     * @return int
     */
    public int inputInvalidInt()
    {
        System.out.println("Invalid Entry, you must enter an integer value for the number of players, please enter" +
                " a valid integer value: ");
        int validNum = inpt.nextInt();
        return validNum;
    }

    /*
     * Prompts the user to enter a valid number of players
     * Reads in that value
     * @return int
     */
    public int inputInvalidNumOfPlayers()
    {
        System.out.println("Invalid Entry, there must be a minimum of 2 players per game, please enter a " +
                "valid number of players:");
        int nmbrPlyrs = inpt.nextInt();
        return nmbrPlyrs;
    }

    /*
     * Prompts the user for the number of players for the game
     * Reads in that value
     * @return int
     */
    public int inputNumberOfPlayers()
    {
        System.out.println("How many players will be in this game?");
        int numberPlayers = inpt.nextInt();
        return numberPlayers;
    }

    /*
     * Prompts each player for their pet information
     * Reads in that information and stores it accordingly
     * Checks for an invalid Pet Type entry
     * @return Pet
     */
    public Pet inputPet()
    {

        System.out.println("Please enter your pet's name:");
        String inPetName = inpt.next();

        System.out.println("Please enter your pet's starting HP:");
        double inPetHP = inpt.nextDouble();
        if( inPetHP < 0)
        {
            System.out.println("Pet Hp cannot be negative \n Please enter your pet's starting HP:");
            inPetHP = inpt.nextDouble();
        }

        System.out.println("Please enter which type of pet you would like: 1 - Power, 2 - Speed, 3 - Intelligence");
        int inPetType = inpt.nextInt();

        Pet pet = null;

        if (inPetType == 1)
        {
            pet = new Pet(inPetHP, inPetName, PetTypes.POWER);
        }
        else if (inPetType == 2)
        {
            pet = new Pet(inPetHP, inPetName, PetTypes.SPEED);
        }
        else if (inPetType == 3)
        {
            pet = new Pet(inPetHP, inPetName, PetTypes.INTELLIGENCE);
        }
        else
        {
            System.out.println("Invalid entry, please enter a valid pet type:");
            inPetType = inpt.nextInt();

            if (inPetType == 1)
            {
                pet = new Pet(inPetHP, inPetName, PetTypes.POWER);
            }
            else if (inPetType == 2)
            {
                pet = new Pet(inPetHP, inPetName, PetTypes.SPEED);
            }
            else if (inPetType == 3)
            {
                pet = new Pet(inPetHP, inPetName, PetTypes.INTELLIGENCE);
            }
        }
        return pet;
    }

    /*
     * Asks the user if they would like to play again
     * Reads in whether or not the user would like to play again
     * @return boolean
     */
    public boolean inputPlayAgain()
    {
        boolean playAg = true;

        System.out.println("Do you want to play again? 1 - Yes, 2 - No");
        int play = inpt.nextInt();

        if (play == 1)
        {
            playAg = true;
        }
        else if (play == 2)
        {
            playAg = false;
        }
        else
        {
            System.out.println("Invalid Entry, please enter 1 if you would like to play again, or 2 if you " +
                    "wouldn't like to play again.");
            play = inpt.nextInt();

            if (play == 1)
            {
                playAg = true;
            }
            else if (play == 2)
            {
                playAg = false;
            }
        }
        return playAg;
    }

    /*
     * Prompts the user for their player name
     * Reads in that value
     * @return Player
     */
    public Player inputPlayer(int place)
    {
        System.out.println("Please enter your Player name:");
        String inName = inpt.next();
        Player player = new Player(inName, inputPet(), PlayerTypes.HUMAN, place);
        return player;
    }

    /*
     * Asks the user if they would like to watch a replay of the battle
     * Reads in whether or not they would like to watch a replay
     * @return boolean
     */
    public boolean inputReplay ()
    {
        System.out.println("Would you like to watch a replay of the battle? 1 - Yes, 2 - No");
        int watchReplay = inpt.nextInt();

        if (watchReplay == 1 || watchReplay ==2)
        {
            return watchReplay == 1;
        }
        else
        {
            System.out.println("Invalid Entry, please enter 1 if you would like to watch a replay, or 2 " +
                    "if you wouldn't like to watch a replay: ");
            int invldWatchReplay = inpt.nextInt();

            return invldWatchReplay == 1;
        }
    }

    /*
     * Asks the user what they would like to do while watching the replay of the battle
     * Reads in that value
     * @return int
     */
    public int inputReplayChoiceStandard()
    {
        System.out.println("1 - Step Forward, 2 - Step Backward, 3 - Continue, 4 - Exit");
        int choice = inpt.nextInt();

        if (choice == 1 || choice == 2 || choice == 3 || choice == 4)
        {
            return choice;
        }
        else
        {
            System.out.println("Invalid Entry, please enter a valid option: 1 - Step Forward, 2 - Step Backward, " +
                    "3 - Continue, 4 - Exit: ");
            int nChoice = inpt.nextInt();

            return nChoice;
        }
    }

    /*
     * Asks the user what they would like to do while watching the replay of the battle without the step forward option
     * Reads in that value
     * @return int
     */
    public int inputReplayChoiceNoNextRound()
    {
        System.out.println("2 - Step Backward, 3 - Continue, 4 - Exit");
        int choice = inpt.nextInt();

        if (choice == 2 || choice == 3 || choice == 4)
        {
            return choice;
        }
        else
        {
            System.out.println("Invalid Entry, please enter a valid option: 2 - Step Backward, " +
                    "3 - Continue, 4 - Exit: ");
            int nChoice = inpt.nextInt();

            return nChoice;
        }
    }

    /*
     * Asks the user what they would like to do while watching the replay of the battle
     * Reads in that value
     * @return int
     */
    public int inputReplayChoiceNoPreviousRound()
    {
        System.out.println("1 - Step Forward, 3 - Continue, 4 - Exit");
        int choice = inpt.nextInt();

        if (choice == 1 || choice == 3 || choice == 4)
        {
            return choice;
        }
        else
        {
            System.out.println("Invalid Entry, please enter a valid option: 1 - Step Forward, " +
                    "3 - Continue, 4 - Exit: ");
            int nChoice = inpt.nextInt();

            return nChoice;
        }
    }

    /*
     * Prompts the user for which skill they would like to use
     * Reads in that value and sets the skill accordingly
     * Checks for an invalid entry
     * @return Skills
     */
    public Skills inputSkillChoice(int rockCD, int paperCD, int scissorCD, int reversalOfFortuneCD, int shootTheMoonCD)
    {
        Skills skill = null;
        while (skill == null)
        {
            System.out.println("Please enter your skill choice: 1 - Rock Throw, 2 - Scissor Poke, 3 - Paper Cut, " +
                    "4 - Reverse of Fortune, 5 - Shoot the Moon");

            int skillInt = inpt.nextInt();

            if (skillInt == 1 && rockCD == 0)
            {
                skill = Skills.ROCK_THROW;
            }
            else if (skillInt == 2 && scissorCD == 0)
            {
                skill = Skills.SCISSORS_POKE;
            }
            else if (skillInt == 3 && paperCD == 0)
            {
                skill = Skills.PAPER_CUT;
            }
            else if (skillInt == 4 && reversalOfFortuneCD == 0)
            {
                skill = Skills.REVERSAL_OF_FORTUNE;
            }
            else if (skillInt == 5 && shootTheMoonCD == 0)
            {
                skill = Skills.SHOOT_THE_MOON;
            }
            else
            {
                System.out.println("Invalid entry, please enter a valid skill choice:");
                skillInt = inpt.nextInt();

                if (skillInt == 1 && rockCD == 0)
                {
                    skill = Skills.ROCK_THROW;
                }
                else if (skillInt == 2 && scissorCD == 0)
                {
                    skill = Skills.SCISSORS_POKE;
                }
                else if (skillInt == 3 && paperCD == 0)
                {
                    skill = Skills.PAPER_CUT;
                }
                else if (skillInt == 4 && reversalOfFortuneCD == 0)
                {
                    skill = Skills.REVERSAL_OF_FORTUNE;
                }
                else if (skillInt == 5 && shootTheMoonCD == 0)
                {
                    skill = Skills.SHOOT_THE_MOON;
                }
            }
        }
        return skill;
    }
    /*
     * An input method that takes the random number generator seed before
     * a game starts.
     * @return int
     */
    public int inputSeed()
    {
        boolean tryAgain = true;
        int seed = 7;
        while(tryAgain)
        {
            try {
                System.out.println("Input a random Seed:");
                seed = inpt.nextInt();
                if (seed < 0) {
                    tryAgain = true;
                    System.out.println("The random Seed can only be positive.");
                }
                tryAgain = false;
            } catch (Exception e) {
                System.out.println("The random Seed can only be an integer.");
                tryAgain = true;
            }
        }
        return seed;
    }
    /*
     * An input method that asks the user which skill they predict the next user will use.
     * @return SKills
     */
    public Skills predictSkillChoice()
    {
        Skills skill = null;
        while(skill == null)
        {
            System.out.println("Please enter your prediction: 1 - Rock Throw, 2 - Scissor Poke, 3 - Paper Cut, " +
                    "4 - Reverse of Fortune, 5 - Shoot the Moon");
            int skillInt = inpt.nextInt();
            switch(skillInt)
            {
                case 1:
                    skill = Skills.ROCK_THROW;
                    break;
                case 2:
                    skill = Skills.SCISSORS_POKE;
                    break;
                case 3:
                    skill = Skills.PAPER_CUT;
                    break;
                case 4:
                    skill = Skills.REVERSAL_OF_FORTUNE;
                    break;
                case 5:
                    skill = Skills.SHOOT_THE_MOON;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid skill.");
                    break;
            }
        }
        return skill;

    }
}