import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m"; 
        final String ANSI_BLUE = "\u001B[34m";
        System.out.println(ANSI_GREEN + "\t\t\t\tWELCOME TO HANGMAN!!!!!!!!" + ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        String[] words = { "java", "python", "javascript", "hangman", "programming" };

        String secretWord = words[(int) (Math.random() * words.length)];
        StringBuilder guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        int attempts = 6;
        boolean[] guessedLetters = new boolean[26];
        boolean gameWon = false;
        // boolean gameLost = false;
        System.out.println("Guess the word: " + ANSI_BLUE + guessedWord + ANSI_RESET);
        System.out.println("You have " + attempts + " attempts left.");
        while (attempts > 0 && !gameWon) {
            System.out.println("\n");
            System.out.print("Enter a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (guess < 'a' || guess > 'z') {
                System.out.println("Invalid input. Please enter a letter between a and z.");
                attempts --;
                System.out.println("You have " + ANSI_YELLOW + attempts + ANSI_RESET + " attempts left.");
                continue;
            }

            if (guessedLetters[guess - 'a']) {
                System.out.println("You already guessed that letter. Try again.");
                attempts --;
                System.out.println("You have " + ANSI_YELLOW + attempts + ANSI_RESET + " attempts left.");
                continue;
            }

            guessedLetters[guess - 'a'] = true;

            if (secretWord.indexOf(guess) >= 0) {
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess) {
                        guessedWord.setCharAt(i, guess);
                    }
                }
                System.out.println("Good guess! " + ANSI_BLUE + guessedWord + ANSI_RESET);
            } else {
                attempts--;
                System.out.println("Wrong guess! You have " + attempts + " attempts left.");
            }

            if (guessedWord.toString().equals(secretWord)) {
                gameWon = true;
            }
        }
        if (gameWon) {
            System.out.println("Congratulations! You guessed the word: " + ANSI_RED + secretWord + ANSI_RESET);
        } else {
            System.out.println("Game over! The word was: " + ANSI_RED + secretWord + ANSI_RESET);
        }
        scanner.close();
    }
}
