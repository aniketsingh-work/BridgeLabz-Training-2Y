import java.util.Arrays;
import java.util.List;

public class PriceCalculator {
    public static double calculateTotal(List<? extends Product> items) {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }
    
    public static void main(String[] args) {
        // Test with List<Mobile>
        List<Mobile> mobiles = Arrays.asList(
            new Mobile("iPhone", 99.99),
            new Mobile("Samsung Galaxy", 799.99),
            new Mobile("Google Pixel", 699.99)
        );
        System.out.println("Total price of mobiles: $" + calculateTotal(mobiles));
        
        // Test with List<Laptop>
        List<Laptop> laptops = Arrays.asList(
            new Laptop("MacBook Pro", 1999.99),
            new Laptop("Dell XPS", 1299.99),
            new Laptop("HP Spectre", 1499.99)
        );
        System.out.println("Total price of laptops: $" + calculateTotal(laptops));
        
        // Test with mixed list of products
        List<Product> allProducts = Arrays.asList(
            new Mobile("iPhone", 999.99),
            new Laptop("MacBook Pro", 1999.99),
            new Mobile("Samsung Galaxy", 799.99)
        );
        System.out.println("Total price of mixed products: $" + calculateTotal(allProducts));
    }
}