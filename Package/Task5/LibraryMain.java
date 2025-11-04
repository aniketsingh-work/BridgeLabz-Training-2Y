import library.books.Book;
import library.members.Member;
import library.transactions.Transaction;

public class LibraryMain {
    public static void main(String[] args) {
        System.out.println("Library Management System Demo");
        System.out.println("==============================");
        
        // 1. Adding a new book
        System.out.println("1. Adding a new book:");
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        book.displayBookDetails();
        System.out.println();
        
        // 2. Registering a new member
        System.out.println("2. Registering a new member:");
        Member member = new Member("M001", "John Smith", "john.smith@email.com");
        member.displayMemberDetails();
        System.out.println();
        
        // 3. Issuing a book to a member
        System.out.println("3. Issuing a book to a member:");
        Transaction transaction = new Transaction(member, book, "2023-06-15");
        transaction.issueBook();
        System.out.println();
        
        // Display transaction details
        transaction.displayTransactionDetails();
        System.out.println();
        
        // Simulate returning the book
        System.out.println("Simulating book return:");
        transaction.returnBook("2023-07-15");
        System.out.println();
        transaction.displayTransactionDetails();
    }
}