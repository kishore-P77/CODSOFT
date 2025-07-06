package codesoft.task1;
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numberToGuess = random.nextInt(100) ; 
        int numberOfTries = 0;
        int guess;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100:");

        while (!hasGuessedCorrectly) 
        {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfTries++;

            if (guess < numberToGuess)
            {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess)
            {
                System.out.println("Too high! Try again.");
            } else 
            {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
            }
        }

        scanner.close();
    }
}

