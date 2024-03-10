package efs.task.syntax;


import java.util.Scanner;

public class GuessNumberGame {
    private int argumentM;
    private int lives = 0;
    private int number = 0;

    //Do not modify main method
    public static void main(String[] args)
    {
        try
        {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument)
    {
        try
        {
            argumentM = Integer.parseInt(argument);
        }
        catch(NumberFormatException e)
        {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException();
        }
        if ((argumentM <1) || (argumentM > UsefulConstants.MAX_UPPER_BOUND))
        {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException();
        }
    }

    public void play()
    {
        number = randomInt(1, argumentM);
        printRange(argumentM);
        lives = (int)(calculateLives(argumentM));
        int fails = 0;
        int guess = 0;
        boolean guessed = false;

        do
        {
            printLives(lives, fails);
            System.out.println(UsefulConstants.GIVE_ME);
            Scanner input = new Scanner(System.in);
            if(!input.hasNextInt())
            {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                fails ++;
                continue;
            }
            guess = input.nextInt();
            if(guess > number)
            {
                System.out.println(UsefulConstants.TO_MUCH);
                fails ++;
            }
            else if(guess < number)
            {
                System.out.println(UsefulConstants.TO_LESS);
                fails ++;
            }
            else
            {
                System.out.println(UsefulConstants.YES);
                guessed = true;
                break;
            }
        }while(lives > fails);

        if(guessed)
        {
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else
        {
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }


    public void printRange(int range)
    {
        String printString = "<1,";
        printString = printString.concat(Integer.toString(range));
        printString = printString.concat(">");
        System.out.println(printString);
    }

    public double calculateLives(int argumentM)
    {
        return Math.floor(customLog(2, argumentM)) + 1;
    }

    public double customLog(double a, double b)
    {
        return Math.log(b) / Math.log(a);
    }

    public void printLives(int lives, int fails)
    {
        String printString = "[*";
        printString = printString.concat(("*".repeat(fails)));
        printString = printString.concat(".".repeat(lives - fails - 1));
        printString = printString.concat("]");
        System.out.println(printString);
    }

    public int randomInt(int min, int max)
    {
        return (int)((Math.random() * (max - min)) + min);
    }
}
