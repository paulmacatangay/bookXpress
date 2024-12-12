public class FictionBook extends Book {
    String genre;

    public FictionBook(int copiesAvailable, double cost, String genre) {
        super(copiesAvailable, cost);
        this.genre = genre;
    }

    public void displayBookInfo(String title) {
        super.displayBookInfo(title);
        System.out.println("Genre: " + genre);
    }
}
