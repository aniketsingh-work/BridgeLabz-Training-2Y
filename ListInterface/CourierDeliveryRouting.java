import java.util.*;

public class CourierDeliveryRouting {
    
    static class Parcel {
        private String parcelId;
        private String recipientName;
        private String destination;
        private int priority; // Higher number means higher priority
        private String status; // PENDING, ASSIGNED, COMPLETED, etc.
        private String assignedDeliveryId;
        
        public Parcel(String parcelId, String recipientName, String destination, int priority) {
            this.parcelId = parcelId;
            this.recipientName = recipientName;
            this.destination = destination;
            this.priority = priority;
            this.status = "PENDING";
        }
        
        // Getters and setters
        public String getParcelId() { return parcelId; }
        public String getRecipientName() { return recipientName; }
        public String getDestination() { return destination; }
        public int getPriority() { return priority; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getAssignedDeliveryId() { return assignedDeliveryId; }
        public void setAssignedDeliveryId(String assignedDeliveryId) { this.assignedDeliveryId = assignedDeliveryId; }
        
        @Override
        public String toString() {
            return "Parcel{" + parcelId + ", " + recipientName + ", Priority:" + priority + ", Status:" + status + "}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Parcel parcel = (Parcel) obj;
            return Objects.equals(parcelId, parcel.parcelId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(parcelId);
        }
    }
    
    // Use PriorityQueue to assign high-priority deliveries first
    private PriorityQueue<Parcel> priorityParcels;
    
    // Use Set to track already assigned delivery IDs
    private Set<String> assignedDeliveryIds;
    
    // Use List to maintain completed deliveries
    private List<Parcel> completedDeliveries;
    
    // Use Queue for normal pending deliveries
    private Queue<Parcel> normalPendingDeliveries;
    
    public CourierDeliveryRouting() {
        // Priority queue with custom comparator (higher priority value first)
        this.priorityParcels = new PriorityQueue<>((p1, p2) -> 
            Integer.compare(p2.getPriority(), p1.getPriority()));
        this.assignedDeliveryIds = new HashSet<>();
        this.completedDeliveries = new ArrayList<>();
        this.normalPendingDeliveries = new LinkedList<>();
    }
    
    // 1. Add parcels and assign delivery priority
    public void addParcel(Parcel parcel) {
        if (parcel.getPriority() > 5) { // High priority
            priorityParcels.add(parcel);
            System.out.println("Added high priority parcel to priority queue: " + parcel);
        } else {
            normalPendingDeliveries.add(parcel);
            System.out.println("Added normal priority parcel to queue: " + parcel);
        }
    }
    
    // 2. Assign available delivery agents
    public boolean assignDeliveryAgent() {
        Parcel parcelToAssign = null;
        
        // First check priority queue
        if (!priorityParcels.isEmpty()) {
            parcelToAssign = priorityParcels.remove();
            System.out.println("Processing high priority parcel: " + parcelToAssign.getParcelId());
        } 
        // Then check normal queue
        else if (!normalPendingDeliveries.isEmpty()) {
            parcelToAssign = normalPendingDeliveries.remove();
            System.out.println("Processing normal priority parcel: " + parcelToAssign.getParcelId());
        }
        
        if (parcelToAssign == null) {
            System.out.println("No parcels to assign");
            return false;
        }
        
        // Generate a delivery ID
        String deliveryId = "DEL" + System.currentTimeMillis();
        
        // Check if this ID is already assigned (should not happen with timestamp, but just in case)
        while (assignedDeliveryIds.contains(deliveryId)) {
            deliveryId = "DEL" + (System.currentTimeMillis() + new Random().nextInt(1000));
        }
        
        // Assign the delivery ID
        parcelToAssign.setAssignedDeliveryId(deliveryId);
        parcelToAssign.setStatus("ASSIGNED");
        assignedDeliveryIds.add(deliveryId);
        
        System.out.println("Assigned delivery ID " + deliveryId + " to parcel " + parcelToAssign.getParcelId());
        return true;
    }
    
    // 3. Move completed deliveries to the list
    public boolean completeDelivery(String parcelId) {
        // Find the parcel in priority queue
        Parcel parcel = null;
        PriorityQueue<Parcel> tempPriorityQueue = new PriorityQueue<>((p1, p2) -> 
            Integer.compare(p2.getPriority(), p1.getPriority()));
        
        while (!priorityParcels.isEmpty()) {
            Parcel p = priorityParcels.remove();
            if (p.getParcelId().equals(parcelId)) {
                parcel = p;
                break;
            }
            tempPriorityQueue.add(p);
        }
        
        // Put back the non-matching parcels
        while (!tempPriorityQueue.isEmpty()) {
            priorityParcels.add(tempPriorityQueue.remove());
        }
        
        // If not found in priority queue, check normal queue
        if (parcel == null) {
            Queue<Parcel> tempNormalQueue = new LinkedList<>();
            while (!normalPendingDeliveries.isEmpty()) {
                Parcel p = normalPendingDeliveries.remove();
                if (p.getParcelId().equals(parcelId)) {
                    parcel = p;
                } else {
                    tempNormalQueue.add(p);
                }
            }
            
            // Put back the non-matching parcels
            while (!tempNormalQueue.isEmpty()) {
                normalPendingDeliveries.add(tempNormalQueue.remove());
            }
        }
        
        if (parcel == null) {
            System.out.println("Parcel with ID " + parcelId + " not found");
            return false;
        }
        
        // Mark as completed and add to completed list
        parcel.setStatus("COMPLETED");
        completedDeliveries.add(parcel);
        
        System.out.println("Completed delivery for parcel: " + parcelId);
        return true;
    }
    
    // 4. Ensure no duplicate delivery IDs
    public boolean isUniqueDeliveryId(String deliveryId) {
        return !assignedDeliveryIds.contains(deliveryId);
    }
    
    // Getters
    public PriorityQueue<Parcel> getPriorityParcels() {
        return new PriorityQueue<>(priorityParcels);
    }
    
    public Set<String> getAssignedDeliveryIds() {
        return new HashSet<>(assignedDeliveryIds);
    }
    
    public List<Parcel> getCompletedDeliveries() {
        return new ArrayList<>(completedDeliveries);
    }
    
    public Queue<Parcel> getNormalPendingDeliveries() {
        return new LinkedList<>(normalPendingDeliveries);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Courier Delivery Routing System ===");
        
        CourierDeliveryRouting courierSystem = new CourierDeliveryRouting();
        
        // Add parcels with different priorities
        courierSystem.addParcel(new Parcel("P001", "John Doe", "123 Main St", 8)); // High priority
        courierSystem.addParcel(new Parcel("P002", "Jane Smith", "456 Oak Ave", 3)); // Normal priority
        courierSystem.addParcel(new Parcel("P003", "Bob Johnson", "789 Pine Rd", 9)); // High priority
        courierSystem.addParcel(new Parcel("P004", "Alice Williams", "321 Elm St", 2)); // Normal priority
        courierSystem.addParcel(new Parcel("P005", "Charlie Brown", "654 Maple Dr", 7)); // High priority
        
        System.out.println("\n1. Priority parcels queue size: " + courierSystem.getPriorityParcels().size());
        System.out.println("   Normal pending parcels queue size: " + courierSystem.getNormalPendingDeliveries().size());
        
        System.out.println("\n2. Assigning delivery agents:");
        for (int i = 0; i < 5; i++) {
            courierSystem.assignDeliveryAgent();
        }
        
        System.out.println("\n3. Assigned delivery IDs: " + courierSystem.getAssignedDeliveryIds().size());
        
        System.out.println("\n4. Completing deliveries:");
        courierSystem.completeDelivery("P001");
        courierSystem.completeDelivery("P002");
        
        System.out.println("\n5. Completed deliveries: " + courierSystem.getCompletedDeliveries().size());
        for (Parcel parcel : courierSystem.getCompletedDeliveries()) {
            System.out.println(" " + parcel);
        }
    }
}