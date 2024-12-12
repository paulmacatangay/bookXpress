import java.util.Scanner;

public class BookRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== bookXpress: Book Rental System ===");
            System.out.println("1. Admin login");
            System.out.println("2. Create an account");
            System.out.println("3. Login");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> AdminService.adminLogin();
                case 2 -> UserService.createAccount();
                case 3 -> UserService.login();
                case 4 -> {
                    System.out.println("Thank you for using bookXpress!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
