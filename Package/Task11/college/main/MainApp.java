package college.main;

import college.student.Student;
import college.faculty.Faculty;
import college.department.Department;

public class MainApp {
    public static void main(String[] args) {
        // Create Student objects
        Student student1 = new Student("Alice Johnson", "S001", "Computer Science", 2);
        Student student2 = new Student("Bob Smith", "S002", "Electrical Engineering", 3);
        
        // Create Faculty objects
        Faculty faculty1 = new Faculty("Dr. Sarah Williams", "Computer Science", "Data Structures", 8);
        Faculty faculty2 = new Faculty("Prof. Michael Chen", "Mathematics", "Calculus", 12);
        
        // Create Department objects
        Department csDept = new Department("Computer Science", "Dr. Amanda Roberts", 15, 200);
        Department eeDept = new Department("Electrical Engineering", "Dr. James Wilson", 12, 180);
        
        System.out.println("COLLEGE MANAGEMENT SYSTEM");
        System.out.println("=========================\n");
        
        // Display student information
        System.out.println("STUDENT INFORMATION:");
        student1.displayStudentInfo();
        System.out.println();
        student2.displayStudentInfo();
        System.out.println();
        
        // Display faculty information
        System.out.println("FACULTY INFORMATION:");
        faculty1.displayFacultyInfo();
        System.out.println();
        faculty2.displayFacultyInfo();
        System.out.println();
        
        // Display department information
        System.out.println("DEPARTMENT INFORMATION:");
        csDept.displayDepartmentInfo();
        System.out.println();
        eeDept.displayDepartmentInfo();
        System.out.println();
        
        System.out.println("How packages helped in organizing this project:");
        System.out.println("1. Logical separation of concerns: Each package handles a specific aspect of the college");
        System.out.println("2. Easy maintenance: Related classes are grouped together in their respective packages");
        System.out.println("3. Code reusability: Classes in one package can be easily imported and used in another");
        System.out.println("4. Better readability: The package structure clearly indicates the purpose of each class");
        System.out.println("5. Reduced naming conflicts: Similar class names in different packages don't conflict");
        System.out.println("6. Access control: Packages allow for proper encapsulation and access management");
    }
}