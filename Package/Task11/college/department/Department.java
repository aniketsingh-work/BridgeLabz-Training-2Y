package college.department;

public class Department {
    private String name;
    private String head;
    private int numberOfFaculty;
    private int numberOfStudents;
    
    public Department(String name, String head, int numberOfFaculty, int numberOfStudents) {
        this.name = name;
        this.head = head;
        this.numberOfFaculty = numberOfFaculty;
        this.numberOfStudents = numberOfStudents;
    }
    
    public String getName() {
        return name;
    }
    
    public String getHead() {
        return head;
    }
    
    public int getNumberOfFaculty() {
        return numberOfFaculty;
    }
    
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    public void displayDepartmentInfo() {
        System.out.println("Department Name: " + name);
        System.out.println("Department Head: " + head);
        System.out.println("Number of Faculty: " + numberOfFaculty);
        System.out.println("Number of Students: " + numberOfStudents);
    }
}