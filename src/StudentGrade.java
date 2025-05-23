import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader; // For reading
import java.io.FileReader; // For reading

public class StudentGrade {
    private String name;
    private List<Double> grades;

    public StudentGrade(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public StudentGrade(String name, double initialGrade) {
        this(name);
        this.grades.add(initialGrade);
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        this.grades.add(grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public Double getHighestGrade() {
        if (grades.isEmpty()) {
            return null;
        }
        return Collections.max(grades);
    }

    public Double getLowestGrade() {
        if (grades.isEmpty()) {
            return null;
        }
        return Collections.min(grades);
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "name='" + name + '\'' +
                ", grades=" + grades +
                ", average=" + String.format("%.2f", getAverageGrade()) +
                '}';
    }

    /**
     * Saves a list of StudentGrade objects to a CSV file.
     * Format: name,grade1,grade2,grade3,...
     *
     * @param students The list of StudentGrade objects to save.
     * @param filePath The path to the CSV file.
     */
    public static void saveStudentsToCsv(List<StudentGrade> students, String filePath) {
        // Use try-with-resources to ensure the writer is closed automatically
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (StudentGrade student : students) {
                StringBuilder line = new StringBuilder();
                line.append(student.getName()); // Add student name

                // Add all grades, separated by commas
                for (Double grade : student.getGrades()) {
                    line.append(",").append(grade);
                }
                writer.write(line.toString()); // Write the complete line
                writer.newLine(); // Add a new line for the next student
            }
            System.out.println("Data saved to " + filePath + " successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of StudentGrade objects from a CSV file.
     * Expected format: name,grade1,grade2,grade3,...
     *
     * @param filePath The path to the CSV file.
     * @return A List of StudentGrade objects loaded from the file.
     */
    public static List<StudentGrade> loadStudentsFromCsv(String filePath) {
        List<StudentGrade> loadedStudents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read line by line until end of file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split the line by comma

                if (parts.length > 0) {
                    String name = parts[0].trim(); // First part is the name
                    StudentGrade student = new StudentGrade(name);

                    // Parse subsequent parts as grades
                    for (int i = 1; i < parts.length; i++) {
                        try {
                            double grade = Double.parseDouble(parts[i].trim());
                            student.addGrade(grade);
                        } catch (NumberFormatException e) {
                            System.err.println("Skipping invalid grade '" + parts[i] + "' for student '" + name + "'.");
                        }
                    }
                    loadedStudents.add(student);
                }
            }
            System.out.println("Data loaded from " + filePath + " successfully!");
        } catch (IOException e) {
            System.err.println("Error loading data from CSV: " + e.getMessage());
            e.printStackTrace();
        }
        return loadedStudents;
    }

    public static void main(String args[]) {
        List<StudentGrade> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String csvFilePath = "students.csv"; // Define your CSV file path

        System.out.println("📖 Welcome to the Student Grade Manager!");

        // Option to load existing data
        System.out.print("Do you want to load existing student data from '" + csvFilePath + "'? (yes/no): ");
        String loadChoice = scanner.nextLine().trim().toLowerCase();
        if (loadChoice.equals("yes")) {
            studentList = loadStudentsFromCsv(csvFilePath);
        }

        // Main loop for adding new students
        while (true) {
            System.out.print("\nDo you want to add a new student? (yes/no): ");
            String addStudentChoice = scanner.nextLine().trim().toLowerCase();
            if (!addStudentChoice.equals("yes")) {
                break;
            }

            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();
            StudentGrade currentStudent = new StudentGrade(name);

            int gradeCounter = 1;
            boolean addingGrades = true;

            System.out.println("Enter " + name + "'s grades (type 'done' to finish grades for this student):");

            while (addingGrades) {
                System.out.print("Grade " + gradeCounter + " (0-100) or 'done': ");
                String gradeInput = scanner.nextLine();

                if (gradeInput.equalsIgnoreCase("done")) {
                    addingGrades = false;
                    continue;
                }

                try {
                    double grade = Double.parseDouble(gradeInput);

                    if (grade < 0 || grade > 100) {
                        System.out.println("⚠️ Grade must be between 0 and 100. Please try again.");
                    } else {
                        currentStudent.addGrade(grade);
                        gradeCounter++;
                        System.out.println("✅ Grade added successfully.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Invalid grade format. Please enter a number (e.g., 85.5) or 'done'.");
                }
            }

            if (currentStudent.getGrades().isEmpty()) {
                System.out.println(
                        "No grades entered for " + currentStudent.getName() + ". Student not added to the list.");
            } else {
                studentList.add(currentStudent);
                System.out.println("Student '" + currentStudent.getName() + "' finalized with " +
                        currentStudent.getGrades().size() + " grades.");
            }
        } // End of outer while(true) loop for adding students

        // Option to save data before exiting
        System.out.print("\nDo you want to save all student data to '" + csvFilePath + "'? (yes/no): ");
        String saveChoice = scanner.nextLine().trim().toLowerCase();
        if (saveChoice.equals("yes")) {
            saveStudentsToCsv(studentList, csvFilePath);
        }

        System.out.println("\n--- Final Student Grade Report ---");
        if (studentList.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (StudentGrade sg : studentList) {
                System.out.println(sg);
            }
        }

        scanner.close();
        System.out.println("\n👋 Exiting. Goodbye!");
    }
}