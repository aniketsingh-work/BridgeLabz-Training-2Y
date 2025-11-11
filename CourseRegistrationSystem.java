import java.util.*;

public class CourseRegistrationSystem {
    private Map<String, Integer> courseRegistrations;
    
    public CourseRegistrationSystem() {
        courseRegistrations = new HashMap<>();
    }
    
    public void addCourse(String courseCode, int initialCount) {
        courseRegistrations.put(courseCode, initialCount);
    }
    
    public void addStudent(String courseCode) {
        if (courseRegistrations.containsKey(courseCode)) {
            courseRegistrations.put(courseCode, courseRegistrations.get(courseCode) + 1);
        }
    }
    
    public void dropStudent(String courseCode) {
        if (courseRegistrations.containsKey(courseCode)) {
            int currentCount = courseRegistrations.get(courseCode);
            if (currentCount > 0) {
                courseRegistrations.put(courseCode, currentCount - 1);
            }
        }
    }
    
    public void printCourseStatus() {
        List<String> nearFull = new ArrayList<>();
        List<String> underSubscribed = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : courseRegistrations.entrySet()) {
            int count = entry.getValue();
            if (count >= 50) {
                nearFull.add(entry.getKey());
            } else if (count < 5) {
                underSubscribed.add(entry.getKey());
            }
        }
        
        System.out.println("Near full courses: " + nearFull);
        System.out.println("Under subscribed courses: " + underSubscribed);
    }
    
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.addCourse("CS101", 45);
        system.addCourse("CS201", 3);
        system.addCourse("MATH101", 55);
        system.addCourse("ENG101", 2);
        system.addCourse("PHYS101", 48);
        
        system.addStudent("CS101");
        system.dropStudent("ENG101");
        
        system.printCourseStatus();
    }
}