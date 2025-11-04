// Interface defining the common methods for all rental vehicles
interface RentalVehicle {
    void rent();
    void returnVehicle();
}

// Implementation for Car rental
class Car implements RentalVehicle {
    private String model;
    private String licensePlate;
    private boolean isRented;
    private double rentalRate; // per day
    
    public Car(String model, String licensePlate, double rentalRate) {
        this.model = model;
        this.licensePlate = licensePlate;
        this.rentalRate = rentalRate;
        this.isRented = false;
    }
    
    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Car " + model + " with license plate " + licensePlate + " has been rented for $" + rentalRate + " per day");
        } else {
            System.out.println("Car " + model + " is already rented");
        }
    }
    
    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Car " + model + " with license plate " + licensePlate + " has been returned");
        } else {
            System.out.println("Car " + model + " was not rented");
        }
    }
    
    public String getModel() {
        return model;
    }
    
    public boolean isRented() {
        return isRented;
    }
}

// Implementation for Bike rental
class Bike implements RentalVehicle {
    private String brand;
    private String bikeId;
    private boolean isRented;
    private double rentalRate; // per day
    
    public Bike(String brand, String bikeId, double rentalRate) {
        this.brand = brand;
        this.bikeId = bikeId;
        this.rentalRate = rentalRate;
        this.isRented = false;
    }
    
    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Bike " + brand + " with ID " + bikeId + " has been rented for $" + rentalRate + " per day");
        } else {
            System.out.println("Bike " + brand + " is already rented");
        }
    }
    
    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bike " + brand + " with ID " + bikeId + " has been returned");
        } else {
            System.out.println("Bike " + brand + " was not rented");
        }
    }
    
    public String getBrand() {
        return brand;
    }
    
    public boolean isRented() {
        return isRented;
    }
}

// Implementation for Bus rental
class Bus implements RentalVehicle {
    private String company;
    private String busNumber;
    private boolean isRented;
    private int capacity;
    private double rentalRate; // per day
    
    public Bus(String company, String busNumber, int capacity, double rentalRate) {
        this.company = company;
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.rentalRate = rentalRate;
        this.isRented = false;
    }
    
    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Bus from " + company + " with number " + busNumber + " (capacity: " + capacity + ") has been rented for $" + rentalRate + " per day");
        } else {
            System.out.println("Bus from " + company + " is already rented");
        }
    }
    
    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bus from " + company + " with number " + busNumber + " has been returned");
        } else {
            System.out.println("Bus from " + company + " was not rented");
        }
    }
    
    public String getCompany() {
        return company;
    }
    
    public boolean isRented() {
        return isRented;
    }
}

public class MultiVehicleRental {
    public static void main(String[] args) {
        // Create instances of different rental vehicles
        RentalVehicle car = new Car("Toyota Camry", "ABC123", 45.0);
        RentalVehicle bike = new Bike("Yamaha", "BIKE001", 25.0);
        RentalVehicle bus = new Bus("City Express", "BUS456", 40, 150.0);
        
        System.out.println("Multi-Vehicle Rental System:");
        System.out.println("============================");
        
        // Rent all vehicles
        car.rent();
        bike.rent();
        bus.rent();
        
        System.out.println();
        
        // Try to rent already rented vehicles
        car.rent();  // Should show it's already rented
        
        System.out.println();
        
        // Return all vehicles
        car.returnVehicle();
        bike.returnVehicle();
        bus.returnVehicle();
        
        System.out.println();
        
        // Try to return already returned vehicles
        car.returnVehicle();  // Should show it was not rented
        
        System.out.println();
        
        System.out.println("Using interface polymorphism:");
        System.out.println("==============================");
        
        // Array of vehicles using the interface
        RentalVehicle[] vehicles = {
            new Car("Honda Civic", "XYZ789", 40.0),
            new Bike("Harley Davidson", "BIKE002", 80.0),
            new Bus("Tour Express", "BUS789", 30, 120.0)
        };
        
        // Rent all vehicles
        for (RentalVehicle vehicle : vehicles) {
            vehicle.rent();
        }
        
        System.out.println();
        
        // Return all vehicles
        for (RentalVehicle vehicle : vehicles) {
            vehicle.returnVehicle();
        }
        
        System.out.println("\nInterface ensures all rental vehicles implement required methods");
        System.out.println("while allowing each vehicle type to have its specific behavior.");
    }
}