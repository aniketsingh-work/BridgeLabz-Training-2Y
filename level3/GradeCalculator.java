package level3;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter marks for three subjects:");
        
        System.out.print("Physics: ");
        int physics = scanner.nextInt();
        
        System.out.print("Chemistry: ");
        int chemistry = scanner.nextInt();
        
        System.out.print("Mathematics: ");
        int mathematics = scanner.nextInt();
        
        // Calculate average
        double average = (physics + chemistry + mathematics) / 3.0;
        
        // Determine grade and remarks
        String grade, remarks;
        
        if (average >= 90) {
            grade = "A+";
            remarks = "Outstanding";
        } else if (average >= 80) {
            grade = "A";
            remarks = "Excellent";
        } else if (average >= 70) {
            grade = "B";
            remarks = "Good";
        } else if (average >= 60) {
            grade = "C";
            remarks = "Average";
        } else if (average >= 50) {
            grade = "D";
            remarks = "Below Average";
        } else {
            grade = "F";
            remarks = "Fail";
        }
        
        // Display results
        System.out.println("\n--- Results ---");
        System.out.println("Average Mark: " + String.format("%.2f", average));
        System.out.println("Grade: " + grade);
        System.out.println("Remarks: " + remarks);
        
        scanner.close();
    }
}