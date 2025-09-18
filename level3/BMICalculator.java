package level3;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter weight (in kg): ");
        double weight = scanner.nextDouble();
        
        System.out.print("Enter height (in cm): ");
        double height = scanner.nextDouble();
        
        // Convert height from cm to meters
        height = height / 100;
        
        // Calculate BMI
        double bmi = weight / (height * height);
        
        // Determine weight status
        String weightStatus;
        if (bmi < 18.5) {
            weightStatus = "Underweight";
        } else if (bmi < 25) {
            weightStatus = "Normal weight";
        } else if (bmi < 30) {
            weightStatus = "Overweight";
        } else {
            weightStatus = "Obese";
        }
        
        // Display results
        System.out.println("\n--- BMI Results ---");
        System.out.println("BMI: " + String.format("%.2f", bmi));
        System.out.println("Weight Status: " + weightStatus);
        
        scanner.close();
    }
}