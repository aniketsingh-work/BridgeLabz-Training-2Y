import java.util.ArrayList;
import java.util.List;

public class UniversityCourseDemo {
    // Method to handle any type of course using wildcards
    public static void handleCourses(List<? extends CourseType> courses) {
        System.out.println("Handling courses:");
        for (CourseType course : courses) {
            System.out.println("- " + course.getCourseName());
            course.conductEvaluation();
        }
    }
    
    public static void main(String[] args) {
        // Create different types of courses
        Course<ExamCourse> mathCourse = new Course<>(new ExamCourse("Calculus"));
        mathCourse.enrollStudent("Alice");
        mathCourse.enrollStudent("Bob");
        
        Course<AssignmentCourse> programmingCourse = new Course<>(new AssignmentCourse("Java Programming"));
        programmingCourse.enrollStudent("Charlie");
        programmingCourse.enrollStudent("David");
        programmingCourse.enrollStudent("Eve");
        
        Course<ResearchCourse> researchCourse = new Course<>(new ResearchCourse("Advanced Algorithms"));
        researchCourse.enrollStudent("Frank");
        researchCourse.enrollStudent("Grace");
        
        // Display course information
        System.out.println(mathCourse);
        System.out.println(programmingCourse);
        System.out.println(researchCourse);
        
        // Conduct evaluations
        System.out.println("\nConducting evaluations:");
        mathCourse.conductCourseEvaluation();
        programmingCourse.conductCourseEvaluation();
        researchCourse.conductCourseEvaluation();
        
        // Create a list of different course types to demonstrate wildcard usage
        List<CourseType> allCourses = new ArrayList<>();
        allCourses.add(mathCourse.getCourseType());
        allCourses.add(programmingCourse.getCourseType());
        allCourses.add(researchCourse.getCourseType());
        
        System.out.println();
        handleCourses(allCourses);
    }
}