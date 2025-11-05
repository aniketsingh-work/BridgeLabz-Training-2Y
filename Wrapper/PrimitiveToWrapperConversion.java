import java.util.Scanner;

public class PrimitiveToWrapperConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int primitiveInt = scanner.nextInt();
        
        // Convert primitive to wrapper
        Integer wrapperInt = Integer.valueOf(primitiveInt); // or new Integer(primitiveInt) - deprecated
        
        System.out.println("Primitive value: " + primitiveInt);
        System.out.println("Wrapper object value: " + wrapperInt);
        
        scanner.close();
    }
}