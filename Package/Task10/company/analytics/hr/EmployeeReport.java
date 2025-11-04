package com.company.analytics.hr;

public class EmployeeReport {
    private String[] departments = {"Engineering", "Marketing", "Sales", "HR"};
    private int[] employeeCount = {45, 22, 30, 12};
    private double[] avgPerformance = {4.2, 3.8, 4.0, 4.5};  // On a scale of 5
    
    public void printEmployeePerformanceData() {
        System.out.println("EMPLOYEE REPORT - PERFORMANCE DATA");
        System.out.println("==================================");
        for (int i = 0; i < departments.length; i++) {
            System.out.println(departments[i] + " Department: " + 
                             employeeCount[i] + " employees, " + 
                             "Avg Performance: " + avgPerformance[i] + "/5.0");
        }
        System.out.println();
    }
    
    public int getTotalEmployees() {
        int total = 0;
        for (int count : employeeCount) {
            total += count;
        }
        return total;
    }
}