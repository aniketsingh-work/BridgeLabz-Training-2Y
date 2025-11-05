public class InputConversionUtility {
    public static void main(String[] args) {
        String[] testInputs = {"123", "abc", "45.6", "0"};
        for (String input : testInputs) {
            int result = safeParseInt(input);
            System.out.println("safeParseInt(\"" + input + "\"): " + result);
        }
    }
    
    public static int safeParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}