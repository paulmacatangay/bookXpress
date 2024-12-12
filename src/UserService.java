import java.sql.*;
import java.util.Scanner;

public class UserService {
    public static void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO users (username, password, balance) VALUES (?, ?, 0.0)")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ? AND password = ?")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                Account account = new Account(rs.getString("username"), rs.getDouble("balance"));
                userMenu(account);
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void userMenu(Account account) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            account.displayAccountInfo();
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View available books");
            System.out.println("2. Rent a book");
            System.out.println("3. Top up balance");
            System.out.println("4. View inventory");
            System.out.println("5. Return a book");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> AdminService.displayAvailableBooks();
                case 2 -> TransactionService.rentBook(account);
                case 3 -> TransactionService.topUpBalance(account);
                case 4 -> TransactionService.viewInventory(account);
                case 5 -> TransactionService.returnBook(account);
                case 6 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
