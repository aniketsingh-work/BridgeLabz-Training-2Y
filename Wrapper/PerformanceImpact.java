import java.util.ArrayList;

public class PerformanceImpact {
    public static void main(String[] args) {
        int size = 100000;
        
        // Benchmark ArrayList<Integer>
        long startTime = System.nanoTime();
        ArrayList<Integer> wrapperList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            wrapperList.add(i); // auto-boxing
        }
        
        long sumWrapper = 0;
        for (Integer num : wrapperList) {
            sumWrapper += num; // auto-unboxing
        }
        long wrapperTime = System.nanoTime() - startTime;
        
        // Benchmark int[]
        startTime = System.nanoTime();
        int[] primitiveArray = new int[size];
        for (int i = 0; i < size; i++) {
            primitiveArray[i] = i;
        }
        
        long sumPrimitive = 0;
        for (int num : primitiveArray) {
            sumPrimitive += num;
        }
        long primitiveTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList<Integer> time: " + wrapperTime / 1_000_000.0 + " ms");
        System.out.println("int[] time: " + primitiveTime / 1_000_000.0 + " ms");
        System.out.println("ArrayList<Integer> is " + (wrapperTime / (double) primitiveTime) + "x slower");
    }
}