import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

public class ATMSimulator {
    static class Account {
        String pin;
        double balance;

        Account(String pin) {
            this.pin = pin;
            this.balance = 0.0;
        }

        void deposit(double amount) {
            this.balance += amount;
        }

        void withdraw(double amount) {
            if (amount <= balance) {
                this.balance -= amount;
            } else {
                System.out.println("Insufficient funds.");
            }
        }

        double getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM Simulator!");

        while (true) {
            System.out.print("""
                    Enter a number:
                    0. Exit
                    1. Create Account
                    2. Deposit
                    3. Withdraw
                    4. Check Balance
                    """);

            int command = -1;
            boolean valid = false;
            while (!valid) {
                try {
                    command = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            switch (command) {
                case 1 -> createAccount(scanner, accounts);
                case 2 -> deposit(scanner, accounts);
                case 3 -> withdraw(scanner, accounts);
                case 4 -> checkBalance(scanner, accounts);
                case 0 -> {
                    System.out.println("Exiting the ATM Simulator. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    public static void createAccount(Scanner scanner, List<Account> accounts) {
        System.out.print("Enter a 4-digit PIN: ");
        String pin = createPin(scanner);
        accounts.add(new Account(pin));
        System.out.println("Account created successfully.");
    }

    public static void withdraw(Scanner scanner, List<Account> accounts) {
        Account account = login(scanner, accounts);
        if (account == null)
            return;

        boolean valid = false;
        while (!valid) {
            System.out.print("Enter amount to withdraw: ");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a positive number.");
                    continue;
                }
                account.withdraw(amount);
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static void checkBalance(Scanner scanner, List<Account> accounts) {
        Account account = login(scanner, accounts);
        if (account == null)
            return;

        System.out.println("Your balance is: " + account.getBalance());
    }

    public static void deposit(Scanner scanner, List<Account> accounts) {
        Account account = login(scanner, accounts);
        if (account == null)
            return;

        boolean valid = false;
        while (!valid) {
            System.out.print("Enter amount to deposit: ");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a positive number.");
                    continue;
                }
                account.deposit(amount);
                System.out.println("Deposit successful. New balance: " + account.getBalance());
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static Account login(Scanner scanner, List<Account> accounts) {
        int attempts = 0;
        final int maxAttempts = 3;

        while (attempts < maxAttempts) {
            System.out.print("Enter your 4-digit PIN (" + (maxAttempts - attempts) + " attempts remaining): ");
            String enteredPin = scanner.nextLine();

            for (Account account : accounts) {
                if (account.pin.equals(enteredPin)) {
                    System.out.println("Login successful.");
                    return account;
                }
            }

            attempts++;
            System.out.println("Incorrect PIN.");
        }

        System.out.println("Maximum login attempts reached. Returning to main menu.");
        return null;
    }

    public static String createPin(Scanner scanner) {
        while (true) {
            String pin = scanner.nextLine();
            if (pin.matches("\\d{4}")) {
                return pin;
            }
            System.out.println("Invalid PIN. Please enter exactly 4 digits.");
        }
    }
}