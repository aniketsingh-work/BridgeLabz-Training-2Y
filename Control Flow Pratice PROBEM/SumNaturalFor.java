import java.util.Scanner;

public class SumNaturalFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number >= 0) {
            // Calculate using formula
            int formulaSum = number * (number + 1) / 2;
            
            // Calculate using for loop
            int loopSum = 0;
            for (int counter = 1; counter <= number; counter++) {
                loopSum += counter;
            }
            
            System.out.println("Formula sum: " + formulaSum);
            System.out.println("For loop sum: " + loopSum);
            System.out.println("Both results are equal: " + (formulaSum == loopSum));
        } else {
            System.out.println("The number " + number + " is not a natural number");
        }
        
        scanner.close();
    }
}