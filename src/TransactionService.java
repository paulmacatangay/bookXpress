import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionService {
    public static void rentBook(Account account) {
        Scanner scanner = new Scanner(System.in);

        // Display available books
        AdminService.displayAvailableBooks();
        System.out.println("Enter the book ID you want to rent:");
        int bookId = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement bookStmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
             PreparedStatement updateBookStmt = conn.prepareStatement(
                     "UPDATE books SET copies_available = copies_available - 1 WHERE id = ?");
             PreparedStatement addRentalStmt = conn.prepareStatement(
                     "INSERT INTO rentals (username, book_id) VALUES (?, ?)");
             PreparedStatement deductBalanceStmt = conn.prepareStatement(
                     "UPDATE users SET balance = balance - ? WHERE username = ?")) {

            // Check book availability
            bookStmt.setInt(1, bookId);
            ResultSet rs = bookStmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                int copies = rs.getInt("copies_available");
                double cost = rs.getDouble("cost");

                if (copies > 0 && account.balance >= cost) {
                    // Deduct a copy
                    updateBookStmt.setInt(1, bookId);
                    updateBookStmt.executeUpdate();

                    // Add rental record
                    addRentalStmt.setString(1, account.username);
                    addRentalStmt.setInt(2, bookId);
                    addRentalStmt.executeUpdate();

                    // Deduct user balance
                    deductBalanceStmt.setDouble(1, cost);
                    deductBalanceStmt.setString(2, account.username);
                    deductBalanceStmt.executeUpdate();

                    account.balance -= cost;
                    System.out.println("You have successfully rented \"" + title + "\"!");
                } else {
                    System.out.println("Insufficient balance or no copies available.");
                }
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewInventory(Account account) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT rentals.book_id, books.title FROM rentals INNER JOIN books ON rentals.book_id = books.id WHERE rentals.username = ?")) {

            pstmt.setString(1, account.username);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nYour Rented Books:");
            ArrayList<Integer> bookIds = new ArrayList<>();
            int index = 1;

            while (rs.next()) {
                bookIds.add(rs.getInt("book_id"));
                System.out.println(index + ". " + rs.getString("title"));
                index++;
            }

            if (bookIds.isEmpty()) {
                System.out.println("No books rented.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(Account account) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> rentedBooks = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT rentals.book_id, books.title FROM rentals INNER JOIN books ON rentals.book_id = books.id WHERE rentals.username = ?")) {

            pstmt.setString(1, account.username);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nYour Rented Books:");
            int index = 1;

            while (rs.next()) {
                rentedBooks.add(rs.getInt("book_id"));
                System.out.println(index + ". " + rs.getString("title"));
                index++;
            }

            if (rentedBooks.isEmpty()) {
                System.out.println("No books rented.");
                return;
            }

            System.out.println("Enter the number of the book you want to return:");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= rentedBooks.size()) {
                int bookId = rentedBooks.get(choice - 1);

                try (PreparedStatement deleteStmt = conn.prepareStatement(
                        "DELETE FROM rentals WHERE book_id = ? AND username = ?")) {

                    deleteStmt.setInt(1, bookId);
                    deleteStmt.setString(2, account.username);
                    int rowsAffected = deleteStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Failed to return the book.");
                    }

                    try (PreparedStatement updateStmt = conn.prepareStatement(
                            "UPDATE books SET copies_available = copies_available + 1 WHERE id = ?")) {
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();
                    }
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void topUpBalance(Account account) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter amount to top up:");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                         "UPDATE users SET balance = balance + ? WHERE username = ?")) {

                pstmt.setDouble(1, amount);
                pstmt.setString(2, account.username);
                pstmt.executeUpdate();

                account.balance += amount;
                System.out.println("Balance topped up successfully! New balance: $" + account.balance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}
