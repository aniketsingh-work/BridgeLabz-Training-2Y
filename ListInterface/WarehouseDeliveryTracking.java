import java.util.*;

public class WarehouseDeliveryTracking {
    
    static class Package {
        private String packageId;
        private String recipientName;
        private String destination;
        private String status; // PENDING, DELIVERED, RETURNED, CANCELLED
        private Date deliveryDate;
        
        public Package(String packageId, String recipientName, String destination) {
            this.packageId = packageId;
            this.recipientName = recipientName;
            this.destination = destination;
            this.status = "PENDING";
        }
        
        // Getters and setters
        public String getPackageId() { return packageId; }
        public String getRecipientName() { return recipientName; }
        public String getDestination() { return destination; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public Date getDeliveryDate() { return deliveryDate; }
        public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }
        
        @Override
        public String toString() {
            return "Package{" + packageId + ", " + recipientName + ", " + destination + ", " + status + "}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Package pkg = (Package) obj;
            return Objects.equals(packageId, pkg.packageId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(packageId);
        }
    }
    
    // Use Queue to hold pending deliveries
    private Queue<Package> pendingDeliveries;
    
    // Use Set to store unique package IDs
    private Set<String> uniquePackageIds;
    
    // Use List to maintain delivered packages
    private List<Package> deliveredPackages;
    
    // Use Stack for returned/cancelled packages
    private Stack<Package> returnedPackages;
    
    public WarehouseDeliveryTracking() {
        this.pendingDeliveries = new LinkedList<>();
        this.uniquePackageIds = new HashSet<>();
        this.deliveredPackages = new ArrayList<>();
        this.returnedPackages = new Stack<>();
    }
    
    // 1. Add and process delivery requests in order
    public boolean addDeliveryRequest(Package pkg) {
        if (uniquePackageIds.contains(pkg.getPackageId())) {
            System.out.println("Package ID " + pkg.getPackageId() + " already exists!");
            return false;
        }
        
        uniquePackageIds.add(pkg.getPackageId());
        pendingDeliveries.add(pkg);
        System.out.println("Added delivery request: " + pkg);
        return true;
    }
    
    public boolean processNextDelivery() {
        if (pendingDeliveries.isEmpty()) {
            System.out.println("No pending deliveries to process");
            return false;
        }
        
        Package pkg = pendingDeliveries.remove();
        
        // Simulate delivery process
        Random random = new Random();
        if (random.nextDouble() < 0.8) { // 80% success rate
            pkg.setStatus("DELIVERED");
            pkg.setDeliveryDate(new Date());
            deliveredPackages.add(pkg);
            System.out.println("Successfully delivered: " + pkg.getPackageId());
        } else {
            // Package returned/cancelled
            pkg.setStatus("RETURNED");
            returnedPackages.push(pkg);
            System.out.println("Package returned: " + pkg.getPackageId());
        }
        
        return true;
    }
    
    // 2. Track delivered and returned packages separately
    public List<Package> getDeliveredPackages() {
        return new ArrayList<>(deliveredPackages);
    }
    
    public Stack<Package> getReturnedPackages() {
        return (Stack<Package>) returnedPackages.clone();
    }
    
    // 3. Ensure no duplicate package IDs
    public boolean isUniquePackageId(String packageId) {
        return !uniquePackageIds.contains(packageId);
    }
    
    // 4. Display summary of all deliveries
    public void displayDeliverySummary() {
        System.out.println("=== DELIVERY SUMMARY ===");
        System.out.println("Total pending deliveries: " + pendingDeliveries.size());
        System.out.println("Total delivered packages: " + deliveredPackages.size());
        System.out.println("Total returned packages: " + returnedPackages.size());
        System.out.println("Total unique packages: " + uniquePackageIds.size());
    }
    
    // Getters
    public Queue<Package> getPendingDeliveries() {
        return new LinkedList<>(pendingDeliveries);
    }
    
    public Set<String> getUniquePackageIds() {
        return new HashSet<>(uniquePackageIds);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Warehouse Delivery Tracking System ===");
        
        WarehouseDeliveryTracking deliverySystem = new WarehouseDeliveryTracking();
        
        // Add some delivery requests
        deliverySystem.addDeliveryRequest(new Package("PKG001", "John Doe", "123 Main St"));
        deliverySystem.addDeliveryRequest(new Package("PKG002", "Jane Smith", "456 Oak Ave"));
        deliverySystem.addDeliveryRequest(new Package("PKG003", "Bob Johnson", "789 Pine Rd"));
        deliverySystem.addDeliveryRequest(new Package("PKG004", "Alice Williams", "321 Elm St"));
        
        // Try to add a duplicate package
        deliverySystem.addDeliveryRequest(new Package("PKG001", "Duplicate", "999 Test St"));
        
        System.out.println("\n1. Unique package IDs:");
        for (String id : deliverySystem.getUniquePackageIds()) {
            System.out.println(" " + id);
        }
        
        System.out.println("\n2. Pending deliveries queue size: " + deliverySystem.getPendingDeliveries().size());
        
        System.out.println("\n3. Processing deliveries:");
        while (deliverySystem.getPendingDeliveries().size() > 0) {
            deliverySystem.processNextDelivery();
        }
        
        System.out.println("\n4. Delivered packages:");
        for (Package pkg : deliverySystem.getDeliveredPackages()) {
            System.out.println(" " + pkg);
        }
        
        System.out.println("\n5. Returned packages:");
        Stack<Package> returned = deliverySystem.getReturnedPackages();
        while (!returned.isEmpty()) {
            System.out.println(" " + returned.pop());
        }
        
        System.out.println("\n6. Delivery summary:");
        deliverySystem.displayDeliverySummary();
    }
}