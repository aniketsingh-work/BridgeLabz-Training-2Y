// Original vehicle interface with basic dashboard functionality
interface VehicleDashboard {
    void displaySpeed();
    
    // New default method for displaying battery percentage (for electric vehicles)
    // This was added later without breaking existing implementations
    default void displayBatteryPercentage() {
        System.out.println("Battery: Feature not available for this vehicle type");
    }
    
    // Default method for fuel efficiency
    default void displayFuelEfficiency() {
        System.out.println("Fuel efficiency: Calculating...");
        System.out.println("Average MPG: 25-30 MPG");
    }
    
    // Default method for trip information
    default void displayTripInfo() {
        System.out.println("Trip info: Distance traveled: 0 miles, Average speed: 0 mph");
    }
    
    // Default method for maintenance alerts
    default void checkMaintenance() {
        System.out.println("Maintenance check: Next service in 5000 miles");
    }
    
    // Default method for eco mode status
    default void displayEcoModeStatus() {
        System.out.println("Eco Mode: OFF");
    }
}

// Traditional car implementation (before battery percentage was added to the interface)
class GasolineCar implements VehicleDashboard {
    private int currentSpeed;
    private int fuelLevel; // in percentage
    
    public GasolineCar(int speed, int fuelLevel) {
        this.currentSpeed = speed;
        this.fuelLevel = fuelLevel;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Current speed: " + currentSpeed + " mph");
    }
    
    // Gasoline car can override the default battery method if needed
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Gasoline car does not have a battery percentage");
    }
    
    // Custom fuel level display
    public void displayFuelLevel() {
        System.out.println("Fuel level: " + fuelLevel + "%");
    }
}

// Another traditional vehicle implementation
class Motorcycle implements VehicleDashboard {
    private int currentSpeed;
    private String model;
    
    public Motorcycle(int speed, String model) {
        this.currentSpeed = speed;
        this.model = model;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Motorcycle " + model + " speed: " + currentSpeed + " mph");
    }
    
    // Motorcycle can override default methods as needed
    @Override
    public void displayFuelEfficiency() {
        System.out.println("Motorcycle fuel efficiency: 45-60 MPG");
    }
}

// Electric vehicle implementation that benefits from the new default method
class ElectricCar implements VehicleDashboard {
    private int currentSpeed;
    private int batteryPercentage;
    private boolean ecoMode;
    
    public ElectricCar(int speed, int batteryPercentage, boolean ecoMode) {
        this.currentSpeed = speed;
        this.batteryPercentage = batteryPercentage;
        this.ecoMode = ecoMode;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Electric car speed: " + currentSpeed + " mph");
    }
    
    // Electric car provides its own implementation of battery percentage
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Battery level: " + batteryPercentage + "%");
        System.out.println("Estimated range: " + (batteryPercentage * 4) + " miles");
    }
    
    // Override eco mode display
    @Override
    public void displayEcoModeStatus() {
        System.out.println("Eco Mode: " + (ecoMode ? "ON" : "OFF"));
    }
    
    // Electric car specific method
    public void displayChargingStatus() {
        System.out.println("Charging status: " + (batteryPercentage < 20 ? "URGENT - Charge needed" : "Battery sufficient"));
    }
}

// Hybrid vehicle implementation
class HybridCar implements VehicleDashboard {
    private int currentSpeed;
    private int batteryPercentage;
    private int fuelLevel;
    
    public HybridCar(int speed, int batteryPercentage, int fuelLevel) {
        this.currentSpeed = speed;
        this.batteryPercentage = batteryPercentage;
        this.fuelLevel = fuelLevel;
    }
    
    @Override
    public void displaySpeed() {
        System.out.println("Hybrid car speed: " + currentSpeed + " mph");
    }
    
    // Hybrid car implementation of battery percentage
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Hybrid battery level: " + batteryPercentage + "%");
        System.out.println("Fuel level: " + fuelLevel + "%");
    }
    
    // Override fuel efficiency for hybrid
    @Override
    public void displayFuelEfficiency() {
        System.out.println("Hybrid fuel efficiency: 50-60 MPG equivalent");
    }
}

public class SmartVehicleDashboard {
    public static void main(String[] args) {
        System.out.println("Smart Vehicle Dashboard with Default Methods:");
        System.out.println("==============================================");
        
        // Create different types of vehicles
        VehicleDashboard[] vehicles = {
            new GasolineCar(65, 75),
            new Motorcycle(80, "Harley Davidson"),
            new ElectricCar(55, 85, true),
            new HybridCar(60, 40)
        };
        
        String[] vehicleNames = {"Gasoline Car", "Motorcycle", "Electric Car", "Hybrid Car"};
        
        // Display basic information for all vehicles
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println("\n" + vehicleNames[i] + " Dashboard:");
            System.out.println("-".repeat(30));
            
            // Display speed (original method)
            vehicles[i].displaySpeed();
            
            // Display battery percentage (new default method added later)
            vehicles[i].displayBatteryPercentage();
            
            // Display fuel efficiency (default method)
            vehicles[i].displayFuelEfficiency();
            
            // Display trip info (default method)
            vehicles[i].displayTripInfo();
            
            // Check maintenance (default method)
            vehicles[i].checkMaintenance();
            
            // Display eco mode status (default method)
            vehicles[i].displayEcoModeStatus();
        }
        
        // Demonstrate specific vehicle capabilities
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Vehicle-Specific Features:");
        System.out.println("==========================");
        
        // Gasoline car specific features
        GasolineCar gasCar = (GasolineCar) vehicles[0];
        gasCar.displayFuelLevel();
        
        // Motorcycle specific features
        Motorcycle bike = (Motorcycle) vehicles[1];
        bike.displayFuelEfficiency(); // Uses overridden method
        
        // Electric car specific features
        ElectricCar eCar = new ElectricCar(45, 30, false);
        System.out.println("\nElectric Car Specific Features:");
        eCar.displaySpeed();
        eCar.displayBatteryPercentage();
        eCar.displayChargingStatus();
        eCar.displayEcoModeStatus();
        
        // Hybrid car specific features
        HybridCar hCar = new HybridCar(50, 70, 65);
        System.out.println("\nHybrid Car Specific Features:");
        hCar.displaySpeed();
        hCar.displayBatteryPercentage();
        hCar.displayFuelEfficiency();
        
        System.out.println("\nDefault methods in interfaces allow adding new dashboard features");
        System.out.println("without breaking existing vehicle implementations, enabling");
        System.out.println("flexible and evolutionary vehicle dashboard systems.");
    }
}