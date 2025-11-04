interface SecurityUtils {
    // Static method to check password strength
    static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    
    // Static method to provide password strength feedback
    static String getPasswordStrengthFeedback(String password) {
        if (!isPasswordStrong(password)) {
            StringBuilder feedback = new StringBuilder("Password is weak. It should contain: ");
            
            if (password == null || password.length() < 8) {
                feedback.append("- At least 8 characters; ");
            }
            
            if (!hasUpperCase(password)) {
                feedback.append("- At least one uppercase letter; ");
            }
            
            if (!hasLowerCase(password)) {
                feedback.append("- At least one lowercase letter; ");
            }
            
            if (!hasDigit(password)) {
                feedback.append("- At least one digit; ");
            }
            
            if (!hasSpecialCharacter(password)) {
                feedback.append("- At least one special character; ");
            }
            
            return feedback.toString();
        }
        
        return "Password is strong!";
    }
    
    // Helper static methods
    private static boolean hasUpperCase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean hasLowerCase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean hasDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean hasSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }
}

public class PasswordStrengthValidator {
    public static void main(String[] args) {
        System.out.println("Password Strength Validator:");
        System.out.println("============================");
        
        // Test passwords
        String[] testPasswords = {
            "weak",           // Too short
            "password123",    // No uppercase or special character
            "Password123",    // No special character
            "Password123!",   // Strong password
            "simple",         // Too short and simple
            "MySecureP@ss9",  // Strong password
            "12345678",       // No letters
            "ABCDEFGH",       // No lowercase, digits, or special chars
            "Abcdefgh!",      // No digits
            "MyP@ssw0rd"      // Strong password
        };
        
        // Check each password using the static method from the interface
        for (String password : testPasswords) {
            boolean isStrong = SecurityUtils.isPasswordStrong(password);
            String feedback = SecurityUtils.getPasswordStrengthFeedback(password);
            
            System.out.println("Password: " + password);
            System.out.println("Is Strong: " + isStrong);
            System.out.println("Feedback: " + feedback);
            System.out.println();
        }
        
        // Demonstrate using the static methods directly
        System.out.println("Direct usage of static methods:");
        System.out.println("isPasswordStrong(\"Hello123!\"): " + 
                          SecurityUtils.isPasswordStrong("Hello123!"));
        System.out.println("isPasswordStrong(\"weak\"): " + 
                          SecurityUtils.isPasswordStrong("weak"));
        System.out.println();
        
        // Real-world scenario: User registration
        System.out.println("Real-world scenario - User Registration:");
        System.out.println("=======================================");
        
        String[] userPasswords = {"user123", "SecurePass!2023", "123456", "MyStrongP@ss1"};
        
        for (String userPassword : userPasswords) {
            if (SecurityUtils.isPasswordStrong(userPassword)) {
                System.out.println("✓ Password accepted for user: *** (password meets security requirements)");
            } else {
                System.out.println("✗ Password rejected for user: " + SecurityUtils.getPasswordStrengthFeedback(userPassword));
            }
        }
        
        System.out.println("\nStatic methods in interfaces provide centralized security utilities");
        System.out.println("that can be used without implementing the interface.");
    }
}