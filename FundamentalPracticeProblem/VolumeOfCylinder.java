import java.util.Scanner;

public class VolumeOfCylinder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter radius: ");
        double r = in.nextDouble();
        System.out.print("Enter height: ");
        double h = in.nextDouble();
        double volume = Math.PI * r * r * h;
        System.out.println("Volume = " + volume);
        in.close();
    }
}
