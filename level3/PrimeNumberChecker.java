package level3;

import java.util.Scanner;

public class PrimeNumberChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if number is greater than 1
        if (number <= 1) {
            System.out.println(number + " is not a prime number");
            return;
        }
        
        // Check if number is prime
        boolean isPrime = true;
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        
        if (isPrime) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }
        
        scanner.close();
    }
}