import java.util.Scanner;

public class PerimeterOfRectangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter length: ");
        double l = in.nextDouble();
        System.out.print("Enter width: ");
        double w = in.nextDouble();
        double perimeter = 2 * (l + w);
        System.out.println("Perimeter = " + perimeter);
        in.close();
    }
}
