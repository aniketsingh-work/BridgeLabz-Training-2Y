import java.util.*;

public class StudentGradeTracker {
    private Map<String, Double> studentGrades;
    
    public StudentGradeTracker() {
        studentGrades = new HashMap<>();
    }
    
    public void addStudent(String name, Double grade) {
        studentGrades.put(name, grade);
    }
    
    public void updateGrade(String name, Double newGrade) {
        if (studentGrades.containsKey(name)) {
            studentGrades.put(name, newGrade);
        }
    }
    
    public void removeStudent(String name) {
        studentGrades.remove(name);
    }
    
    public void printGradesInAlphabeticalOrder() {
        Map<String, Double> sortedMap = new TreeMap<>(studentGrades);
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        StudentGradeTracker tracker = new StudentGradeTracker();
        tracker.addStudent("Alice", 85.5);
        tracker.addStudent("Bob", 92.0);
        tracker.addStudent("Charlie", 78.5);
        
        tracker.updateGrade("Bob", 95.0);
        tracker.removeStudent("Charlie");
        
        tracker.printGradesInAlphabeticalOrder();
    }
}