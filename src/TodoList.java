import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TodoList { 
    private static final String FILE_NAME = "tasks.txt";

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

        String toFileFormat() {
            return (completed ? "1" : "0") + "|" + title;
        }

        static Task fromFileFormat(String line) {
            String[] parts = line.split("\\|", 2);
            if (parts.length != 2)
                return null;

            Task task = new Task(parts[1]);
            task.completed = parts[0].equals("1");
            return task;
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = loadTasks();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Todo List!");

        while (true) {
            System.out.print("""
                    \nOptions:
                    1. Add Task
                    2. List Tasks
                    3. Complete Task
                    4. Delete Task
                    5. Exit
                    >\s""");

            try {
                int command = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (command) {
                    case 1 -> addTask(scanner, tasks);
                    case 2 -> listTasks(tasks);
                    case 3 -> completeTask(scanner, tasks);
                    case 4 -> deleteTask(scanner, tasks);
                    case 5 -> {
                        saveTasks(tasks);
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number 1-5");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // === File Operations ===
    private static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null)
                    tasks.add(task);
            }
        } catch (IOException e) {
            // File doesn't exist yet - that's okay
        }
        return tasks;
    }

    // === Task Operations ===
    private static void addTask(Scanner scanner, List<Task> tasks) {
        System.out.print("Enter task: ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) {
            tasks.add(new Task(title));
            saveTasks(tasks); // Auto-save
            System.out.println("Added: " + title);
        } else {
            System.out.println("Task cannot be empty!");
        }
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet!");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    private static void completeTask(Scanner scanner, List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to complete!");
            return;
        }

        listTasks(tasks);
        System.out.print("Enter task number to complete: ");
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num > 0 && num <= tasks.size()) {
                tasks.get(num - 1).markCompleted();
                saveTasks(tasks); // Auto-save
                System.out.println("Marked as completed!");
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
            scanner.nextLine();
        }
    }

    private static void deleteTask(Scanner scanner, List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete!");
            return;
        }

        listTasks(tasks);
        System.out.print("Enter task number to delete: ");
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num > 0 && num <= tasks.size()) {
                String title = tasks.get(num - 1).title;
                tasks.remove(num - 1);
                saveTasks(tasks); // Auto-save
                System.out.println("Deleted: " + title);
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
            scanner.nextLine();
        }
    }
}