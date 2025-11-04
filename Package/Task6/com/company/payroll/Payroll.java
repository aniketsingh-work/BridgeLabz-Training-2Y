package com.company.payroll;
import com.company.hr.Employee;

public class Payroll {
    public double calculateBonus(Employee e) {
        // Adds 10% bonus to salary
        return e.getSalary() * 0.10;
    }
    
    public double calculateTotalSalary(Employee e) {
        return e.getSalary() + calculateBonus(e);
    }
    
    public void displayPayrollInfo(Employee e) {
        System.out.println("Employee: " + e.getName());
        System.out.println("Base Salary: $" + e.getSalary());
        System.out.println("Bonus (10%): $" + calculateBonus(e));
        System.out.println("Total Salary: $" + calculateTotalSalary(e));
    }
}