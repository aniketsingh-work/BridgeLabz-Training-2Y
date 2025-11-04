package college.student;

public class Student {
    private String name;
    private String rollNumber;
    private String department;
    
    public Student(String name, String rollNumber, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void displayStudentInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Department: " + department);
    }
}