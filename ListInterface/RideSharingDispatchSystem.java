import java.util.*;

public class RideSharingDispatchSystem {
    
    static class Driver {
        private String driverId;
        private String name;
        private boolean available;
        private double rating;
        private String location;
        
        public Driver(String driverId, String name, double rating, String location) {
            this.driverId = driverId;
            this.name = name;
            this.rating = rating;
            this.location = location;
            this.available = true; // Initially available
        }
        
        // Getters and setters
        public String getDriverId() { return driverId; }
        public String getName() { return name; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
        public double getRating() { return rating; }
        public String getLocation() { return location; }
        
        @Override
        public String toString() {
            return name + "(ID:" + driverId + ", Rating:" + rating + ", Available:" + available + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Driver driver = (Driver) obj;
            return Objects.equals(driverId, driver.driverId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(driverId);
        }
    }
    
    static class RideRequest {
        private String requestId;
        private String passengerName;
        private String pickupLocation;
        private String destination;
        private int priority; // Higher number means higher priority
        private double estimatedFare;
        
        public RideRequest(String requestId, String passengerName, String pickupLocation, 
                          String destination, int priority, double estimatedFare) {
            this.requestId = requestId;
            this.passengerName = passengerName;
            this.pickupLocation = pickupLocation;
            this.destination = destination;
            this.priority = priority;
            this.estimatedFare = estimatedFare;
        }
        
        // Getters
        public String getRequestId() { return requestId; }
        public String getPassengerName() { return passengerName; }
        public String getPickupLocation() { return pickupLocation; }
        public String getDestination() { return destination; }
        public int getPriority() { return priority; }
        public double getEstimatedFare() { return estimatedFare; }
        
        @Override
        public String toString() {
            return passengerName + "(ID:" + requestId + ", Priority:" + priority + 
                   ", From:" + pickupLocation + " To:" + destination + ")";
        }
    }
    
    static class Ride {
        private String rideId;
        private String passengerName;
        private String driverName;
        private String pickupLocation;
        private String destination;
        private double fare;
        private String status; // COMPLETED, CANCELLED, etc.
        
        public Ride(String rideId, String passengerName, String driverName, String pickupLocation, 
                   String destination, double fare) {
            this.rideId = rideId;
            this.passengerName = passengerName;
            this.driverName = driverName;
            this.pickupLocation = pickupLocation;
            this.destination = destination;
            this.fare = fare;
            this.status = "COMPLETED";
        }
        
        // Getters
        public String getRideId() { return rideId; }
        public String getPassengerName() { return passengerName; }
        public String getDriverName() { return driverName; }
        public String getPickupLocation() { return pickupLocation; }
        public String getDestination() { return destination; }
        public double getFare() { return fare; }
        public String getStatus() { return status; }
        
        @Override
        public String toString() {
            return "Ride{" + rideId + ", " + passengerName + " with " + driverName + 
                   ", From:" + pickupLocation + " To:" + destination + ", Fare:" + fare + "}";
        }
    }
    
    // Use Queue for pending ride requests
    private Queue<RideRequest> pendingRideRequests;
    
    // Use Set to store available drivers (no duplicates)
    private Set<Driver> availableDrivers;
    
    // Use List to maintain completed rides
    private List<Ride> completedRides;
    
    // Use PriorityQueue for assigning rides based on proximity or urgency
    private PriorityQueue<RideRequest> priorityRideRequests;
    
    public RideSharingDispatchSystem() {
        this.pendingRideRequests = new LinkedList<>();
        this.availableDrivers = new HashSet<>();
        this.completedRides = new ArrayList<>();
        // Priority queue with custom comparator (higher priority value first)
        this.priorityRideRequests = new PriorityQueue<>((r1, r2) -> 
            Integer.compare(r2.getPriority(), r1.getPriority()));
    }
    
    // 1. Add incoming ride requests
    public void addRideRequest(RideRequest request) {
        pendingRideRequests.add(request);
        priorityRideRequests.add(request);
        System.out.println("Added ride request: " + request);
    }
    
    // 2. Assign drivers from the available pool
    public boolean assignDriverToRide() {
        if (pendingRideRequests.isEmpty() || availableDrivers.isEmpty()) {
            System.out.println("No pending requests or available drivers");
            return false;
        }
        
        RideRequest request = pendingRideRequests.remove();
        
        // Find the best available driver (for simplicity, we'll take the first one)
        Driver assignedDriver = null;
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable()) {
                assignedDriver = driver;
                break;
            }
        }
        
        if (assignedDriver == null) {
            System.out.println("No available drivers for request: " + request.getRequestId());
            return false;
        }
        
        // Mark driver as unavailable
        assignedDriver.setAvailable(false);
        availableDrivers.remove(assignedDriver);
        
        // Create a ride record
        Ride ride = new Ride(
            "RIDE" + System.currentTimeMillis(),
            request.getPassengerName(),
            assignedDriver.getName(),
            request.getPickupLocation(),
            request.getDestination(),
            request.getEstimatedFare()
        );
        
        System.out.println("Assigned driver " + assignedDriver.getName() + " to " + request.getPassengerName());
        return true;
    }
    
    // 3. Move completed rides to the ride history list
    public void completeRide(String rideId, String driverId) {
        // In a real system, we would track active rides and complete them
        // For this example, we'll just add a completed ride to the history
        // assuming we have some driver to assign
        
        Driver driver = null;
        for (Driver d : availableDrivers) {
            if (d.getDriverId().equals(driverId)) {
                driver = d;
                break;
            }
        }
        
        if (driver != null) {
            Ride completedRide = new Ride(
                rideId,
                "Sample Passenger",
                driver.getName(),
                "Sample Pickup",
                "Sample Destination",
                25.0
            );
            completedRides.add(completedRide);
            System.out.println("Completed ride: " + completedRide);
            
            // Make driver available again
            driver.setAvailable(true);
            availableDrivers.add(driver);
        }
    }
    
    // 4. Handle high-priority requests first
    public void processHighPriorityRequests() {
        System.out.println("Processing high priority requests...");
        while (!priorityRideRequests.isEmpty()) {
            RideRequest highPriorityRequest = priorityRideRequests.remove();
            System.out.println("Processing high priority request: " + highPriorityRequest);
            
            // Try to assign a driver
            if (availableDrivers.size() > 0) {
                Driver assignedDriver = availableDrivers.iterator().next();
                assignedDriver.setAvailable(false);
                availableDrivers.remove(assignedDriver);
                
                System.out.println("Assigned driver " + assignedDriver.getName() + 
                                 " to high priority request: " + highPriorityRequest.getRequestId());
            } else {
                System.out.println("No available drivers for high priority request: " + 
                                 highPriorityRequest.getRequestId());
            }
        }
    }
    
    // Add driver to the system
    public void addDriver(Driver driver) {
        availableDrivers.add(driver);
        System.out.println("Added driver: " + driver);
    }
    
    // Getters
    public Queue<RideRequest> getPendingRideRequests() {
        return new LinkedList<>(pendingRideRequests);
    }
    
    public Set<Driver> getAvailableDrivers() {
        return new HashSet<>(availableDrivers);
    }
    
    public List<Ride> getCompletedRides() {
        return new ArrayList<>(completedRides);
    }
    
    public PriorityQueue<RideRequest> getPriorityRideRequests() {
        return new PriorityQueue<>(priorityRideRequests);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Ride-Sharing Dispatch System ===");
        
        RideSharingDispatchSystem system = new RideSharingDispatchSystem();
        
        // Add some drivers
        system.addDriver(new Driver("D001", "John Driver", 4.8, "Downtown"));
        system.addDriver(new Driver("D002", "Jane Driver", 4.9, "Uptown"));
        system.addDriver(new Driver("D003", "Bob Driver", 4.5, "Midtown"));
        
        // Add ride requests
        system.addRideRequest(new RideRequest("REQ001", "Alice Passenger", "Downtown", "Airport", 2, 30.0));
        system.addRideRequest(new RideRequest("REQ002", "Charlie Passenger", "Uptown", "Mall", 5, 20.0)); // High priority
        system.addRideRequest(new RideRequest("REQ003", "Eve Passenger", "Midtown", "Stadium", 1, 25.0)); // Low priority
        system.addRideRequest(new RideRequest("REQ004", "David Passenger", "Downtown", "Beach", 4, 40.0)); // High priority
        
        System.out.println("\n1. Available drivers: " + system.getAvailableDrivers().size());
        for (Driver driver : system.getAvailableDrivers()) {
            System.out.println(" " + driver);
        }
        
        System.out.println("\n2. Pending ride requests: " + system.getPendingRideRequests().size());
        
        System.out.println("\n3. Processing high priority requests first:");
        system.processHighPriorityRequests();
        
        System.out.println("\n4. Assigning remaining drivers to requests:");
        while (system.getPendingRideRequests().size() > 0 && system.getAvailableDrivers().size() > 0) {
            system.assignDriverToRide();
        }
        
        System.out.println("\n5. Completed rides: " + system.getCompletedRides().size());
    }
}