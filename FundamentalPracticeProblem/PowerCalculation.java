import java.util.Scanner;

public class PowerCalculation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base: ");
        double base = in.nextDouble();
        System.out.print("Enter exponent: ");
        double exp = in.nextDouble();
        double result = Math.pow(base, exp);
        System.out.println(base + " raised to " + exp + " = " + result);
        in.close();
    }
}
