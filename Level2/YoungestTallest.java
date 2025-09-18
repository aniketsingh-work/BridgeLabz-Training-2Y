import java.util.Scanner;

public class YoungestTallest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Getting input for Amar
        System.out.print("Enter Amar's age: ");
        int amarAge = scanner.nextInt();
        System.out.print("Enter Amar's height: ");
        int amarHeight = scanner.nextInt();
        
        // Getting input for Akbar
        System.out.print("Enter Akbar's age: ");
        int akbarAge = scanner.nextInt();
        System.out.print("Enter Akbar's height: ");
        int akbarHeight = scanner.nextInt();
        
        // Getting input for Anthony
        System.out.print("Enter Anthony's age: ");
        int anthonyAge = scanner.nextInt();
        System.out.print("Enter Anthony's height: ");
        int anthonyHeight = scanner.nextInt();
        
        // Finding the youngest
        int youngestAge = amarAge;
        String youngestName = "Amar";
        
        if (akbarAge < youngestAge) {
            youngestAge = akbarAge;
            youngestName = "Akbar";
        }
        
        if (anthonyAge < youngestAge) {
            youngestAge = anthonyAge;
            youngestName = "Anthony";
        }
        
        // Finding the tallest
        int tallestHeight = amarHeight;
        String tallestName = "Amar";
        
        if (akbarHeight > tallestHeight) {
            tallestHeight = akbarHeight;
            tallestName = "Akbar";
        }
        
        if (anthonyHeight > tallestHeight) {
            tallestHeight = anthonyHeight;
            tallestName = "Anthony";
        }
        
        System.out.println("Youngest friend: " + youngestName);
        System.out.println("Tallest friend: " + tallestName);
        
        scanner.close();
    }
}