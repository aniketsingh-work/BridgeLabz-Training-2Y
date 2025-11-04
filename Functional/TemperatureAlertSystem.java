import java.util.*;
import java.util.function.Predicate;

class TemperatureSensor {
    private String location;
    private double currentTemperature;
    
    public TemperatureSensor(String location, double temperature) {
        this.location = location;
        this.currentTemperature = temperature;
    }
    
    public String getLocation() {
        return location;
    }
    
    public double getCurrentTemperature() {
        return currentTemperature;
    }
    
    public void setCurrentTemperature(double temperature) {
        this.currentTemperature = temperature;
    }
    
    @Override
    public String toString() {
        return String.format("TemperatureSensor{location='%s', currentTemperature=%.2f°C}", 
                           location, currentTemperature);
    }
}

public class TemperatureAlertSystem {
    public static void main(String[] args) {
        // Create a list of temperature sensors
        List<TemperatureSensor> sensors = Arrays.asList(
            new TemperatureSensor("Server Room", 25.5),
            new TemperatureSensor("Warehouse", 18.0),
            new TemperatureSensor("Data Center", 30.2),
            new TemperatureSensor("Laboratory", 22.7),
            new TemperatureSensor("Cold Storage", 4.5),
            new TemperatureSensor("Boiler Room", 85.3)
        );
        
        System.out.println("Temperature Monitoring System:");
        System.out.println("===============================");
        sensors.forEach(System.out::println);
        System.out.println();
        
        // Using Predicate functional interface to check if temperature crosses threshold
        Predicate<Double> isHighTemperature = temp -> temp > 30.0;
        Predicate<Double> isLowTemperature = temp -> temp < 10.0;
        Predicate<Double> isCriticalTemperature = temp -> temp > 80.0 || temp < 0.0;
        
        // Check for high temperature alerts
        System.out.println("High Temperature Alerts (>30°C):");
        sensors.stream()
                .filter(sensor -> isHighTemperature.test(sensor.getCurrentTemperature()))
                .forEach(sensor -> System.out.println("ALERT: High temperature detected at " + 
                                                     sensor.getLocation() + ": " + 
                                                     sensor.getCurrentTemperature() + "°C"));
        System.out.println();
        
        // Check for low temperature alerts
        System.out.println("Low Temperature Alerts (<10°C):");
        sensors.stream()
                .filter(sensor -> isLowTemperature.test(sensor.getCurrentTemperature()))
                .forEach(sensor -> System.out.println("ALERT: Low temperature detected at " + 
                                                     sensor.getLocation() + ": " + 
                                                     sensor.getCurrentTemperature() + "°C"));
        System.out.println();
        
        // Check for critical temperature alerts
        System.out.println("Critical Temperature Alerts (>80°C or <0°C):");
        sensors.stream()
                .filter(sensor -> isCriticalTemperature.test(sensor.getCurrentTemperature()))
                .forEach(sensor -> System.out.println("CRITICAL ALERT: Critical temperature detected at " + 
                                                     sensor.getLocation() + ": " + 
                                                     sensor.getCurrentTemperature() + "°C"));
        System.out.println();
        
        // Dynamic threshold checking using Predicate
        System.out.println("Custom threshold alerts (using dynamic predicates):");
        checkTemperatureWithThreshold(sensors, 20.0, "Cold Warning", temp -> temp < 20.0);
        checkTemperatureWithThreshold(sensors, 28.0, "Hot Warning", temp -> temp > 28.0);
        System.out.println();
        
        // Multiple conditions using Predicate composition
        System.out.println("Temperature in normal range (10-30°C):");
        Predicate<Double> isNormalTemperature = isLowTemperature.negate().and(isHighTemperature.negate());
        sensors.stream()
                .filter(sensor -> isNormalTemperature.test(sensor.getCurrentTemperature()))
                .forEach(sensor -> System.out.println("Normal: " + sensor.getLocation() + 
                                                     " - " + sensor.getCurrentTemperature() + "°C"));
        System.out.println();
        
        // Simulate temperature changes and alerts
        System.out.println("Simulating temperature changes:");
        sensors.get(0).setCurrentTemperature(35.0); // Server room overheating
        sensors.get(2).setCurrentTemperature(5.0);  // Data center cooling down
        
        System.out.println("After temperature changes:");
        checkTemperatureWithThreshold(sensors, 30.0, "Overheating Alert", temp -> temp > 30.0);
        
        System.out.println("\nUsing Predicate functional interface allows for flexible and reusable");
        System.out.println("temperature checking logic with lambda expressions.");
    }
    
    // Method that accepts a predicate to check temperature with custom threshold
    public static void checkTemperatureWithThreshold(List<TemperatureSensor> sensors, 
                                                    double threshold, 
                                                    String alertType,
                                                    Predicate<Double> condition) {
        sensors.stream()
                .filter(sensor -> condition.test(sensor.getCurrentTemperature()))
                .forEach(sensor -> System.out.println(alertType + " at " + 
                                                     sensor.getLocation() + 
                                                     ": " + sensor.getCurrentTemperature() + "°C (threshold: " + 
                                                     threshold + "°C)"));
    }
}