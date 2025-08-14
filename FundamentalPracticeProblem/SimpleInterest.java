import java.util.Scanner;

public class SimpleInterest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter principal: ");
        double p = in.nextDouble();
        System.out.print("Enter rate (annual %): ");
        double r = in.nextDouble();
        System.out.print("Enter time (years): ");
        double t = in.nextDouble();
        double si = (p * r * t) / 100;
        System.out.println("Simple Interest = " + si);
        in.close();
    }
}
