public class UserInputValidation {
    public static void main(String[] args) {
        String[] ageInputs = {"20", "17", "abc", "18", "15"};
        for (String ageStr : ageInputs) {
            boolean isValid = isValidAge(ageStr);
            System.out.println("Age '" + ageStr + "' is valid and >= 18: " + isValid);
        }
    }
    
    public static boolean isValidAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}