import java.util.*;

public class LibraryBookCatalog {
    private Map<String, String> catalog;
    
    public LibraryBookCatalog() {
        catalog = new HashMap<>();
    }
    
    public void addBook(String isbn, String title) {
        catalog.put(isbn, title);
    }
    
    public String searchByISBN(String isbn) {
        if (catalog.containsKey(isbn)) {
            return catalog.get(isbn);
        }
        return "Book not found";
    }
    
    public void removeBook(String isbn) {
        catalog.remove(isbn);
    }
    
    public void printAllBooksSortedByISBN() {
        Map<String, String> sortedCatalog = new TreeMap<>(catalog);
        for (Map.Entry<String, String> entry : sortedCatalog.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public List<String> searchByTitle(String title) {
        List<String> matchingISBNs = new ArrayList<>();
        for (Map.Entry<String, String> entry : catalog.entrySet()) {
            if (entry.getValue().toLowerCase().contains(title.toLowerCase())) {
                matchingISBNs.add(entry.getKey());
            }
        }
        return matchingISBNs;
    }
    
    public static void main(String[] args) {
        LibraryBookCatalog library = new LibraryBookCatalog();
        library.addBook("978-1234567890", "Java Programming");
        library.addBook("978-0987654321", "Data Structures");
        library.addBook("978-11111111", "Algorithms");
        
        System.out.println("Search by ISBN '978-1234567890': " + library.searchByISBN("978-1234567890"));
        System.out.println("Search by title 'Java': " + library.searchByTitle("Java"));
        
        library.printAllBooksSortedByISBN();
    }
}