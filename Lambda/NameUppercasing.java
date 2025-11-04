import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String firstName;
    private String lastName;
    
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return getFullName();
    }
}

public class NameUppercasing {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("john", "smith"),
            new Employee("jane", "doe"),
            new Employee("robert", "johnson"),
            new Employee("emily", "davis"),
            new Employee("michael", "wilson"),
            new Employee("sarah", "brown")
        );
        
        System.out.println("Original Employee Names:");
        employees.forEach(System.out::println);
        System.out.println();
        
        // Convert all employee names to uppercase using String::toUpperCase method reference
        System.out.println("Employee Names in Uppercase (using String::toUpperCase):");
        List<String> upperCaseNames = employees.stream()
                .map(Employee::getFullName)  // Extract full names
                .map(String::toUpperCase)    // Convert to uppercase using method reference
                .collect(Collectors.toList());
        
        upperCaseNames.forEach(System.out::println);
        System.out.println();
        
        // Alternative: Directly print uppercase names using method reference
        System.out.println("Employee Names in Uppercase (direct output):");
        employees.stream()
                .map(Employee::getFullName)
                .map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println();
        
        // Using method reference in a different context - creating uppercase first names
        System.out.println("First Names in Uppercase:");
        List<String> upperCaseFirstNames = employees.stream()
                .map(Employee::getFirstName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperCaseFirstNames.forEach(System.out::println);
        System.out.println();
        
        // Last names in uppercase
        System.out.println("Last Names in Uppercase:");
        List<String> upperCaseLastNames = employees.stream()
                .map(Employee::getLastName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperCaseLastNames.forEach(System.out::println);
        System.out.println();
        
        // Create formal names for HR letter using method reference
        System.out.println("Formal Names for HR Letter:");
        List<String> formalNames = employees.stream()
                .map(emp -> "Mr./Ms. " + emp.getFullName().toUpperCase())
                .collect(Collectors.toList());
        formalNames.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Using String::toUpperCase method reference is more concise and");
        System.out.println("readable than equivalent lambda expressions like x -> x.toUpperCase().");
    }
}