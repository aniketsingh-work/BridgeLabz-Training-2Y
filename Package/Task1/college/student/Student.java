package college.student;

public class Student {
    private String name;
    private String rollNumber;
    
    public Student(String name, String rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }
    
    public void displayStudentName() {
        System.out.println("Student Name: " + name);
    }
    
    public void displayRollNumber() {
        System.out.println("Roll Number: " + rollNumber);
    }
    
    public String getName() {
        return name;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
}