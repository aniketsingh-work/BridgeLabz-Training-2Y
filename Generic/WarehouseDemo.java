import java.util.ArrayList;
import java.util.List;

public class WarehouseDemo {
    // Wildcard method to display all items in storage regardless of their type
    public static void displayAllItems(List<? extends WarehouseItem> items) {
        System.out.println("All items in warehouse:");
        for (WarehouseItem item : items) {
            System.out.println("- " + item);
        }
    }
    
    public static void main(String[] args) {
        // Create storage for different types of items
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.addItem(new Electronics("Laptop", 999.99));
        electronicsStorage.addItem(new Electronics("Smartphone", 699.99));
        System.out.println("Electronics Storage:");
        electronicsStorage.displayItems();
        
        System.out.println();
        
        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.addItem(new Groceries("Milk", 3.99));
        groceriesStorage.addItem(new Groceries("Bread", 2.49));
        System.out.println("Groceries Storage:");
        groceriesStorage.displayItems();
        
        System.out.println();
        
        Storage<Furniture> furnitureStorage = new Storage<>();
        furnitureStorage.addItem(new Furniture("Chair", 89.99));
        furnitureStorage.addItem(new Furniture("Table", 199.99));
        System.out.println("Furniture Storage:");
        furnitureStorage.displayItems();
        
        // Create a mixed list to demonstrate wildcard method
        List<WarehouseItem> allItems = new ArrayList<>();
        allItems.add(new Electronics("TV", 499.99));
        allItems.add(new Groceries("Eggs", 4.99));
        allItems.add(new Furniture("Sofa", 599.99));
        
        System.out.println();
        displayAllItems(allItems);
    }
}