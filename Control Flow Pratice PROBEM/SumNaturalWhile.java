import java.util.Scanner;

public class SumNaturalWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (number >= 0) {
            // Calculate using formula
            int formulaSum = number * (number + 1) / 2;
            
            // Calculate using while loop
            int loopSum = 0;
            int counter = 1;
            while (counter <= number) {
                loopSum += counter;
                counter++;
            }
            
            System.out.println("Formula sum: " + formulaSum);
            System.out.println("While loop sum: " + loopSum);
            System.out.println("Both results are equal: " + (formulaSum == loopSum));
        } else {
            System.out.println("The number " + number + " is not a natural number");
        }
        
        scanner.close();
    }
}