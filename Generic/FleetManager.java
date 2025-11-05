import java.util.ArrayList;
import java.util.List;

class FleetManager<T extends Vehicle> {
    private List<T> fleet;
    
    public FleetManager() {
        this.fleet = new ArrayList<>();
    }
    
    public void addVehicle(T vehicle) {
        fleet.add(vehicle);
    }
    
    public void showFleet() {
        if (fleet.isEmpty()) {
            System.out.println("Fleet is empty");
        } else {
            System.out.println("Vehicles in fleet:");
            for (T vehicle : fleet) {
                System.out.println("- " + vehicle);
                vehicle.start();
            }
        }
    }
    
    public List<T> getFleet() {
        return fleet;
    }
}