public class EqualityChecker {
    public static <T> boolean isEqual(T a, T b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }
    
    public static void main(String[] args) {
        // Test with integers
        System.out.println("5 equals 5: " + isEqual(5, 5));
        System.out.println("5 equals 10: " + isEqual(5, 10));
        
        // Test with strings
        System.out.println("\"hello\" equals \"hello\": " + isEqual("hello", "hello"));
        System.out.println("\"hello\" equals \"world\": " + isEqual("hello", "world"));
        
        // Test with doubles
        System.out.println("3.14 equals 3.14: " + isEqual(3.14, 3.14));
        System.out.println("3.14 equals 2.71: " + isEqual(3.14, 2.71));
    }
}