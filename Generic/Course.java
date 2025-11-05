import java.util.ArrayList;
import java.util.List;

class Course<T extends CourseType> {
    private T courseType;
    private List<String> enrolledStudents;
    
    public Course(T courseType) {
        this.courseType = courseType;
        this.enrolledStudents = new ArrayList<>();
    }
    
    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }
    
    public void conductCourseEvaluation() {
        courseType.conductEvaluation();
    }
    
    public T getCourseType() {
        return courseType;
    }
    
    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    @Override
    public String toString() {
        return "Course: " + courseType.getCourseName() + " (" + enrolledStudents.size() + " students enrolled)";
    }
}