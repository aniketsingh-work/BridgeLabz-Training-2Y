import java.util.*;
import java.util.stream.Collectors;

class Patient {
    private String patientId;
    private String name;
    private int age;
    private String condition;
    
    public Patient(String patientId, String name, int age, String condition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.condition = condition;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getCondition() {
        return condition;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d | Condition: %s", 
                           patientId, name, age, condition);
    }
}

public class HospitalPatientIDPrinting {
    public static void main(String[] args) {
        List<Patient> patients = Arrays.asList(
            new Patient("P001", "John Smith", 45, "Stable"),
            new Patient("P002", "Jane Doe", 32, "Critical"),
            new Patient("P003", "Robert Johnson", 67, "Recovering"),
            new Patient("P004", "Emily Davis", 28, "Stable"),
            new Patient("P005", "Michael Wilson", 55, "Critical"),
            new Patient("P006", "Sarah Brown", 41, "Observation"),
            new Patient("P007", "David Miller", 73, "Recovering")
        );
        
        System.out.println("All Patients:");
        patients.forEach(System.out::println);
        System.out.println();
        
        // Print all patient IDs using method reference instead of lambda
        System.out.println("Patient IDs (using method reference System.out::println):");
        List<String> patientIds = patients.stream()
                .map(Patient::getPatientId)
                .collect(Collectors.toList());
        
        // Using method reference instead of lambda (System.out::println)
        patientIds.forEach(System.out::println);
        System.out.println();
        
        // Another way - directly printing IDs using method reference
        System.out.println("Patient IDs (directly using method reference):");
        patients.stream()
                .map(Patient::getPatientId)
                .forEach(System.out::println);
        System.out.println();
        
        // Using method reference to print full patient details
        System.out.println("All patient details (using method reference):");
        patients.forEach(System.out::println);
        System.out.println();
        
        // Using method reference to filter and print specific patients
        System.out.println("Critical condition patients (using method reference):");
        patients.stream()
                .filter(patient -> patient.getCondition().equals("Critical"))
                .forEach(System.out::println);
        System.out.println();
        
        // Collect patient IDs using method reference
        System.out.println("Collected patient IDs:");
        List<String> allPatientIds = patients.stream()
                .map(Patient::getPatientId)
                .collect(Collectors.toList());
        System.out.println(allPatientIds);
        System.out.println();
        
        System.out.println("Method references (like System.out::println and Patient::getPatientId)");
        System.out.println("provide a more concise way to refer to methods without executing them,");
        System.out.println("making the code cleaner and more readable than equivalent lambdas.");
    }
}