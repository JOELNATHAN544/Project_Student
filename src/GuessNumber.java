import java.util.Scanner;
import java.util.Random;

public class GuessNumber {
    public static void main(String[] args) {
        // Create Random object for generating random numbers 
        try ( // Create Scanner for user input
                Scanner scanner = new Scanner(System.in)) {
            // Create Random object for generating random numbers
            Random random = new Random();

            // Generate random number between 0 and 100
            int secretNumber = random.nextInt(101);
            int attempts = 0;

            System.out.println("Welcome to the Guess the Number Game!");
            System.out.println("I'm thinking of a number between 0 and 100.");
            System.out.println("Enter a difficulty (easy bu default) :\n\neasy : unlimited tries\nhard : 5 tries");
            System.out.print("(hard / easy) : ");
            String level = scanner.nextLine();
            switch (level) {
                case "easy" -> System.out.println("You have unlimited tries. Good luck!");
                case "hard" -> {
                    System.out.println("You have 5 tries. Good luck!");
                    while (attempts < 5) {
                        System.out.print("Take a guess: ");
                        try {
                            int guess = scanner.nextInt();
                            attempts++;

                            if (guess < secretNumber) {
                                System.out.println("Too low! Try again.");
                            } else if (guess > secretNumber) {
                                System.out.println("Too high! Try again.");
                            } else {
                                System.out.println(
                                        "Congratulations! You guessed the number in " + attempts + " attempts!");
                                return;
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Please enter a valid number between 1 and 100.");
                            scanner.next(); // Clear the invalid input
                        }
                    }
                    System.out.println("Sorry, you've used all your attempts. The number was: " + secretNumber);
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid difficulty level. Defaulting to easy.");
            }

            while (true) {
                System.out.print("Take a guess: ");

                try {
                    int guess = scanner.nextInt();
                    attempts++;

                    if (guess < secretNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > secretNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts!");
                        break;
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid number between 1 and 100.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }
    }
}
