package library.transactions;

import library.books.Book;
import library.members.Member;

public class Transaction {
    private Member member;
    private Book book;
    private String issueDate;
    private String returnDate;
    private boolean isReturned;
    
    public Transaction(Member member, Book book, String issueDate) {
        this.member = member;
        this.book = book;
        this.issueDate = issueDate;
        this.isReturned = false;
    }
    
    public void issueBook() {
        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book '" + book.getTitle() + "' issued to member " + member.getName());
            System.out.println("Issue Date: " + issueDate);
        } else {
            System.out.println("Book '" + book.getTitle() + "' is not available for issue.");
        }
    }
    
    public void returnBook(String returnDate) {
        this.returnDate = returnDate;
        book.setAvailable(true);
        isReturned = true;
        System.out.println("Book '" + book.getTitle() + "' returned by member " + member.getName());
        System.out.println("Return Date: " + returnDate);
    }
    
    public void displayTransactionDetails() {
        System.out.println("Transaction Details:");
        System.out.println("Member: " + member.getName());
        System.out.println("Book: " + book.getTitle());
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Return Date: " + (isReturned ? returnDate : "Not returned yet"));
        System.out.println("Status: " + (isReturned ? "Completed" : "Active"));
    }
}