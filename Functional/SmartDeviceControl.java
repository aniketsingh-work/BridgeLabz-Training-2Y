// Interface defining the common methods for all smart devices
interface SmartDevice {
    void turnOn();
    void turnOff();
}

// Implementation of the interface for a smart light
class SmartLight implements SmartDevice {
    private String location;
    private boolean isOn;
    
    public SmartLight(String location) {
        this.location = location;
        this.isOn = false;
    }
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Smart light at " + location + " is turned ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Smart light at " + location + " is turned OFF");
    }
    
    public boolean getStatus() {
        return isOn;
    }
}

// Implementation of the interface for a smart AC
class SmartAC implements SmartDevice {
    private String room;
    private boolean isOn;
    private int temperature;
    
    public SmartAC(String room) {
        this.room = room;
        this.isOn = false;
        this.temperature = 25;
    }
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Smart AC in " + room + " is turned ON at " + temperature + "°C");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Smart AC in " + room + " is turned OFF");
    }
    
    public void setTemperature(int temp) {
        this.temperature = temp;
        if (isOn) {
            System.out.println("AC temperature adjusted to " + temperature + "°C");
        }
    }
}

// Implementation of the interface for a smart TV
class SmartTV implements SmartDevice {
    private String room;
    private boolean isOn;
    private int volume;
    
    public SmartTV(String room) {
        this.room = room;
        this.isOn = false;
        this.volume = 20;
    }
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Smart TV in " + room + " is turned ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Smart TV in " + room + " is turned OFF");
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
        if (isOn) {
            System.out.println("TV volume set to " + volume);
        }
    }
}

public class SmartDeviceControl {
    public static void main(String[] args) {
        // Create instances of different smart devices
        SmartDevice light = new SmartLight("Living Room");
        SmartDevice ac = new SmartAC("Bedroom");
        SmartDevice tv = new SmartTV("Living Room");
        
        // Control devices using the common interface
        System.out.println("Controlling smart devices:");
        System.out.println("=========================");
        
        light.turnOn();
        ac.turnOn();
        tv.turnOn();
        
        System.out.println();
        
        // Using specific methods
        if (ac instanceof SmartAC) {
            ((SmartAC) ac).setTemperature(2);
        }
        
        if (tv instanceof SmartTV) {
            ((SmartTV) tv).setVolume(30);
        }
        
        System.out.println();
        
        light.turnOff();
        ac.turnOff();
        tv.turnOff();
        
        System.out.println("\nUsing interface polymorphism:");
        System.out.println("==============================");
        
        // Array of devices using the interface
        SmartDevice[] devices = {new SmartLight("Kitchen"), new SmartAC("Study"), new SmartTV("Master Bedroom")};
        
        // Turn all devices on
        for (SmartDevice device : devices) {
            device.turnOn();
        }
        
        System.out.println();
        
        // Turn all devices off
        for (SmartDevice device : devices) {
            device.turnOff();
        }
        
        System.out.println("\nInterface ensures all smart devices implement required methods");
        System.out.println("while allowing each device to have its specific behavior.");
    }
}