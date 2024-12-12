public class Account {
    String username;
    double balance;

    public Account(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public void displayAccountInfo() {
        System.out.println("\nUser: " + username + " | Balance: $" + balance);
    }
}
