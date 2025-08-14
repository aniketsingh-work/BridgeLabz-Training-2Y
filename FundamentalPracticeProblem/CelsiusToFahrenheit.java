import java.util.Scanner;

public class CelsiusToFahrenheit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter temperature in Celsius: ");
        double c = in.nextDouble();
        double f = (c * 9/5) + 32;
        System.out.println("Fahrenheit = " + f);
        in.close();
    }
}
