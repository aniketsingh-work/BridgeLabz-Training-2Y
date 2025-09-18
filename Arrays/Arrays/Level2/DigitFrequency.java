import java.util.Scanner;

public class DigitFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = sc.nextLong();

        String numStr = String.valueOf(Math.abs(number));
        int length = numStr.length();

        int[] digits = new int[length];
        int[] frequency = new int[10];

        for (int i = 0; i < length; i++) {
            digits[i] = numStr.charAt(i) - '0';
        }

        for (int d : digits) {
            frequency[d]++;
        }

        System.out.println("\nDigit Frequency:");
        for (int i = 0; i < 10; i++) {
            if (frequency[i] > 0) {
                System.out.println(i + " -> " + frequency[i]);
            }
        }
        sc.close();
    }
}
