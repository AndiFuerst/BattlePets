package edu.team_06.Controller;
import edu.team_06.Boundry.ConsoleInput;
import edu.team_06.Boundry.ConsoleOutput;
import edu.team_06.Entity.Fight;
import edu.team_06.Entity.Round;

public class ReplayController
{
    private Caretaker caretaker;
    /*
    Constructor
    @param Caretaker
     */
    public ReplayController(Caretaker caret)
    {
        this.caretaker = caret;
    }

    /*
    Run replay and handle input for it
    @param String
     */
    public void runReplay(String battleWinner)
    {

        ConsoleInput consoleInput = new ConsoleInput();
        ConsoleOutput consoleOutput = new ConsoleOutput();
        iterator iter = new iterator(caretaker);
        Boolean continueReplay = true;

        consoleOutput.outputStartOfReplay();
        ConsoleOutput.outputFightNum(1);
        ConsoleOutput.outputEntireRound(iter.currentRound());
        while(continueReplay)
        {
            int choice;
            boolean hasNext = iter.hasNextRound() || iter.hasNextFight();
            boolean hasPrevious = iter.hasPreviousRound() || iter.hasPreviousFight();

            if(!hasNext)
            {
                choice = consoleInput.inputReplayChoiceNoNextRound();
                //
                consoleOutput.outputBattle(battleWinner, caretaker.getSize());
            }
            else if(!hasPrevious)
            {
                choice = consoleInput.inputReplayChoiceNoPreviousRound();
            }
            else
            {
                choice = consoleInput.inputReplayChoiceStandard();
            }

            if(choice == 1)//step forward
            {
                if (iter.hasNextRound())
                {
                    ConsoleOutput.outputEntireRound(iter.nextRound());
                }
                else
                {
                    if(iter.hasNextFight())
                    {
                        //
                        ConsoleOutput.outputFight(iter.currentFight(), iter.currentFight().getWinnerName());
                        //
                        ConsoleOutput.outputFightNum(iter.nextFight().getCurrentFightNumber());
                        ConsoleOutput.outputEntireRound(iter.currentRound());
                    }
                    else
                    {
                        continueReplay = false;
                    }
                }
            }
            else if(choice ==2)//step back
            {
                if (iter.hasPreviousRound())
                {
                    ConsoleOutput.outputEntireRound(iter.previousRound());
                }
                else
                {
                    if(iter.hasPreviousFight())
                    {
                        ConsoleOutput.outputFightNum(iter.previousFight().getCurrentFightNumber());
                        ConsoleOutput.outputEntireRound(iter.currentRound());
                        //
                        ConsoleOutput.outputFight(iter.currentFight(), iter.currentFight().getWinnerName());
                        //
                    }
                    else
                    {
                        ConsoleOutput.outputNoPreviousRound();
                    }
                }
            }
            else if(choice == 3)//continue
            {
                while(iter.hasNextRound())
                {
                    ConsoleOutput.outputEntireRound(iter.nextRound());
                }
                while(iter.hasNextFight())
                {
                    //
                    ConsoleOutput.outputFight(iter.currentFight(), iter.currentFight().getWinnerName());
                    //
                    ConsoleOutput.outputFightNum(iter.nextFight().getCurrentFightNumber());
                    ConsoleOutput.outputEntireRound(iter.currentRound());
                    while(iter.hasNextRound())
                    {
                        ConsoleOutput.outputEntireRound(iter.nextRound());
                    }
                }
                continueReplay = false;
            }
            else if(choice == 4)//exit
            {
                continueReplay = false;
            }
        }

    }

    /*
    Class that iterates through a Caretaker
     */
    public class iterator
    {
        private Caretaker caretaker;
        private int currentFightIndex;
        private int currentRoundIndex;

        /*
        Constructor
        @param Caretaker
         */
        public iterator(Caretaker caretaker)
        {
            this.caretaker = caretaker;
            currentFightIndex = 0;
            currentRoundIndex = 0;
        }

        /*
        returns the current fight
        @return Fight
         */
        public Fight currentFight()
        {
            Fight temp = new Fight(currentFightIndex + 1);
            temp.setRounds(caretaker.getMemento(currentFightIndex).getRounds(),
                    caretaker.getMemento(currentFightIndex).getSize());
            temp.setWinnerName(caretaker.getMemento(currentFightIndex).getWinnerName());
            return temp;
        }

        /*
        returns the current Round
        @return Round
         */
        public Round currentRound()
        {
            Round[] temp =  caretaker.getMemento(currentFightIndex).getRounds();
            return temp[currentRoundIndex];
        }

        /*
        Returns true if there is a previous fight in the array
        Returns false if there is no previous fight in the array
        @return boolean
         */
        public boolean hasPreviousFight()
        {
            if(currentFightIndex == 0)
                return false;
            return true;
        }

        /*
        Returns true if there is a previous round in the array
        Returns false if there is no previous round in the array
        @return boolean
         */
        public boolean hasPreviousRound()
        {
            if(currentRoundIndex == 0)
                return false;
            return true;
        }
        /*
        returns the next round in the array
        @return Round
         */
        public Round nextRound()
        {
            currentRoundIndex++;
            Round[] temp =  caretaker.getMemento(currentFightIndex).getRounds();
            return temp[currentRoundIndex];
        }

        /*
        returns the next fight in the array
        @return Fight
         */
        public Fight nextFight()
        {
            currentRoundIndex = 0;
            currentFightIndex++;
            Fight temp = new Fight(currentFightIndex + 1);
            temp.setRounds(caretaker.getMemento(currentFightIndex).getRounds(),
                    caretaker.getMemento(currentFightIndex).getSize());
            return temp;
        }

        /*
        Returns true if there is a next fight in the array
        Returns false if there is no next fight in the array
        @return boolean
         */
        public Boolean hasNextFight()
        {
            if(currentFightIndex + 1 == caretaker.getSize())
                return false;
            return true;
        }

        /*
        Returns true if there is a Next Round in the array
        Returns false if there is no next round in the array
        @return boolean
         */
        public Boolean hasNextRound()
        {
            if(currentRoundIndex + 1 == caretaker.getMemento(currentFightIndex).getSize())
                return false;
            return true;
        }

        /*
        returns the previous round in the array
        @return Round
         */
        public Round previousRound()
        {
            currentRoundIndex--;
            Round[] temp =  caretaker.getMemento(currentFightIndex).getRounds();
            return temp[currentRoundIndex];
        }

        /*
        returns the previous fight in the array
        @return Fight
         */
        public Fight previousFight()
        {
            currentFightIndex--;
            currentRoundIndex = caretaker.getMemento(currentFightIndex).getSize()-1;
            Fight temp = new Fight(currentFightIndex + 1);
            temp.setRounds(caretaker.getMemento(currentFightIndex).getRounds(),
                    caretaker.getMemento(currentFightIndex).getSize());
            return temp;
        }




    }
}
