import java.util.Scanner;

public class NumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        System.out.println("Enter 5 numbers:");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("\nResults:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (arr[i] % 2 == 0) {
                    System.out.println(arr[i] + " is Positive and Even");
                } else {
                    System.out.println(arr[i] + " is Positive and Odd");
                }
            } else if (arr[i] < 0) {
                System.out.println(arr[i] + " is Negative");
            } else {
                System.out.println(arr[i] + " is Zero");
            }
        }

        System.out.println("\nComparison of first and last elements:");
        if (arr[0] == arr[arr.length - 1]) {
            System.out.println("First element " + arr[0] + " is equal to last element " + arr[arr.length - 1]);
        } else if (arr[0] > arr[arr.length - 1]) {
            System.out.println("First element " + arr[0] + " is greater than last element " + arr[arr.length - 1]);
        } else {
            System.out.println("First element " + arr[0] + " is less than last element " + arr[arr.length - 1]);
        }

        sc.close();
    }
}
