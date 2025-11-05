import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCopier {
    public static void copyList(List<? super Number> dest, List<? extends Number> src) {
        for (Number number : src) {
            dest.add(number);
        }
    }
    
    public static void main(String[] args) {
        // Create source lists
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        // Create destination list that can hold any Number
        List<Number> numberList = new ArrayList<>();
        
        // Copy integers to the number list
        System.out.println("Before copying integers: " + numberList);
        copyList(numberList, intList);
        System.out.println("After copying integers: " + numberList);
        
        // Copy doubles to the number list
        copyList(numberList, doubleList);
        System.out.println("After copying doubles: " + numberList);
        
        // Create another destination list of Object (which can hold Numbers as well)
        List<Object> objectList = new ArrayList<>();
        copyList(objectList, intList);
        System.out.println("Copy integers to Object list: " + objectList);
    }
}