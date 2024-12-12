public class NonFictionBook extends Book {
    String subject;

    public NonFictionBook(int copiesAvailable, double cost, String subject) {
        super(copiesAvailable, cost);
        this.subject = subject;
    }

    public void displayBookInfo(String title) {
        super.displayBookInfo(title);
        System.out.println("Subject: " + subject);
    }
}
