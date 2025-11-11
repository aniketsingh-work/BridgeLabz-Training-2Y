import java.util.*;

public class InventoryManagement {
    private Map<String, Integer> inventory;
    
    public InventoryManagement() {
        inventory = new HashMap<>();
    }
    
    public void addProduct(String productName, int quantity) {
        inventory.put(productName, quantity);
    }
    
    public void reduceQuantity(String productName, int quantity) {
        if (inventory.containsKey(productName)) {
            int currentQuantity = inventory.get(productName);
            int newQuantity = currentQuantity - quantity;
            if (newQuantity <= 0) {
                inventory.put(productName, 0);
            } else {
                inventory.put(productName, newQuantity);
            }
        }
    }
    
    public void increaseQuantity(String productName, int quantity) {
        if (inventory.containsKey(productName)) {
            inventory.put(productName, inventory.get(productName) + quantity);
        }
    }
    
    public int getQuantity(String productName) {
        if (inventory.containsKey(productName)) {
            return inventory.get(productName);
        }
        return -1; // indicates not stocked
    }
    
    public List<String> getOutOfStockProducts() {
        List<String> outOfStock = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() == 0) {
                outOfStock.add(entry.getKey());
            }
        }
        return outOfStock;
    }
    
    public static void main(String[] args) {
        InventoryManagement store = new InventoryManagement();
        store.addProduct("Apples", 50);
        store.addProduct("Bananas", 0);
        store.addProduct("Oranges", 30);
        
        store.reduceQuantity("Apples", 45);
        store.increaseQuantity("Oranges", 20);
        
        System.out.println("Apples quantity: " + store.getQuantity("Apples"));
        System.out.println("Bananas quantity: " + store.getQuantity("Bananas"));
        
        System.out.println("Out of stock products: " + store.getOutOfStockProducts());
    }
}