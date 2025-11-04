interface UnitConverter {
    // Static method for distance conversion: km to miles
    static double kmToMiles(double kilometers) {
        return kilometers * 0.621371;
    }
    
    // Static method for distance conversion: miles to km
    static double milesToKm(double miles) {
        return miles * 1.60934;
    }
    
    // Static method for weight conversion: kg to lbs
    static double kgToLbs(double kilograms) {
        return kilograms * 2.20462;
    }
    
    // Static method for weight conversion: lbs to kg
    static double lbsToKg(double pounds) {
        return pounds * 0.453592;
    }
    
    // Static method for temperature conversion: Celsius to Fahrenheit
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0/5.0) + 32.0;
    }
    
    // Static method for temperature conversion: Fahrenheit to Celsius
    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0/9.0;
    }
    
    // Static method for volume conversion: liters to gallons
    static double litersToGallons(double liters) {
        return liters * 0.264172;
    }
    
    // Static method for volume conversion: gallons to liters
    static double gallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }
    
    // Static method to format the conversion result
    static String formatResult(double value, String unit) {
        return String.format("%.2f %s", value, unit);
    }
}

class Shipment {
    private String id;
    private double weightKg; // in kilograms
    private double distanceKm; // in kilometers
    
    public Shipment(String id, double weightKg, double distanceKm) {
        this.id = id;
        this.weightKg = weightKg;
        this.distanceKm = distanceKm;
    }
    
    public String getId() {
        return id;
    }
    
    public double getWeightKg() {
        return weightKg;
    }
    
    public double getDistanceKm() {
        return distanceKm;
    }
    
    @Override
    public String toString() {
        return String.format("Shipment{id='%s', weight=%.2f kg, distance=%.2f km}", 
                           id, weightKg, distanceKm);
    }
}

public class UnitConversionTool {
    public static void main(String[] args) {
        System.out.println("Unit Conversion Tool for Logistics:");
        System.out.println("==================================");
        
        // Test individual conversions
        System.out.println("Individual Conversions:");
        System.out.println("100 km to miles: " + UnitConverter.formatResult(UnitConverter.kmToMiles(100), "miles"));
        System.out.println("50 miles to km: " + UnitConverter.formatResult(UnitConverter.milesToKm(50), "km"));
        System.out.println("25 kg to lbs: " + UnitConverter.formatResult(UnitConverter.kgToLbs(25), "lbs"));
        System.out.println("100 lbs to kg: " + UnitConverter.formatResult(UnitConverter.lbsToKg(100), "kg"));
        System.out.println("25°C to °F: " + UnitConverter.formatResult(UnitConverter.celsiusToFahrenheit(25), "°F"));
        System.out.println("77°F to °C: " + UnitConverter.formatResult(UnitConverter.fahrenheitToCelsius(77), "°C"));
        System.out.println("10 liters to gallons: " + UnitConverter.formatResult(UnitConverter.litersToGallons(10), "gallons"));
        System.out.println("5 gallons to liters: " + UnitConverter.formatResult(UnitConverter.gallonsToLiters(5), "liters"));
        System.out.println();
        
        // Logistics scenario: Processing shipments
        System.out.println("Logistics Shipment Processing:");
        System.out.println("==============================");
        
        Shipment[] shipments = {
            new Shipment("SHP001", 500.5, 1200.0),  // 500.5 kg, 1200 km
            new Shipment("SHP02", 250.0, 800.5),   // 250 kg, 800.5 km
            new Shipment("SHP003", 75.25, 350.75)   // 75.25 kg, 350.75 km
        };
        
        for (Shipment shipment : shipments) {
            System.out.println("Processing " + shipment);
            
            // Convert weight to pounds for US clients
            double weightLbs = UnitConverter.kgToLbs(shipment.getWeightKg());
            System.out.println("  Weight in lbs: " + UnitConverter.formatResult(weightLbs, "lbs"));
            
            // Convert distance to miles for US clients
            double distanceMiles = UnitConverter.kmToMiles(shipment.getDistanceKm());
            System.out.println("  Distance in miles: " + UnitConverter.formatResult(distanceMiles, "miles"));
            
            // Calculate weight in tons for large shipments
            double weightTons = shipment.getWeightKg() / 1000.0;
            System.out.println(" Weight in tons: " + UnitConverter.formatResult(weightTons, "tons"));
            
            System.out.println();
        }
        
        // International shipping scenario
        System.out.println("International Shipping Data Conversion:");
        System.out.println("=====================================");
        
        // Data from US partner (in lbs and miles)
        double usWeightLbs = 110.23; // approximately 50 kg
        double usDistanceMiles = 621.37; // approximately 1000 km
        
        System.out.println("US Partner Data:");
        System.out.println(" Weight: " + UnitConverter.formatResult(usWeightLbs, "lbs"));
        System.out.println(" Distance: " + UnitConverter.formatResult(usDistanceMiles, "miles"));
        
        // Convert to metric for our system
        double convertedWeightKg = UnitConverter.lbsToKg(usWeightLbs);
        double convertedDistanceKm = UnitConverter.milesToKm(usDistanceMiles);
        
        System.out.println("Converted for our system:");
        System.out.println("  Weight: " + UnitConverter.formatResult(convertedWeightKg, "kg"));
        System.out.println("  Distance: " + UnitConverter.formatResult(convertedDistanceKm, "km"));
        
        // Calculate additional metrics using converted values
        double avgSpeedKmh = convertedDistanceKm / 10; // 10 hours travel time
        System.out.println("  Average speed: " + UnitConverter.formatResult(avgSpeedKmh, "km/h"));
        
        System.out.println("\nStatic methods in interfaces provide centralized unit conversion utilities");
        System.out.println("that can be used without implementing the interface, ideal for logistics systems.");
    }
}