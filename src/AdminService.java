import java.sql.*;
import java.util.Scanner;

public class AdminService {
    public static void adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("password123")) {
            System.out.println("Admin login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Remove a book");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addNewBook();
                case 2 -> displayAvailableBooks();
                case 3 -> removeBook();
                case 4 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void addNewBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter number of copies:");
        int copies = scanner.nextInt();
        System.out.println("Enter cost per rental:");
        double cost = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Is this a Fiction book or Non-Fiction book?");
        String type = scanner.nextLine();
        System.out.println(type.equalsIgnoreCase("Fiction") ? "Enter genre:" : "Enter subject:");
        String genreOrSubject = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO books (title, copies_available, cost, type, genre_or_subject) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, title);
            pstmt.setInt(2, copies);
            pstmt.setDouble(3, cost);
            pstmt.setString(4, type);
            pstmt.setString(5, genreOrSubject);

            pstmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAvailableBooks() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nAvailable Books:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " +
                        rs.getString("title") + " - Copies: " +
                        rs.getInt("copies_available") + ", Cost: $" +
                        rs.getDouble("cost") + ", Type: " +
                        rs.getString("type") + ", " +
                        (rs.getString("type").equalsIgnoreCase("Fiction") ? "Genre: " : "Subject: ") +
                        rs.getString("genre_or_subject"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBook() {
        Scanner scanner = new Scanner(System.in);

        displayAvailableBooks();
        System.out.println("Enter the book ID to remove:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmtSelect = conn.prepareStatement("SELECT title FROM books WHERE id = ?")) {

            pstmtSelect.setInt(1, bookId);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                String bookTitle = rs.getString("title");
                System.out.println("Do you want to delete \"" + bookTitle + "\"? (yes/no)");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    try (PreparedStatement pstmtDelete = conn.prepareStatement("DELETE FROM books WHERE id = ?")) {
                        pstmtDelete.setInt(1, bookId);
                        int rowsAffected = pstmtDelete.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Book \"" + bookTitle + "\" removed successfully.");
                        } else {
                            System.out.println("Failed to remove the book.");
                        }
                    }
                } else {
                    System.out.println("Book removal canceled.");
                }
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
