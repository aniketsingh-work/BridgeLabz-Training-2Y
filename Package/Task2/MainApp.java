import com.university.department.cse.Course;

public class MainApp {
    public static void main(String[] args) {
        // Create a Course object
        Course course = new Course("Data Structures and Algorithms", "CSE201", 4);
        
        // Print course details
        course.printCourseDetails();
        
        System.out.println("\nFolder structure when compiled:");
        System.out.println("Task2/");
        System.out.println("  com/");
        System.out.println("    university/");
        System.out.println("      department/");
        System.out.println("        cse/");
        System.out.println("          Course.class");
        System.out.println("  MainApp.class");
        
        System.out.println("\nWhen compiling and running the program:");
        System.out.println("1. Compile Course.java from the Task2 directory:");
        System.out.println("   javac com/university/department/cse/Course.java");
        System.out.println("2. Compile MainApp.java from the Task2 directory:");
        System.out.println("   javac MainApp.java");
        System.out.println("3. Run MainApp from the Task2 directory:");
        System.out.println("   java MainApp");
    }
}