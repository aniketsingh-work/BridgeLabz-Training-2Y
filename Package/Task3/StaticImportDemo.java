import static java.lang.Math.*;

public class StaticImportDemo {
    public static void main(String[] args) {
        // Demonstrating sqrt() method
        double number1 = 25.0;
        double sqrtResult = sqrt(number1);
        System.out.println("Square root of " + number1 + " is: " + sqrtResult);
        
        // Demonstrating pow() method
        double base = 2.0;
        double exponent = 3.0;
        double powerResult = pow(base, exponent);
        System.out.println(base + " raised to the power " + exponent + " is: " + powerResult);
        
        // Demonstrating max() method
        int a = 15;
        int b = 25;
        int maxResult = max(a, b);
        System.out.println("Maximum of " + a + " and " + b + " is: " + maxResult);
        
        // Demonstrating min() method
        double x = 10.5;
        double y = 7.3;
        double minResult = min(x, y);
        System.out.println("Minimum of " + x + " and " + y + " is: " + minResult);
        
        // Demonstrating abs() method
        int negativeNumber = -42;
        int absResult = abs(negativeNumber);
        System.out.println("Absolute value of " + negativeNumber + " is: " + absResult);
        
        // Additional demonstration of other Math methods
        double angle = PI / 3; // 60 degrees in radians
        double sinResult = sin(angle);
        System.out.println("Sin of " + angle + " radians is: " + sinResult);
        
        double randomValue = random();
        System.out.println("Random value between 0 and 1: " + randomValue);
        
        System.out.println("\nWhy static import can be useful:");
        System.out.println("1. Reduces code verbosity - you can call methods directly without class name");
        System.out.println("2. Makes code more readable when using methods frequently");
        System.out.println("3. Especially useful for constants like PI, E, etc.");
        
        System.out.println("\nWhen static import should be avoided:");
        System.out.println("1. When it reduces code clarity or makes it unclear which class a method belongs to");
        System.out.println("2. When importing methods with common names that might conflict");
        System.out.println("3. When importing too many static members from different classes, causing confusion");
    }
}