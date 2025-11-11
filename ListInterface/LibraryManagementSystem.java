import java.util.*;

public class LibraryManagementSystem {
    
    static class Book {
        private String bookId;
        private String title;
        private String author;
        private boolean isAvailable;
        
        public Book(String bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isAvailable = true; // Initially available
        }
        
        // Getters and setters
        public String getBookId() { return bookId; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { this.isAvailable = available; }
        
        @Override
        public String toString() {
            return title + " by " + author + "(ID:" + bookId + ", Available:" + isAvailable + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Book book = (Book) obj;
            return Objects.equals(bookId, book.bookId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(bookId);
        }
    }
    
    static class Member {
        private String memberId;
        private String name;
        private String email;
        
        public Member(String memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }
        
        // Getters
        public String getMemberId() { return memberId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return name + "(ID:" + memberId + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Member member = (Member) obj;
            return Objects.equals(memberId, member.memberId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(memberId);
        }
    }
    
    // Use List for total books available
    private List<Book> totalBooks;
    
    // Use Set for unique member IDs
    private Set<String> memberIds;
    
    // Use Queue for books waiting to be issued
    private Queue<Book> booksWaitingToBeIssued;
    
    // Use Stack to store recently returned books for quick re-issue
    private Stack<Book> recentlyReturnedBooks;
    
    public LibraryManagementSystem() {
        this.totalBooks = new ArrayList<>();
        this.memberIds = new HashSet<>();
        this.booksWaitingToBeIssued = new LinkedList<>();
        this.recentlyReturnedBooks = new Stack<>();
    }
    
    // 1. Add books to the system
    public void addBook(Book book) {
        totalBooks.add(book);
        System.out.println("Added book: " + book);
    }
    
    // 2. Manage book issuing and returning
    public boolean issueBookToMember(String memberId, String bookId) {
        if (!memberIds.contains(memberId)) {
            System.out.println("Member ID " + memberId + " not found!");
            return false;
        }
        
        Book bookToIssue = null;
        for (Book book : totalBooks) {
            if (book.getBookId().equals(bookId) && book.isAvailable()) {
                bookToIssue = book;
                break;
            }
        }
        
        if (bookToIssue == null) {
            System.out.println("Book ID " + bookId + " not available!");
            return false;
        }
        
        // Issue the book
        bookToIssue.setAvailable(false);
        System.out.println("Book issued to member " + memberId + ": " + bookToIssue.getTitle());
        return true;
    }
    
    public boolean returnBook(String bookId) {
        Book bookToReturn = null;
        for (Book book : totalBooks) {
            if (book.getBookId().equals(bookId) && !book.isAvailable()) {
                bookToReturn = book;
                break;
            }
        }
        
        if (bookToReturn == null) {
            System.out.println("Book ID " + bookId + " not found or already returned!");
            return false;
        }
        
        // Return the book
        bookToReturn.setAvailable(true);
        recentlyReturnedBooks.push(bookToReturn);
        System.out.println("Book returned: " + bookToReturn.getTitle());
        return true;
    }
    
    // 3. Prevent duplicate member registrations
    public boolean registerMember(Member member) {
        if (memberIds.contains(member.getMemberId())) {
            System.out.println("Member " + member.getName() + " already registered!");
            return false;
        }
        
        memberIds.add(member.getMemberId());
        System.out.println("Member registered: " + member);
        return true;
    }
    
    // 4. Re-issue the most recently returned book
    public boolean reIssueRecentlyReturnedBook(String memberId) {
        if (recentlyReturnedBooks.isEmpty()) {
            System.out.println("No recently returned books to re-issue");
            return false;
        }
        
        if (!memberIds.contains(memberId)) {
            System.out.println("Member ID " + memberId + " not found!");
            return false;
        }
        
        Book book = recentlyReturnedBooks.pop();
        
        // Issue the book to the member
        book.setAvailable(false);
        System.out.println("Recently returned book re-issued to member " + memberId + ": " + book.getTitle());
        return true;
    }
    
    // Getters
    public List<Book> getTotalBooks() {
        return new ArrayList<>(totalBooks);
    }
    
    public Set<String> getMemberIds() {
        return new HashSet<>(memberIds);
    }
    
    public Queue<Book> getBooksWaitingToBeIssued() {
        return new LinkedList<>(booksWaitingToBeIssued);
    }
    
    public Stack<Book> getRecentlyReturnedBooks() {
        return (Stack<Book>) recentlyReturnedBooks.clone();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===");
        
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        // Add books to the system
        library.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("B002", "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("B03", "1984", "George Orwell"));
        library.addBook(new Book("B04", "Pride and Prejudice", "Jane Austen"));
        
        // Register members
        library.registerMember(new Member("M001", "John Doe", "john@email.com"));
        library.registerMember(new Member("M02", "Jane Smith", "jane@email.com"));
        library.registerMember(new Member("M001", "John Duplicate", "john2@email.com")); // This should fail
        
        System.out.println("\n1. Total books in library: " + library.getTotalBooks().size());
        for (Book book : library.getTotalBooks()) {
            System.out.println(" " + book);
        }
        
        System.out.println("\n2. Registered members: " + library.getMemberIds().size());
        
        // Issue some books
        System.out.println("\n3. Issuing books:");
        library.issueBookToMember("M001", "B001");
        library.issueBookToMember("M002", "B002");
        
        // Try to issue an already issued book
        library.issueBookToMember("M001", "B001");
        
        // Return a book
        System.out.println("\n4. Returning a book:");
        library.returnBook("B001");
        
        // Check recently returned books
        System.out.println("\n5. Recently returned books: " + library.getRecentlyReturnedBooks().size());
        
        // Re-issue the recently returned book
        System.out.println("\n6. Re-issuing recently returned book:");
        library.reIssueRecentlyReturnedBook("M02");
        
        System.out.println("\n7. Books after re-issue:");
        for (Book book : library.getTotalBooks()) {
            System.out.println(" " + book);
        }
    }
}