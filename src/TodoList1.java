import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TodoList1 {

    static class Task {
        String title;
        boolean completed;

        Task(String title) {
            this.title = title;
            this.completed = false; 
        }

        void markCompleted() {
            this.completed = true;
        }

        @Override
        public String toString() {
            return (completed ? "[x] " : "[ ] ") + title;
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int command;

        System.out.println("Welcome to the Todo List!");

        while (true) {
            System.out.print("\nEnter a number \n\n1. add\n2. list\n3. complete\n4. exit\n\n ");
            try {

                command = scanner.nextInt();

                switch (command) {
                    case 1: // add
                        System.out.print("Enter task title: ");
                        scanner.nextLine();
                        String title = scanner.nextLine();
                        tasks.add(new Task(title));
                        System.out.println("Task added.");
                        break;
                    case 2: // list
                        System.out.println("Todo List:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        break;
                    case 3: // complete
                        System.out.print("Enter task number to mark as completed: ");
                        try {

                            int taskNumber = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                                tasks.get(taskNumber - 1).markCompleted();
                                System.out.println("Task marked as completed.");
                            } else {
                                System.out.println("Invalid task number.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;
                    case 4: // exit
                        System.out.println("\nExiting Todo List. Goodbye!");
                        return;
                    default: // unknown command
                        System.out.println("Unknown command. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        // scanner.close();
    }
}
