import java.util.Scanner;

public class KilometersToMiles {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter distance in kilometers: ");
        double km = s.nextDouble();
        double miles = km * 0.621371;
        System.out.println(km + " km = " + miles + " miles");
        s.close();
    }
}
