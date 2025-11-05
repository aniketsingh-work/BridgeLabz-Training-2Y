public class MaximumFinder {
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }
    
    public static void main(String[] args) {
        // Test with integers
        System.out.println("Maximum of 3, 7, 5: " + maximum(3, 7, 5));
        
        // Test with doubles
        System.out.println("Maximum of 3.14, 2.71, 1.41: " + maximum(3.14, 2.71, 1.41));
        
        // Test with strings
        System.out.println("Maximum of \"apple\", \"banana\", \"cherry\": " + maximum("apple", "banana", "cherry"));
    }
}