import java.util.Scanner;

public class StoreNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] numbers = new double[10];
        double total = 0.0;
        int index = 0;

        System.out.println("Enter numbers (max 10). Enter 0 or negative to stop:");

        while (true) {
            double input = sc.nextDouble();

            if (input <= 0) {
                break;
            }
            if (index == 10) {
                break;
            }

            numbers[index] = input;
            index++;
        }

        System.out.println("\nNumbers entered:");
        for (int i = 0; i < index; i++) {
            System.out.println(numbers[i]);
            total += numbers[i];
        }

        System.out.println("\nTotal = " + total);

        sc.close();
    }
}
