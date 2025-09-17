import java.util.Scanner;

public class FactorialFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number >= 0) {
            int factorial = 1;
            
            for (int counter = 1; counter <= number; counter++) {
                factorial *= counter;
            }
            
            System.out.println("Factorial of " + number + " is " + factorial);
        } else {
            System.out.println("Please enter a positive integer.");
        }
        
        scanner.close();
    }
}