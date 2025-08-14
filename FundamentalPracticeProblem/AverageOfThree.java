import java.util.Scanner;

public class AverageOfThree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter three numbers separated by space: ");
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double avg = (a + b + c) / 3;
        System.out.println("Average = " + avg);
        in.close();
    }
}
