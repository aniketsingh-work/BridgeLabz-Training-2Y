import java.util.*;

public class InventoryRestockManagement {
    
    static class Product {
        private String productName;
        private double price;
        private int stock;
        
        public Product(String productName, double price, int stock) {
            this.productName = productName;
            this.price = price;
            this.stock = stock;
        }
        
        // Getters and setters
        public String getProductName() { return productName; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }
        
        @Override
        public String toString() {
            return productName + "(Price:" + price + ", Stock:" + stock + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Product product = (Product) obj;
            return Objects.equals(productName, product.productName);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(productName);
        }
    }
    
    // Use Set to store unique product names
    private Set<String> uniqueProductNames;
    
    // Use List to maintain all product details
    private List<Product> allProducts;
    
    // Use Queue to track products that need restocking
    private Queue<Product> restockQueue;
    
    // Use Stack to track recently restocked items for quick undo (rollback)
    private Stack<Product> recentlyRestockedStack;
    
    public InventoryRestockManagement() {
        this.uniqueProductNames = new HashSet<>();
        this.allProducts = new ArrayList<>();
        this.restockQueue = new LinkedList<>();
        this.recentlyRestockedStack = new Stack<>();
    }
    
    // 1. Add new products and avoid duplicates
    public boolean addProduct(Product product) {
        if (uniqueProductNames.contains(product.getProductName())) {
            System.out.println("Product " + product.getProductName() + " already exists!");
            return false;
        }
        
        uniqueProductNames.add(product.getProductName());
        allProducts.add(product);
        System.out.println("Added product: " + product);
        
        // Check if the product needs restocking (low stock)
        if (product.getStock() < 10) { // Threshold for restocking
            restockQueue.add(product);
            System.out.println("Product " + product.getProductName() + " added to restock queue (low stock)");
        }
        
        return true;
    }
    
    // 2. Identify items low in stock and enqueue them for restocking
    public void checkLowStock() {
        for (Product product : allProducts) {
            if (product.getStock() < 10 && !restockQueue.contains(product)) {
                restockQueue.add(product);
                System.out.println("Added " + product.getProductName() + " to restock queue (low stock: " + product.getStock() + ")");
            }
        }
    }
    
    // 3. Process the restock queue
    public boolean processRestock() {
        if (restockQueue.isEmpty()) {
            System.out.println("No products in restock queue");
            return false;
        }
        
        Product productToRestock = restockQueue.remove();
        int currentStock = productToRestock.getStock();
        int restockAmount = 20; // Standard restock amount
        
        // Increase stock
        productToRestock.setStock(currentStock + restockAmount);
        
        // Add to recently restocked stack for potential rollback
        recentlyRestockedStack.push(new Product(productToRestock.getProductName(), 
                                               productToRestock.getPrice(), 
                                               currentStock)); // Store previous state
        
        System.out.println("Restocked " + productToRestock.getProductName() + 
                         " from " + currentStock + " to " + productToRestock.getStock());
        
        return true;
    }
    
    // 4. Undo a recent restock operation using the stack
    public boolean undoRecentRestock() {
        if (recentlyRestockedStack.isEmpty()) {
            System.out.println("No recent restocks to undo");
            return false;
        }
        
        Product previousState = recentlyRestockedStack.pop();
        
        // Find the current product and revert to previous state
        for (Product product : allProducts) {
            if (product.getProductName().equals(previousState.getProductName())) {
                product.setStock(previousState.getStock());
                System.out.println("Undid restock for " + product.getProductName() + 
                                 ", reverted to stock: " + previousState.getStock());
                return true;
            }
        }
        
        System.out.println("Could not find product to undo restock: " + previousState.getProductName());
        return false;
    }
    
    // Check if a product needs restocking
    public boolean needsRestocking(Product product) {
        return product.getStock() < 10;
    }
    
    // Getters
    public Set<String> getUniqueProductNames() {
        return new HashSet<>(uniqueProductNames);
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(allProducts);
    }
    
    public Queue<Product> getRestockQueue() {
        return new LinkedList<>(restockQueue);
    }
    
    public Stack<Product> getRecentlyRestockedStack() {
        return (Stack<Product>) recentlyRestockedStack.clone();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Inventory and Restock Management System ===");
        
        InventoryRestockManagement inventorySystem = new InventoryRestockManagement();
        
        // Add some products
        inventorySystem.addProduct(new Product("Laptop", 1200.0, 5));  // Low stock
        inventorySystem.addProduct(new Product("Mouse", 25.0, 50));    // Good stock
        inventorySystem.addProduct(new Product("Keyboard", 75.0, 3));  // Low stock
        inventorySystem.addProduct(new Product("Monitor", 300.0, 15)); // Good stock
        inventorySystem.addProduct(new Product("Headphones", 100.0, 2)); // Very low stock
        
        // Try to add a duplicate product
        inventorySystem.addProduct(new Product("Laptop", 1100.0, 8)); // Should fail
        
        System.out.println("\n1. All products:");
        for (Product product : inventorySystem.getAllProducts()) {
            System.out.println(" " + product);
        }
        
        System.out.println("\n2. Products in restock queue:");
        Queue<Product> queue = inventorySystem.getRestockQueue();
        while (!queue.isEmpty()) {
            System.out.println(" " + queue.remove());
        }
        
        System.out.println("\n3. Processing restocks:");
        while (inventorySystem.getRestockQueue().size() > 0) {
            inventorySystem.processRestock();
        }
        
        System.out.println("\n4. Products after restocking:");
        for (Product product : inventorySystem.getAllProducts()) {
            System.out.println(" " + product);
        }
        
        System.out.println("\n5. Undoing last restock:");
        inventorySystem.undoRecentRestock();
        
        System.out.println("\n6. Products after undo:");
        for (Product product : inventorySystem.getAllProducts()) {
            System.out.println(" " + product);
        }
    }
}