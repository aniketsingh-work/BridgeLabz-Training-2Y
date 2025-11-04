import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Alert {
    private String patientName;
    private String type; // "critical", "normal", "test"
    private String priority; // "high", "medium", "low"
    private String message;
    
    public Alert(String patientName, String type, String priority, String message) {
        this.patientName = patientName;
        this.type = type;
        this.priority = priority;
        this.message = message;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public String getType() {
        return type;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return String.format("Patient: %s | Type: %s | Priority: %s | Message: %s", 
                           patientName, type, priority, message);
    }
}

public class NotificationFiltering {
    public static void main(String[] args) {
        List<Alert> alerts = Arrays.asList(
            new Alert("John Smith", "critical", "high", "Heart rate irregularity detected"),
            new Alert("Jane Doe", "normal", "medium", "Scheduled medication reminder"),
            new Alert("Robert Johnson", "critical", "high", "Blood pressure dangerously high"),
            new Alert("Emily Davis", "normal", "low", "Appointment reminder"),
            new Alert("Michael Wilson", "test", "low", "Lab results ready for review"),
            new Alert("Sarah Brown", "critical", "high", "Oxygen saturation below normal"),
            new Alert("David Miller", "normal", "medium", "Follow-up appointment needed")
        );
        
        System.out.println("All Alerts:");
        alerts.forEach(System.out::println);
        System.out.println();
        
        // Filter alerts using different predicates with lambdas
        Predicate<Alert> criticalAlerts = alert -> alert.getType().equals("critical");
        Predicate<Alert> highPriorityAlerts = alert -> alert.getPriority().equals("high");
        Predicate<Alert> nonTestAlerts = alert -> !alert.getType().equals("test");
        
        System.out.println("Critical Alerts:");
        List<Alert> critical = alerts.stream()
                .filter(criticalAlerts)
                .collect(Collectors.toList());
        critical.forEach(System.out::println);
        System.out.println();
        
        System.out.println("High Priority Alerts:");
        List<Alert> highPriority = alerts.stream()
                .filter(highPriorityAlerts)
                .collect(Collectors.toList());
        highPriority.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Non-Test Alerts:");
        List<Alert> nonTest = alerts.stream()
                .filter(nonTestAlerts)
                .collect(Collectors.toList());
        nonTest.forEach(System.out::println);
        System.out.println();
        
        // Combined filtering based on user preferences
        System.out.println("Critical and High Priority Alerts (Combined Filter):");
        List<Alert> criticalHighPriority = alerts.stream()
                .filter(criticalAlerts.and(highPriorityAlerts))
                .collect(Collectors.toList());
        criticalHighPriority.forEach(System.out::println);
        System.out.println();
        
        // Dynamic filtering based on user preferences using lambda
        System.out.println("Alerts for 'Emergency Only' user preference (critical alerts):");
        List<Alert> emergencyOnly = filterAlerts(alerts, criticalAlerts);
        emergencyOnly.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Alerts for 'High Priority' user preference:");
        List<Alert> highPriorityOnly = filterAlerts(alerts, highPriorityAlerts);
        highPriorityOnly.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Alerts for 'All Except Tests' user preference:");
        List<Alert> allExceptTests = filterAlerts(alerts, nonTestAlerts);
        allExceptTests.forEach(System.out::println);
        System.out.println();
        
        // Complex filtering: Critical OR High Priority
        System.out.println("Critical OR High Priority Alerts:");
        List<Alert> criticalOrHigh = alerts.stream()
                .filter(criticalAlerts.or(highPriorityAlerts))
                .collect(Collectors.toList());
        criticalOrHigh.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Lambdas with Predicate allow flexible filtering of alerts based on");
        System.out.println("user preferences without creating multiple filter methods.");
    }
    
    // Method that accepts a predicate as a lambda
    public static List<Alert> filterAlerts(List<Alert> alerts, Predicate<Alert> predicate) {
        return alerts.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}