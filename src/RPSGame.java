import java.util.Random;
import java.util.Scanner;

public class RPSGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Rock, Paper, Scissors, Lizard, Spock!");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = { "rock", "paper", "scissors", "lizard", "spock" };
        int userScore = 0;
        int computerScore = 0;

        while (true) {
            System.out.print(
                    "\nEnter your choice (rock, paper, scissors, lizard, spock), 'score' to see the current score or 'exit' to quit: ");
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("exit")) {
                break;
            }

            if (userChoice.equals("score")) {
                System.out.println("Current Score = You: " + userScore + " - Computer: " + computerScore);
                continue;
            }

            boolean validChoice = false;
            for (String choice : choices) {
                if (choice.equals(userChoice)) {
                    validChoice = true;
                    break;
                }
            }

            if (!validChoice) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            int computerChoiceIndex = random.nextInt(choices.length);
            String computerChoice = choices[computerChoiceIndex];

            System.out.println("Computer chose: " + computerChoice);

            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (winsAgainst(userChoice, computerChoice)) {
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

    public static boolean winsAgainst(String user, String computer) {
        return (user.equals("rock") && (computer.equals("scissors") || computer.equals("lizard"))) ||
                (user.equals("paper") && (computer.equals("rock") || computer.equals("spock"))) ||
                (user.equals("scissors") && (computer.equals("paper") || computer.equals("lizard"))) ||
                (user.equals("lizard") && (computer.equals("spock") || computer.equals("paper"))) ||
                (user.equals("spock") && (computer.equals("scissors") || computer.equals("rock")));
    }
}
