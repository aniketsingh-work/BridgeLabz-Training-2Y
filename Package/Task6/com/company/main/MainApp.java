package com.company.main;

import com.company.hr.Employee;
import com.company.payroll.Payroll;

public class MainApp {
    public static void main(String[] args) {
        // Create an Employee object
        Employee emp = new Employee(101, "John Doe", "Engineering", 75000.0);
        
        // Create a Payroll object
        Payroll payroll = new Payroll();
        
        // Display employee details
        System.out.println("Employee Details:");
        emp.displayEmployeeDetails();
        System.out.println();
        
        // Display payroll information (salary with bonus)
        System.out.println("Payroll Information:");
        payroll.displayPayrollInfo(emp);
        
        System.out.println("\nFolder structure:");
        System.out.println("Task6/");
        System.out.println("  com/");
        System.out.println("    company/");
        System.out.println("      hr/");
        System.out.println("        Employee.java");
        System.out.println("      payroll/");
        System.out.println("        Payroll.java");
        System.out.println("      main/");
        System.out.println("        MainApp.java");
        
        System.out.println("\nThe folder structure must match the package declaration because:");
        System.out.println("1. Java uses the directory structure to locate classes based on their package names");
        System.out.println("2. The package com.company.main means the class must be in com/company/main/ directory");
        System.out.println("3. This prevents naming conflicts and organizes code logically");
        System.out.println("4. The JVM uses this structure to find and load classes at runtime");
    }
}