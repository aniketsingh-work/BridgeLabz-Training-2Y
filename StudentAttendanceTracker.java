import java.util.*;

public class StudentAttendanceTracker {
    private Map<String, Integer> attendance;
    private static final int THRESHOLD = 10;
    
    public StudentAttendanceTracker(List<String> studentNames) {
        attendance = new HashMap<>();
        for (String name : studentNames) {
            attendance.put(name, 0);
        }
    }
    
    public void markAttendance(List<String> presentStudents) {
        for (String student : presentStudents) {
            if (attendance.containsKey(student)) {
                attendance.put(student, attendance.get(student) + 1);
            }
        }
    }
    
    public List<String> getUnderAttendingStudents() {
        List<String> underAttending = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : attendance.entrySet()) {
            if (entry.getValue() < THRESHOLD) {
                underAttending.add(entry.getKey());
            }
        }
        return underAttending;
    }
    
    public static void main(String[] args) {
        List<String> students = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        StudentAttendanceTracker tracker = new StudentAttendanceTracker(students);
        
        // Simulate attendance over 15 days
        Random random = new Random();
        for (int day = 0; day < 15; day++) {
            List<String> present = new ArrayList<>();
            for (String student : students) {
                if (random.nextBoolean()) { // Randomly decide if student is present
                    present.add(student);
                }
            }
            tracker.markAttendance(present);
        }
        
        System.out.println("Under attending students: " + tracker.getUnderAttendingStudents());
    }
}