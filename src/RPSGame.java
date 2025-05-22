import java.util.Random;
import java.util.Scanner;

public class RPSGame {
    public static void main(String[] args) {
        int x = 0;
        x = ++x; 
        System.out.println(4 + 3 * 2);
        // System.getCommandLineArgs();
        System.out.println("Welcome to Rock, Paper, Scissors!");
        // System.exit(1);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = { "rock", "paper", "scissors" };
        int userScore = 0;
        int computerScore = 0;

        while (true) {
            System.out.print(
                    "\nEnter your choice (rock, paper, scissors), 'score' to see the current score or 'exit' to quit: ");
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("exit")) {
                break;
            }

            if (userChoice.equals("score")) {
                System.out.println("Current Score = You: " + userScore + " - Computer: " + computerScore);
                continue;
            }

            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            int computerChoiceIndex = random.nextInt(3);
            String computerChoice = choices[computerChoiceIndex];

            System.out.println("Computer chose: " + computerChoice);

            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                    (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                    (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
                System.out.println("You win!");
                userScore++;
            } else {
                System.out.println("Computer wins!");
                computerScore++;
            }

            System.out.println("Score = You: " + userScore + " - Computer: " + computerScore);
        }

        System.out.println("Thanks for playing! Final Score - You: " + userScore + ", Computer: " + computerScore);
        scanner.close();
    }
}
