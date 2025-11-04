import college.student.Student;

public class MainApp {
    public static void main(String[] args) {
        // Create a Student object using the Student class from collegeinfo module
        Student student = new Student("Alice Johnson", "STU001", "Computer Science");
        
        // Display student information
        student.displayStudentInfo();
        
        System.out.println("\nDemonstrating how to compile and run a modular application:");
        System.out.println("1. Compile the collegeinfo module:");
        System.out.println("   javac --module-source-path . -d mods --module collegeinfo");
        System.out.println("2. Compile the app module:");
        System.out.println("   javac --module-source-path . -d mods --module app");
        System.out.println("3. Run the application:");
        System.out.println("   java --module-path mods --module app");
        
        System.out.println("\nDifference between packages and modules:");
        System.out.println("1. Packages:");
        System.out.println("   - Organize classes within a single program");
        System.out.println("   - Provide namespace management");
        System.out.println("   - Access control with public, private, protected");
        System.out.println("   - No explicit dependency declaration required");
        System.out.println("   - Part of the language since Java 1.0");
        
        System.out.println("\n2. Modules:");
        System.out.println("   - Organize packages into larger units");
        System.out.println("   - Explicitly declare dependencies with 'requires'");
        System.out.println("   - Explicitly export packages with 'exports'");
        System.out.println("   - Provide stronger encapsulation than packages");
        System.out.println("   - Introduced in Java 9");
        System.out.println("   - Enable the creation of the modular JDK");
    }
}