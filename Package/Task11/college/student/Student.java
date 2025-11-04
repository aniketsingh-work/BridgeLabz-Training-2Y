package college.student;

public class Student {
    private String name;
    private String rollNumber;
    private String department;
    private int year;
    
    public Student(String name, String rollNumber, String department, int year) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.year = year;
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
    
    public int getYear() {
        return year;
    }
    
    public void displayStudentInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Department: " + department);
        System.out.println("Year: " + year);
    }
}