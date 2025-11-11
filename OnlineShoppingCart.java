import java.util.*;

public class OnlineShoppingCart {
    private Map<String, Double> cart;
    
    public OnlineShoppingCart() {
        cart = new LinkedHashMap<>();
    }
    
    public void addProduct(String productName, double price) {
        cart.put(productName, price);
    }
    
    public void removeProduct(String productName) {
        cart.remove(productName);
    }
    
    public double calculateTotal() {
        double total = 0.0;
        for (double price : cart.values()) {
            total += price;
        }
        return total;
    }
    
    public double calculateTotalWithDiscount() {
        double total = calculateTotal();
        if (total > 5000) {
            return total * 0.9; // 10% discount
        }
        return total;
    }
    
    public void displayProducts() {
        for (Map.Entry<String, Double> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        OnlineShoppingCart shoppingCart = new OnlineShoppingCart();
        shoppingCart.addProduct("Laptop", 45000.0);
        shoppingCart.addProduct("Mouse", 500.0);
        shoppingCart.addProduct("Keyboard", 1500.0);
        shoppingCart.addProduct("Monitor", 12000.0);
        shoppingCart.addProduct("Webcam", 3000.0);
        
        System.out.println("Products in cart:");
        shoppingCart.displayProducts();
        
        System.out.println("Total bill: " + shoppingCart.calculateTotal());
        System.out.println("Total bill with discount: " + shoppingCart.calculateTotalWithDiscount());
    }
}