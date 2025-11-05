public class WrapperToPrimitiveConversion {
    public static void main(String[] args) {
        Double doubleObj = 45.67;
        double primitiveDouble = doubleObj.doubleValue();
        int intFromDouble = (int) doubleObj.doubleValue(); // casting
        
        System.out.println("Original Double object: " + doubleObj);
        System.out.println("Converted to double: " + primitiveDouble);
        System.out.println("Converted to int (with casting): " + intFromDouble);
    }
}