import college.faculty.Faculty;
import college.student.Student;

public class MainClass {
    public static void main(String[] args) {
        // Create a Student object
        Student student = new Student("John Doe", "S001");
        
        // Call student methods
        student.displayStudentName();
        student.displayRollNumber();
        
        System.out.println(); // Empty line for readability
        
        // Create a Faculty object
        Faculty faculty = new Faculty("Dr. Smith", "Computer Science");
        
        // Call faculty methods
        faculty.displayName();
        faculty.displaySubject();
    }
}