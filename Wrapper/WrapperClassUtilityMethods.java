public class WrapperClassUtilityMethods {
    public static void main(String[] args) {
        int parsedInt = Integer.parseInt("123");
        double parsedDouble = Double.parseDouble("3.14");
        boolean parsedBoolean = Boolean.parseBoolean("true");
        String binaryString = Integer.toBinaryString(10);
        boolean isDigit = Character.isDigit('5');
        char upperChar = Character.toUpperCase('a');
        
        System.out.println("Integer.parseInt(\"123\"): " + parsedInt);
        System.out.println("Double.parseDouble(\"3.14\"): " + parsedDouble);
        System.out.println("Boolean.parseBoolean(\"true\"): " + parsedBoolean);
        System.out.println("Integer.toBinaryString(10): " + binaryString);
        System.out.println("Character.isDigit('5'): " + isDigit);
        System.out.println("Character.toUpperCase('a'): " + upperChar);
    }
}