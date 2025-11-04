import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private double price;
    private double rating;
    private double discount; // in percentage
    
    public Product(String name, double price, double rating, double discount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.discount = discount;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getRating() {
        return rating;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (Rating: %.1f, Discount: %.1f%%)", 
                           name, price, rating, discount);
    }
}

public class ECommerceSorting {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, 4.5, 10.0),
            new Product("Smartphone", 699.99, 4.7, 5.0),
            new Product("Headphones", 199.99, 4.2, 15.0),
            new Product("Tablet", 399.99, 4.0, 20.0),
            new Product("Smart Watch", 299.99, 4.3, 8.0),
            new Product("Camera", 599.99, 4.6, 12.0)
        );
        
        System.out.println("Original Product List:");
        products.forEach(System.out::println);
        System.out.println();
        
        // Sort by price using lambda with Comparator
        System.out.println("Sorted by Price (Low to High):");
        List<Product> sortedByPrice = products.stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .collect(Collectors.toList());
        sortedByPrice.forEach(System.out::println);
        System.out.println();
        
        // Sort by rating using lambda with Comparator
        System.out.println("Sorted by Rating (High to Low):");
        List<Product> sortedByRating = products.stream()
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .collect(Collectors.toList());
        sortedByRating.forEach(System.out::println);
        System.out.println();
        
        // Sort by discount using lambda with Comparator.comparing()
        System.out.println("Sorted by Discount (High to Low):");
        List<Product> sortedByDiscount = products.stream()
                .sorted(Comparator.comparing(Product::getDiscount).reversed())
                .collect(Collectors.toList());
        sortedByDiscount.forEach(System.out::println);
        System.out.println();
        
        // Dynamic sorting based on sales campaign using lambda
        System.out.println("Dynamic sorting for 'Budget Sale' campaign (by price):");
        List<Product> budgetSale = sortProducts(products, 
                (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        budgetSale.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Dynamic sorting for 'Premium Products' campaign (by rating):");
        List<Product> premiumProducts = sortProducts(products, 
                (p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
        premiumProducts.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Dynamic sorting for 'Clearance Sale' campaign (by discount):");
        List<Product> clearanceSale = sortProducts(products, 
                (p1, p2) -> Double.compare(p2.getDiscount(), p1.getDiscount()));
        clearanceSale.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Lambdas allow dynamic sorting based on different criteria without");
        System.out.println("creating separate comparator classes for each sorting strategy.");
    }
    
    // Method that accepts a comparator as a lambda
    public static List<Product> sortProducts(List<Product> products, Comparator<Product> comparator) {
        return products.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}