import java.util.*;

public class EmployeeDataProcessing {
    public static void main(String[] args) {
        int[] ages = {25, 30, 35, 22, 40, 28};
        ArrayList<Integer> ageList = new ArrayList<>();
        
        for (int age : ages) {
            ageList.add(age); // auto-boxing
        }
        
        int youngest = Collections.min(ageList);
        int oldest = Collections.max(ageList);
        
        System.out.println("Youngest employee age: " + youngest);
        System.out.println("Oldest employee age: " + oldest);
    }
}