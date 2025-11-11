import java.util.*;

public class ECommerceOrderProcessing {
    
    static class Order {
        private String orderId;
        private String customerName;
        private String product;
        private double amount;
        private String status;
        
        public Order(String orderId, String customerName, String product, double amount) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.product = product;
            this.amount = amount;
            this.status = "PENDING"; // Default status
        }
        
        // Getters
        public String getOrderId() { return orderId; }
        public String getCustomerName() { return customerName; }
        public String getProduct() { return product; }
        public double getAmount() { return amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        @Override
        public String toString() {
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", product='" + product + '\'' +
                    ", amount=" + amount +
                    ", status='" + status + '\'' +
                    '}';
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Order order = (Order) obj;
            return Objects.equals(orderId, order.orderId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(orderId);
        }
    }
    
    // Use List to store all orders placed
    private List<Order> allOrders;
    
    // Use Set to remove duplicate orders
    private Set<Order> uniqueOrders;
    
    // Use Queue to process orders one by one (FIFO)
    private Queue<Order> processingQueue;
    
    // Use Stack for orders that failed and need to be retried
    private Stack<Order> failedOrders;
    
    public ECommerceOrderProcessing() {
        this.allOrders = new ArrayList<>();
        this.uniqueOrders = new HashSet<>();
        this.processingQueue = new LinkedList<>();
        this.failedOrders = new Stack<>();
    }
    
    // 1. Add orders to the system
    public void addOrder(Order order) {
        allOrders.add(order);
        uniqueOrders.add(order);
        processingQueue.add(order);
    }
    
    // 2. Identify and remove duplicate orders
    public List<Order> removeDuplicateOrders() {
        List<Order> uniqueOrderList = new ArrayList<>();
        Set<String> seenOrderIds = new HashSet<>();
        
        for (Order order : allOrders) {
            if (seenOrderIds.add(order.getOrderId())) {
                uniqueOrderList.add(order);
            }
        }
        
        return uniqueOrderList;
    }
    
    // 3. Process all valid orders in order of placement
    public void processOrders() {
        System.out.println("Processing orders...");
        while (!processingQueue.isEmpty()) {
            Order order = processingQueue.remove();
            
            // Simulate processing - sometimes orders might fail
            if (Math.random() > 0.8) { // 20% chance of failure
                System.out.println("Order " + order.getOrderId() + " failed, adding to retry stack");
                order.setStatus("FAILED");
                failedOrders.push(order);
            } else {
                System.out.println("Successfully processed order: " + order.getOrderId());
                order.setStatus("COMPLETED");
            }
        }
    }
    
    // 4. Re-process failed orders from the stack
    public void reprocessFailedOrders() {
        System.out.println("Reprocessing failed orders...");
        while (!failedOrders.isEmpty()) {
            Order failedOrder = failedOrders.pop();
            System.out.println("Reprocessing failed order: " + failedOrder.getOrderId());
            
            // Simulate reprocessing
            if (Math.random() > 0.5) { // 50% chance of success on retry
                System.out.println("Successfully reprocessed order: " + failedOrder.getOrderId());
                failedOrder.setStatus("COMPLETED");
            } else {
                System.out.println("Retry failed for order: " + failedOrder.getOrderId());
                failedOrder.setStatus("FAILED_RETRY");
                // Put it back in the queue for another attempt later
                processingQueue.add(failedOrder);
            }
        }
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(allOrders);
    }
    
    public Set<Order> getUniqueOrders() {
        return new HashSet<>(uniqueOrders);
    }
    
    public Queue<Order> getProcessingQueue() {
        return new LinkedList<>(processingQueue);
    }
    
    public Stack<Order> getFailedOrders() {
        return new Stack<Order>() {{
            for (Order order : ECommerceOrderProcessing.this.failedOrders) {
                this.push(order);
            }
        }};
    }
    
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Order Processing System ===");
        
        ECommerceOrderProcessing system = new ECommerceOrderProcessing();
        
        // Add some sample orders
        system.addOrder(new Order("ORD001", "John Doe", "Laptop", 1200.0));
        system.addOrder(new Order("ORD002", "Jane Smith", "Phone", 800.0));
        system.addOrder(new Order("ORD003", "Bob Johnson", "Tablet", 400.0));
        system.addOrder(new Order("ORD001", "John Doe", "Laptop", 1200.0)); // Duplicate
        system.addOrder(new Order("ORD004", "Alice Williams", "Watch", 300.0));
        
        System.out.println("\n1. All orders (including duplicates):");
        for (Order order : system.getAllOrders()) {
            System.out.println(order);
        }
        
        System.out.println("\n2. Unique orders after removing duplicates:");
        List<Order> uniqueOrders = system.removeDuplicateOrders();
        for (Order order : uniqueOrders) {
            System.out.println(order);
        }
        
        System.out.println("\n3. Processing orders in FIFO order:");
        system.processOrders();
        
        System.out.println("\n4. Reprocessing failed orders:");
        system.reprocessFailedOrders();
        
        System.out.println("\n5. Final order statuses:");
        for (Order order : system.removeDuplicateOrders()) {
            System.out.println(order);
        }
    }
}