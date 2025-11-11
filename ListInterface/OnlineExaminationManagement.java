import java.util.*;

public class OnlineExaminationManagement {
    
    static class Question {
        private int questionId;
        private String questionText;
        private String correctAnswer;
        private String topic;
        
        public Question(int questionId, String questionText, String correctAnswer, String topic) {
            this.questionId = questionId;
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.topic = topic;
        }
        
        // Getters
        public int getQuestionId() { return questionId; }
        public String getQuestionText() { return questionText; }
        public String getCorrectAnswer() { return correctAnswer; }
        public String getTopic() { return topic; }
        
        @Override
        public String toString() {
            return "Q" + questionId + ": " + questionText;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Question question = (Question) obj;
            return questionId == question.questionId;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(questionId);
        }
    }
    
    static class Student {
        private String studentId;
        private String name;
        private int age;
        
        public Student(String studentId, String name, int age) {
            this.studentId = studentId;
            this.name = name;
            this.age = age;
        }
        
        // Getters
        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public int getAge() { return age; }
        
        @Override
        public String toString() {
            return name + "(" + studentId + ")";
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
    
    // Use List to store all questions for an exam
    private List<Question> allQuestions;
    
    // Use Set to store unique student IDs
    private Set<String> uniqueStudentIds;
    
    // Use Queue to manage students waiting to take the test
    private Queue<Student> studentQueue;
    
    // Use Stack for navigating previous/next questions (back functionality)
    private Stack<Question> questionNavigationStack;
    
    public OnlineExaminationManagement() {
        this.allQuestions = new ArrayList<>();
        this.uniqueStudentIds = new HashSet<>();
        this.studentQueue = new LinkedList<>();
        this.questionNavigationStack = new Stack<>();
    }
    
    // 1. Enroll students and ensure no duplicate IDs
    public boolean enrollStudent(Student student) {
        if (uniqueStudentIds.contains(student.getStudentId())) {
            System.out.println("Student with ID " + student.getStudentId() + " already exists!");
            return false;
        }
        
        uniqueStudentIds.add(student.getStudentId());
        studentQueue.add(student);
        System.out.println("Student " + student.getName() + " enrolled successfully.");
        return true;
    }
    
    // 2. Add and randomize exam questions
    public void addQuestion(Question question) {
        allQuestions.add(question);
    }
    
    public void randomizeQuestions() {
        Collections.shuffle(allQuestions);
        System.out.println("Questions randomized successfully.");
    }
    
    // 3. Serve students in order of queue
    public Student getNextStudent() {
        if (!studentQueue.isEmpty()) {
            return studentQueue.remove();
        }
        return null;
    }
    
    public int getStudentQueueSize() {
        return studentQueue.size();
    }
    
    // 4. Allow students to navigate between questions using a stack
    public void navigateToQuestion(Question currentQuestion) {
        questionNavigationStack.push(currentQuestion);
    }
    
    public Question goBackToPreviousQuestion() {
        if (!questionNavigationStack.isEmpty()) {
            return questionNavigationStack.pop();
        }
        return null;
    }
    
    public boolean canGoBack() {
        return !questionNavigationStack.isEmpty();
    }
    
    // Getters
    public List<Question> getAllQuestions() {
        return new ArrayList<>(allQuestions);
    }
    
    public Set<String> getUniqueStudentIds() {
        return new HashSet<>(uniqueStudentIds);
    }
    
    public Queue<Student> getStudentQueue() {
        return new LinkedList<>(studentQueue);
    }
    
    public Stack<Question> getQuestionNavigationStack() {
        return (Stack<Question>) questionNavigationStack.clone();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Online Examination Management System ===");
        
        OnlineExaminationManagement examSystem = new OnlineExaminationManagement();
        
        // Add sample questions
        examSystem.addQuestion(new Question(1, "What is the capital of France?", "Paris", "Geography"));
        examSystem.addQuestion(new Question(2, "What is 2+2?", "4", "Math"));
        examSystem.addQuestion(new Question(3, "Who wrote Romeo and Juliet?", "Shakespeare", "Literature"));
        examSystem.addQuestion(new Question(4, "What is the largest planet?", "Jupiter", "Science"));
        examSystem.addQuestion(new Question(5, "What is the square root of 16?", "4", "Math"));
        
        System.out.println("\n1. Added questions to the system:");
        for (Question q : examSystem.getAllQuestions()) {
            System.out.println(q);
        }
        
        // Randomize questions
        examSystem.randomizeQuestions();
        System.out.println("\nAfter randomization:");
        for (Question q : examSystem.getAllQuestions()) {
            System.out.println(q);
        }
        
        // Enroll students (including a duplicate to test uniqueness)
        examSystem.enrollStudent(new Student("S001", "John Doe", 20));
        examSystem.enrollStudent(new Student("S002", "Jane Smith", 21));
        examSystem.enrollStudent(new Student("S003", "Bob Johnson", 19));
        examSystem.enrollStudent(new Student("S001", "John Duplicate", 22)); // This should fail
        
        System.out.println("\n2. Students in queue:");
        Queue<Student> queueCopy = examSystem.getStudentQueue();
        while (!queueCopy.isEmpty()) {
            System.out.println(queueCopy.remove());
        }
        
        // Simulate question navigation
        System.out.println("\n3. Question navigation simulation:");
        Question q1 = examSystem.getAllQuestions().get(0);
        Question q2 = examSystem.getAllQuestions().get(1);
        Question q3 = examSystem.getAllQuestions().get(2);
        
        System.out.println("Student navigates to: " + q1);
        examSystem.navigateToQuestion(q1);
        
        System.out.println("Student navigates to: " + q2);
        examSystem.navigateToQuestion(q2);
        
        System.out.println("Student navigates to: " + q3);
        examSystem.navigateToQuestion(q3);
        
        System.out.println("Student goes back to previous question: " + examSystem.goBackToPreviousQuestion());
        System.out.println("Student goes back again: " + examSystem.goBackToPreviousQuestion());
        
        // Serve students in order
        System.out.println("\n4. Serving students in queue order:");
        while (examSystem.getStudentQueueSize() > 0) {
            Student student = examSystem.getNextStudent();
            if (student != null) {
                System.out.println("Serving student: " + student);
            }
        }
    }
}