public class FleetManagerDemo {
    public static void main(String[] args) {
        // Demonstrate using Truck type
        FleetManager<Truck> truckFleet = new FleetManager<>();
        truckFleet.addVehicle(new Truck("Ford", "F-150"));
        truckFleet.addVehicle(new Truck("Chevrolet", "Silverado"));
        System.out.println("Truck Fleet:");
        truckFleet.showFleet();
        
        System.out.println();
        
        // Demonstrate using Bike type
        FleetManager<Bike> bikeFleet = new FleetManager<>();
        bikeFleet.addVehicle(new Bike("Harley-Davidson", "Street 750"));
        bikeFleet.addVehicle(new Bike("Yamaha", "MT-07"));
        System.out.println("Bike Fleet:");
        bikeFleet.showFleet();
    }
}