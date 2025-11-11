import java.util.*;

public class CollegeAdmissionSystem {
    
    static class Student {
        private String studentId;
        private String name;
        private int marks;
        private String email;
        
        public Student(String studentId, String name, int marks, String email) {
            this.studentId = studentId;
            this.name = name;
            this.marks = marks;
            this.email = email;
        }
        
        // Getters
        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public int getMarks() { return marks; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return name + "(ID:" + studentId + ", Marks:" + marks + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return Objects.equals(studentId, student.studentId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(studentId);
        }
    }
    
    // Use List to store all applicants
    private List<Student> allApplicants;
    
    // Use Set to store shortlisted candidates (unique students)
    private Set<Student> shortlistedCandidates;
    
    // Use Queue to manage the interview waiting list
    private Queue<Student> interviewQueue;
    
    // Use TreeSet to maintain a final merit list sorted by marks
    private TreeSet<Student> meritList;
    
    public CollegeAdmissionSystem() {
        this.allApplicants = new ArrayList<>();
        this.shortlistedCandidates = new HashSet<>();
        this.interviewQueue = new LinkedList<>();
        // TreeSet with custom comparator for descending marks order
        this.meritList = new TreeSet<>((s1, s2) -> {
            // Compare marks in descending order (higher marks first)
            int marksComparison = Integer.compare(s2.getMarks(), s1.getMarks());
            if (marksComparison != 0) {
                return marksComparison;
            }
            // If marks are equal, compare by student name
            return s1.getName().compareTo(s2.getName());
        });
    }
    
    // 1. Accept all applications in a list
    public void addApplication(Student student) {
        allApplicants.add(student);
        System.out.println("Application received from: " + student);
    }
    
    // 2. Shortlist eligible students into a set
    public void shortlistStudents(int cutoffMarks) {
        for (Student student : allApplicants) {
            if (student.getMarks() >= cutoffMarks && !shortlistedCandidates.contains(student)) {
                shortlistedCandidates.add(student);
                interviewQueue.add(student);
                System.out.println("Shortlisted: " + student);
            }
        }
    }
    
    // 3. Queue shortlisted students for interviews
    public void scheduleInterviews() {
        System.out.println("Scheduled " + interviewQueue.size() + " students for interviews");
    }
    
    public Student getNextStudentForInterview() {
        if (!interviewQueue.isEmpty()) {
            return interviewQueue.remove();
        }
        return null;
    }
    
    // 4. After interviews, add final selections into a merit-based TreeSet
    public void addStudentToMeritList(Student student) {
        meritList.add(student);
        System.out.println("Added to merit list: " + student);
    }
    
    // Simulate interview process and final selection
    public void conductInterviewsAndSelection() {
        System.out.println("Conducting interviews...");
        
        while (!interviewQueue.isEmpty()) {
            Student student = interviewQueue.remove();
            
            // Simulate interview result (for simplicity, we'll just add them to merit list)
            // In a real system, there would be interview scores and decisions
            addStudentToMeritList(student);
        }
    }
    
    // Getters
    public List<Student> getAllApplicants() {
        return new ArrayList<>(allApplicants);
    }
    
    public Set<Student> getShortlistedCandidates() {
        return new HashSet<>(shortlistedCandidates);
    }
    
    public Queue<Student> getInterviewQueue() {
        return new LinkedList<>(interviewQueue);
    }
    
    public TreeSet<Student> getMeritList() {
        return new TreeSet<>(meritList);
    }
    
    public static void main(String[] args) {
        System.out.println("=== College Admission System ===");
        
        CollegeAdmissionSystem admissionSystem = new CollegeAdmissionSystem();
        
        // Add applications
        admissionSystem.addApplication(new Student("S001", "Alice Johnson", 85, "alice@email.com"));
        admissionSystem.addApplication(new Student("S002", "Bob Smith", 92, "bob@email.com"));
        admissionSystem.addApplication(new Student("S003", "Charlie Brown", 78, "charlie@email.com"));
        admissionSystem.addApplication(new Student("S004", "Diana Prince", 96, "diana@email.com"));
        admissionSystem.addApplication(new Student("S005", "Eve Wilson", 8, "eve@email.com"));
        admissionSystem.addApplication(new Student("S006", "Frank Miller", 75, "frank@email.com"));
        
        System.out.println("\n1. All applicants:");
        for (Student student : admissionSystem.getAllApplicants()) {
            System.out.println(" " + student);
        }
        
        // Shortlist students with marks >= 80
        System.out.println("\n2. Shortlisting students with marks >= 80:");
        admissionSystem.shortlistStudents(80);
        
        System.out.println("\n3. Shortlisted candidates:");
        for (Student student : admissionSystem.getShortlistedCandidates()) {
            System.out.println(" " + student);
        }
        
        System.out.println("\n4. Interview queue size: " + admissionSystem.getInterviewQueue().size());
        
        // Conduct interviews and make selections
        System.out.println("\n5. Conducting interviews and making selections:");
        admissionSystem.conductInterviewsAndSelection();
        
        System.out.println("\n6. Final merit list (sorted by marks):");
        int rank = 1;
        for (Student student : admissionSystem.getMeritList()) {
            System.out.println(rank + ". " + student);
            rank++;
        }
    }
}