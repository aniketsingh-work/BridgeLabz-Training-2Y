import java.util.*;
import java.util.function.Consumer;

class Light {
    private String location;
    private boolean isOn;
    private String pattern;
    
    public Light(String location) {
        this.location = location;
        this.isOn = false;
        this.pattern = "normal";
    }
    
    public void turnOn(String pattern) {
        this.isOn = true;
        this.pattern = pattern;
        System.out.println("Light at " + location + " turned ON with " + pattern + " pattern");
    }
    
    public void turnOff() {
        this.isOn = false;
        System.out.println("Light at " + location + " turned OFF");
    }
    
    public String getLocation() {
        return location;
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    @Override
    public String toString() {
        return "Light at " + location + " (Pattern: " + pattern + ", Status: " + (isOn ? "ON" : "OFF") + ")";
    }
}

public class SmartHomeLighting {
    public static void main(String[] args) {
        List<Light> lights = Arrays.asList(
            new Light("Living Room"),
            new Light("Kitchen"),
            new Light("Bedroom"),
            new Light("Bathroom")
        );
        
        // Define different light activation behaviors using lambdas
        Consumer<List<Light>> motionTrigger = (lightList) -> {
            System.out.println("Motion detected! Activating lights with 'Welcome' pattern:");
            lightList.forEach(light -> light.turnOn("Welcome"));
            System.out.println();
        };
        
        Consumer<List<Light>> eveningTrigger = (lightList) -> {
            System.out.println("Evening time! Activating lights with 'Relax' pattern:");
            lightList.forEach(light -> light.turnOn("Relax"));
            System.out.println();
        };
        
        Consumer<List<Light>> nightTrigger = (lightList) -> {
            System.out.println("Night time! Activating lights with 'Nightlight' pattern:");
            lightList.stream()
                    .filter(light -> light.getLocation().equals("Bedroom") || light.getLocation().equals("Bathroom"))
                    .forEach(light -> light.turnOn("Nightlight"));
            System.out.println();
        };
        
        Consumer<List<Light>> voiceCommand = (lightList) -> {
            System.out.println("Voice command: 'Party Mode'! Activating lights with 'Party' pattern:");
            lightList.forEach(light -> light.turnOn("Party"));
            System.out.println();
        };
        
        // Execute different triggers
        motionTrigger.accept(lights);
        
        // Turn off all lights before next trigger
        lights.forEach(Light::turnOff);
        System.out.println();
        
        eveningTrigger.accept(lights);
        
        lights.forEach(Light::turnOff);
        System.out.println();
        
        nightTrigger.accept(lights);
        
        lights.forEach(Light::turnOff);
        System.out.println();
        
        voiceCommand.accept(lights);
        
        System.out.println("\nLambda expressions allow us to define these light activation behaviors dynamically");
        System.out.println("without creating separate classes for each trigger type.");
    }
}