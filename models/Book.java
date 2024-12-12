public class Book {
    int copiesAvailable;
    double cost;

    public Book(int copiesAvailable, double cost) {
        this.copiesAvailable = copiesAvailable;
        this.cost = cost;
    }

    public void displayBookInfo(String title) {
        System.out.println("Title: " + title + " | Copies: " + copiesAvailable + " | Cost: $" + cost);
    }
}
