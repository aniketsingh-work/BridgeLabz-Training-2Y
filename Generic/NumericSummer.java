import java.util.Arrays;
import java.util.List;

public class NumericSummer {
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    public static void main(String[] args) {
        // Test with List<Integer>
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of integers: " + sumNumbers(intList));
        
        // Test with List<Double>
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println("Sum of doubles: " + sumNumbers(doubleList));
        
        // Test with List<Float>
        List<Float> floatList = Arrays.asList(1.1f, 2.2f, 3.3f);
        System.out.println("Sum of floats: " + sumNumbers(floatList));
    }
}