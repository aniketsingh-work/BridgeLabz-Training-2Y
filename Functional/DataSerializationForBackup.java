import java.util.*;

// Marker interface for classes that should be included in backup
interface Backupable {
    // Marker interfaces contain no methods - they just mark a class
    // as having a certain property or being eligible for certain processing
}

// Regular class that is not backupable
class TemporaryData {
    private String id;
    private String data;
    
    public TemporaryData(String id, String data) {
        this.id = id;
        this.data = data;
    }
    
    public String getId() { return id; }
    public String getData() { return data; }
    
    @Override
    public String toString() {
        return "TemporaryData{id='" + id + "', data='" + data + "'}";
    }
}

// Class that implements the Backupable marker interface
class User implements Backupable {
    private String userId;
    private String name;
    private String email;
    private Date registrationDate;
    
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.registrationDate = new Date();
    }
    
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Date getRegistrationDate() { return registrationDate; }
    
    @Override
    public String toString() {
        return "User{userId='" + userId + "', name='" + name + "', email='" + email + "'}";
    }
}

// Another class that implements the Backupable marker interface
class Order implements Backupable {
    private String orderId;
    private String userId;
    private double amount;
    private Date orderDate;
    
    public Order(String orderId, String userId, double amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.orderDate = new Date();
    }
    
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public Date getOrderDate() { return orderDate; }
    
    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', userId='" + userId + "', amount=" + amount + "}";
    }
}

// Configuration data that should be backed up
class ConfigData implements Backupable {
    private String configKey;
    private String configValue;
    
    public ConfigData(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }
    
    public String getConfigKey() { return configKey; }
    public String getConfigValue() { return configValue; }
    
    @Override
    public String toString() {
        return "ConfigData{configKey='" + configKey + "', configValue='" + configValue + "'}";
    }
}

// Backup utility class
class BackupUtility {
    // Method to filter and backup only Backupable objects
    public static List<Object> performBackup(List<Object> objects) {
        List<Object> backupObjects = new ArrayList<>();
        
        for (Object obj : objects) {
            // Check if the object is Backupable using instanceof
            if (obj instanceof Backupable) {
                backupObjects.add(obj);
                System.out.println("Backing up: " + obj.getClass().getSimpleName() + " - " + obj);
            } else {
                System.out.println("Skipping (not backupable): " + obj.getClass().getSimpleName() + " - " + obj);
            }
        }
        
        return backupObjects;
    }
    
    // Method to restore backup (simulated)
    public static void restoreBackup(List<Object> backupObjects) {
        System.out.println("\nRestoring backup:");
        for (Object obj : backupObjects) {
            if (obj instanceof Backupable) {
                System.out.println("Restored: " + obj.getClass().getSimpleName() + " - " + obj);
            }
        }
    }
}

public class DataSerializationForBackup {
    public static void main(String[] args) {
        System.out.println("Data Serialization for Backup using Marker Interface:");
        System.out.println("===================================================");
        
        // Create a mixed list of objects - some backupable, some not
        List<Object> allData = Arrays.asList(
            new User("U001", "John Doe", "john@example.com"),
            new TemporaryData("TEMP001", "Temporary cache data"),
            new Order("ORD001", "U001", 299.99),
            new TemporaryData("TEMP002", "Session data"),
            new ConfigData("DB_HOST", "localhost"),
            new User("U002", "Jane Smith", "jane@example.com"),
            new Order("ORD002", "U002", 149.50),
            new ConfigData("DB_PORT", "5432")
        );
        
        System.out.println("All data objects:");
        allData.forEach(obj -> System.out.println("  " + obj.getClass().getSimpleName() + ": " + obj));
        System.out.println();
        
        // Perform backup - only Backupable objects will be included
        System.out.println("Starting backup process:");
        List<Object> backupData = BackupUtility.performBackup(allData);
        
        System.out.println("\nBackup completed. " + backupData.size() + " objects backed up.");
        
        // Display what was backed up vs what was skipped
        System.out.println("\nBackup Summary:");
        int backupableCount = 0, nonBackupableCount = 0;
        for (Object obj : allData) {
            if (obj instanceof Backupable) {
                backupableCount++;
            } else {
                nonBackupableCount++;
            }
        }
        System.out.println("Total objects: " + allData.size());
        System.out.println("Backupable objects: " + backupableCount);
        System.out.println("Non-backupable objects: " + nonBackupableCount);
        
        // Restore the backup
        BackupUtility.restoreBackup(backupData);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Advanced Backup Scenario:");
        System.out.println("=========================");
        
        // Simulate a more complex backup scenario
        List<Object> systemData = new ArrayList<>();
        systemData.add(new User("U003", "Bob Johnson", "bob@example.com"));
        systemData.add(new User("U004", "Alice Brown", "alice@example.com"));
        systemData.add(new Order("ORD003", "U003", 89.99));
        systemData.add(new TemporaryData("TEMP003", "Processing queue item"));
        systemData.add(new ConfigData("MAX_CONNECTIONS", "10"));
        
        System.out.println("\nSystem data backup:");
        List<Object> systemBackup = BackupUtility.performBackup(systemData);
        
        // Count backupable objects by type
        long userCount = systemBackup.stream()
                .filter(obj -> obj instanceof User)
                .count();
        long orderCount = systemBackup.stream()
                .filter(obj -> obj instanceof Order)
                .count();
        long configCount = systemBackup.stream()
                .filter(obj -> obj instanceof ConfigData)
                .count();
        
        System.out.println("\nBackup breakdown by type:");
        System.out.println("Users: " + userCount);
        System.out.println("Orders: " + orderCount);
        System.out.println("Configuration: " + configCount);
        
        System.out.println("\nMarker interfaces provide a way to mark classes as having");
        System.out.println("specific properties (like being backupable) without adding");
        System.out.println("any methods, allowing for type-based processing.");
    }
}