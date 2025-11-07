import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.println("6. Ask AI Librarian");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> lib.addBook();
                case 2 -> lib.viewBooks();
                case 3 -> lib.issueBook();
                case 4 -> lib.returnBook();
                case 5 -> {
                    System.out.println("👋 Exiting... Thank you!");
                    return;
                }
                case 6 -> {
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter your question for AI Librarian: ");
                    String q = sc.nextLine();
                    LLMChatAssistant.askAI(q);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

// ---- Book Class ----
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    void displayBook() {
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Issued: " + (isIssued ? "Yes" : "No"));
    }
}

// ---- Library Class ----
class Library {
    ArrayList<Book> books = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!");
    }

    void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ No books available.");
            return;
        }
        for (Book b : books) b.displayBook();
    }

    void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    System.out.println("📚 Book issued successfully!");
                } else {
                    System.out.println("❌ Book already issued!");
                }
                return;
            }
        }
        System.out.println("⚠️ Book not found.");
    }

    void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    b.isIssued = false;
                    System.out.println("✅ Book returned successfully!");
                } else {
                    System.out.println("⚠️ Book was not issued.");
                }
                return;
            }
        }
        System.out.println("⚠️ Book not found.");
    }
}
