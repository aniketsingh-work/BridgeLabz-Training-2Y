import java.util.ArrayList;

public class AutoBoxingAutoUnboxing {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10); // auto-boxing: int to Integer
        numbers.add(15); // auto-boxing
        numbers.add(20); // auto-boxing
        numbers.add(5);  // auto-boxing
        numbers.add(5);  // auto-boxing
        
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // auto-unboxing: Integer to int
        }
        
        System.out.println("Sum of numbers = " + sum);
    }
}