import java.util.*;

public class StudentMarksReport {
    public static void main(String[] args) {
        String[] inputs = {"85", "95", "88", "invalid", "null", "92"};
        ArrayList<Integer> validMarks = new ArrayList<>();
        
        for (String input : inputs) {
            if (!input.equals("null") && !input.equals("invalid")) {
                try {
                    int mark = Integer.parseInt(input);
                    validMarks.add(mark);
                } catch (NumberFormatException e) {
                    // Ignore invalid entries
                }
            }
        }
        
        double avgMarks = validMarks.isEmpty() ? 0 : 
                         validMarks.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average of valid marks: " + avgMarks);
    }
}