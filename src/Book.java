import java.util.HashMap;
import java.util.Map; // Use Map interface for the collection
import java.util.Scanner;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean isBorrowed; // Renamed 'status' to 'isBorrowed' for clarity

    // Changed from List<Book> to Map<Integer, Book>
    // The key is the book's ID (Integer), and the value is the Book object itself.
    private static Map<Integer, Book> books = new HashMap<>();
    private static int nextId = 1; // auto-incremented ID

    // Constructor: Books are initially not borrowed (false)
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false; // Default status
    }

    // --- Getters ---
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() { // Renamed from isStatus() for clarity
        return isBorrowed;
    }

    // --- Setter ---
    public void setBorrowed(boolean borrowed) { // Renamed from setStatus()
        this.isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + (isBorrowed ? "BORROWED" : "RETURNED") + // Display status as BORROWED/AVAILABLE
                '}';
    }

    // --- Static Methods for Library Operations ---

    public static void addBook(String title, String author) {
        Book book = new Book(nextId++, title, author);
        // Add book to the HashMap using its ID as the key
        books.put(book.getId(), book);
        System.out.println("✅ Book added: " + book);
    }

    public static void removeBook(int id) {
        // Use HashMap's remove method for direct removal by key
        Book removedBook = books.remove(id);
        if (removedBook != null) {
            System.out.println("❌ Book with ID " + id + " removed: " + removedBook.getTitle());
        } else {
            System.out.println("⚠️ Book with ID " + id + " not found.");
        }
    }

    public static void searchBook(String keyword) {
        boolean found = false;
        // Iterate over the values (Book objects) in the HashMap
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("🔍 No books found matching: " + keyword);
        }
    }

    public static void listBooks() {
        if (books.isEmpty()) {
            System.out.println("📭 No books in the list.");
        } else {
            System.out.println("📚 Book List:");
            // Iterate over the values (Book objects) in the HashMap
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    // New or modified method for borrowing/returning a book
    public static void changeBookStatus(int id) {
        // Use HashMap's get method for direct lookup by key
        Book bookToChange = books.get(id);
        if (bookToChange != null) {
            bookToChange.setBorrowed(!bookToChange.isBorrowed()); // Toggle the status
            System.out.println("📚 Book '" + bookToChange.getTitle() + "' status changed to " +
                    (bookToChange.isBorrowed() ? "BORROWED" : "AVAILABLE") + ": " + bookToChange);
        } else {
            System.out.println("⚠️ Book with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("📖 Welcome to the Book Manager!");

        while (running) {
            System.out.println("\nChoose an option (1 - 6):");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search Books");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Change Book Status (Borrow / Return)");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    addBook(title, author);
                    break;

                case "2":
                    listBooks();
                    break;

                case "3":
                    System.out.print("Enter keyword to search (title/author): ");
                    String keyword = scanner.nextLine();
                    searchBook(keyword);
                    break;

                case "4":
                    System.out.print("Enter book ID to remove: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        removeBook(id);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid ID format. Please enter a number.");
                    }
                    break;

                case "5": // Updated case for status change
                    System.out.print("Enter book ID to change status (borrow/return): ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        changeBookStatus(id);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid ID format. Please enter a number.");
                    }
                    break;

                case "6": // Exit option
                    running = false;
                    System.out.println("👋 Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("❓ Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}