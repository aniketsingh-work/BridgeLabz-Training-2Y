import java.util.*;

// Marker interface for sensitive data that requires special handling
interface SensitiveData {
    // Marker interface - no methods, just indicates sensitive data
}

// Non-sensitive data class
class PublicInfo {
    private String publicId;
    private String description;
    
    public PublicInfo(String publicId, String description) {
        this.publicId = publicId;
        this.description = description;
    }
    
    public String getPublicId() { return publicId; }
    public String getDescription() { return description; }
    
    @Override
    public String toString() {
        return "PublicInfo{publicId='" + publicId + "', description='" + description + "'}";
    }
}

// Sensitive personal information
class PersonalInfo implements SensitiveData {
    private String ssn;
    private String dob;
    private String address;
    private String phoneNumber;
    
    public PersonalInfo(String ssn, String dob, String address, String phoneNumber) {
        this.ssn = ssn;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public String getSsn() { return ssn; }
    public String getDob() { return dob; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    
    @Override
    public String toString() {
        return "PersonalInfo{ssn='" + mask(ssn) + "', dob='" + dob + "', address='" + mask(address) + "', phone='" + mask(phoneNumber) + "'}";
    }
    
    // Helper method to mask sensitive data for display
    private String mask(String data) {
        if (data == null || data.length() == 0) return data;
        if (data.length() <= 4) return "*".repeat(Math.max(0, data.length() - 1)) + data.charAt(data.length() - 1);
        return data.substring(0, Math.max(0, data.length() - 4)).replaceAll(".", "*") + data.substring(Math.max(0, data.length() - 4));
    }
}

// Sensitive financial information
class FinancialInfo implements SensitiveData {
    private String accountNumber;
    private String routingNumber;
    private String creditCardNumber;
    private double balance;
    
    public FinancialInfo(String accountNumber, String routingNumber, String creditCardNumber, double balance) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.creditCardNumber = creditCardNumber;
        this.balance = balance;
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getRoutingNumber() { return routingNumber; }
    public String getCreditCardNumber() { return creditCardNumber; }
    public double getBalance() { return balance; }
    
    @Override
    public String toString() {
        return "FinancialInfo{account='" + mask(accountNumber) + "', routing='" + mask(routingNumber) + 
               "', creditCard='" + mask(creditCardNumber) + "', balance=" + balance + "}";
    }
    
    // Helper method to mask sensitive data for display
    private String mask(String data) {
        if (data == null || data.length() == 0) return data;
        if (data.length() <= 4) return "*".repeat(Math.max(0, data.length() - 1)) + data.charAt(data.length() - 1);
        return data.substring(0, Math.max(0, data.length() - 4)).replaceAll(".", "*") + data.substring(Math.max(0, data.length() - 4));
    }
}

// Sensitive health information
class HealthInfo implements SensitiveData {
    private String medicalRecordNumber;
    private String diagnosis;
    private String medications;
    private String allergies;
    
    public HealthInfo(String medicalRecordNumber, String diagnosis, String medications, String allergies) {
        this.medicalRecordNumber = medicalRecordNumber;
        this.diagnosis = diagnosis;
        this.medications = medications;
        this.allergies = allergies;
    }
    
    public String getMedicalRecordNumber() { return medicalRecordNumber; }
    public String getDiagnosis() { return diagnosis; }
    public String getMedications() { return medications; }
    public String getAllergies() { return allergies; }
    
    @Override
    public String toString() {
        return "HealthInfo{recordNum='" + mask(medicalRecordNumber) + "', diagnosis='" + diagnosis + 
               "', medications='" + medications + "', allergies='" + allergies + "'}";
    }
    
    // Helper method to mask sensitive data for display
    private String mask(String data) {
        if (data == null || data.length() == 0) return data;
        if (data.length() <= 4) return "*".repeat(Math.max(0, data.length() - 1)) + data.charAt(data.length() - 1);
        return data.substring(0, Math.max(0, data.length() - 4)).replaceAll(".", "*") + data.substring(Math.max(0, data.length() - 4));
    }
}

// User profile that contains both sensitive and non-sensitive data
class UserProfile {
    private String userId;
    private String username;
    private PublicInfo publicInfo;
    private PersonalInfo personalInfo;
    private FinancialInfo financialInfo;
    private HealthInfo healthInfo;
    
    public UserProfile(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }
    
    public void setPublicInfo(PublicInfo publicInfo) { this.publicInfo = publicInfo; }
    public void setPersonalInfo(PersonalInfo personalInfo) { this.personalInfo = personalInfo; }
    public void setFinancialInfo(FinancialInfo financialInfo) { this.financialInfo = financialInfo; }
    public void setHealthInfo(HealthInfo healthInfo) { this.healthInfo = healthInfo; }
    
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public PublicInfo getPublicInfo() { return publicInfo; }
    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public FinancialInfo getFinancialInfo() { return financialInfo; }
    public HealthInfo getHealthInfo() { return healthInfo; }
    
    @Override
    public String toString() {
        return "UserProfile{userId='" + userId + "', username='" + username + "', publicInfo=" + publicInfo + 
               ", personalInfo=" + personalInfo + ", financialInfo=" + financialInfo + ", healthInfo=" + healthInfo + "}";
    }
}

// Data processing utility that handles sensitive data appropriately
class DataProcessor {
    // Method to log data, but handle sensitive data specially
    public static void logData(Object data) {
        if (data instanceof SensitiveData) {
            System.out.println("[SENSITIVE] Logging sensitive data: " + data.getClass().getSimpleName());
            System.out.println("  Content: [CONTENT REDACTED FOR SECURITY]");
        } else {
            System.out.println("[PUBLIC] Logging public data: " + data);
        }
    }
    
    // Method to validate if data is sensitive
    public static boolean isSensitive(Object data) {
        return data instanceof SensitiveData;
    }
    
    // Method to process data with appropriate security measures
    public static void processData(List<Object> data) {
        System.out.println("Processing data with appropriate security measures:");
        
        for (Object item : data) {
            if (item instanceof SensitiveData) {
                System.out.println("  [SECURE] Processing sensitive data: " + item.getClass().getSimpleName());
                // Apply encryption, access controls, etc.
                applySecurityMeasures(item);
            } else {
                System.out.println(" [NORMAL] Processing public data: " + item.getClass().getSimpleName());
                // Normal processing
            }
        }
    }
    
    // Simulate applying security measures to sensitive data
    private static void applySecurityMeasures(Object sensitiveData) {
        System.out.println("    - Applying encryption");
        System.out.println("    - Logging access for audit trail");
        System.out.println("    - Applying access controls");
        System.out.println("    - Storing in secure location");
    }
    
    // Method to filter sensitive data
    public static List<Object> getSensitiveData(List<Object> allData) {
        List<Object> sensitiveData = new ArrayList<>();
        for (Object item : allData) {
            if (item instanceof SensitiveData) {
                sensitiveData.add(item);
            }
        }
        return sensitiveData;
    }
    
    // Method to filter non-sensitive data
    public static List<Object> getPublicData(List<Object> allData) {
        List<Object> publicData = new ArrayList<>();
        for (Object item : allData) {
            if (!(item instanceof SensitiveData)) {
                publicData.add(item);
            }
        }
        return publicData;
    }
}

public class SensitiveDataTagging {
    public static void main(String[] args) {
        System.out.println("Sensitive Data Tagging using Marker Interface:");
        System.out.println("===============================================");
        
        // Create various data objects
        List<Object> allData = Arrays.asList(
            new PublicInfo("PUB001", "Company public information"),
            new PersonalInfo("123-45-6789", "1990-01-01", "123 Main St, City, State", "555-123-4567"),
            new PublicInfo("PUB002", "General product description"),
            new FinancialInfo("1234567890", "987654321", "1234567890123456", 5000.00),
            new HealthInfo("MR-001-2023", "Hypertension", "Lisinopril", "Penicillin"),
            new PublicInfo("PUB003", "Open source project details")
        );
        
        System.out.println("All data objects:");
        allData.forEach(data -> System.out.println("  " + data));
        System.out.println();
        
        // Log each data item with appropriate handling
        System.out.println("Logging data with security awareness:");
        for (Object data : allData) {
            DataProcessor.logData(data);
        }
        System.out.println();
        
        // Process data with security measures
        DataProcessor.processData(allData);
        System.out.println();
        
        // Separate sensitive and public data
        List<Object> sensitiveData = DataProcessor.getSensitiveData(allData);
        List<Object> publicData = DataProcessor.getPublicData(allData);
        
        System.out.println("Sensitive data found: " + sensitiveData.size() + " items");
        sensitiveData.forEach(data -> System.out.println("  " + data.getClass().getSimpleName()));
        
        System.out.println("\nPublic data found: " + publicData.size() + " items");
        publicData.forEach(data -> System.out.println("  " + data.getClass().getSimpleName()));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("User Profile Example:");
        System.out.println("=====================");
        
        // Create a user profile with mixed sensitive and public data
        UserProfile user = new UserProfile("U-12345", "john_doe");
        user.setPublicInfo(new PublicInfo("PUB-PROFILE", "User's public profile information"));
        user.setPersonalInfo(new PersonalInfo("987-65-4321", "1985-05-15", "456 Oak Ave, Town, Country", "555-987-6543"));
        user.setFinancialInfo(new FinancialInfo("0987654321", "123456789", "9876543210987654", 12500.75));
        user.setHealthInfo(new HealthInfo("MR-54321-2023", "Diabetes", "Metformin", "None"));
        
        System.out.println("Full user profile:");
        System.out.println(user);
        
        System.out.println("\nProcessing user profile data:");
        List<Object> userProfileData = Arrays.asList(
            user.getPublicInfo(), user.getPersonalInfo(), user.getFinancialInfo(), user.getHealthInfo()
        );
        
        DataProcessor.processData(userProfileData);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Security Compliance Check:");
        System.out.println("==========================");
        
        // Simulate a compliance check
        System.out.println("Performing data classification for compliance:");
        int sensitiveCount = 0;
        int publicCount = 0;
        
        for (Object data : allData) {
            if (DataProcessor.isSensitive(data)) {
                sensitiveCount++;
                System.out.println("  [SENSITIVE] " + data.getClass().getSimpleName() + " - Requires special handling");
            } else {
                publicCount++;
                System.out.println("  [PUBLIC] " + data.getClass().getSimpleName() + " - Standard handling");
            }
        }
        
        System.out.println("\nClassification Summary:");
        System.out.println("  Sensitive data items: " + sensitiveCount);
        System.out.println("  Public data items: " + publicCount);
        System.out.println("  Total items: " + allData.size());
        
        System.out.println("\nMarker interfaces provide a clean way to tag sensitive data");
        System.out.println("classes, enabling security systems to identify and handle");
        System.out.println("them appropriately without modifying class hierarchies.");
    }
}