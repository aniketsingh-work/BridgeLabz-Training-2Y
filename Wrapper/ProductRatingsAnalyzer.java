import java.util.*;

public class ProductRatingsAnalyzer {
    public static void main(String[] args) {
        int[] primitiveRatings = {4, 5, 3};
        ArrayList<Integer> wrapperRatings = new ArrayList<>();
        wrapperRatings.add(5);
        wrapperRatings.add(4);
        wrapperRatings.add(null); // null rating to test handling
        wrapperRatings.add(3);
        
        ArrayList<Integer> allRatings = new ArrayList<>();
        
        // Add primitive ratings (auto-boxing)
        for (int rating : primitiveRatings) {
            allRatings.add(rating);
        }
        
        // Add wrapper ratings
        for (Integer rating : wrapperRatings) {
            if (rating != null) { // Handle nulls
                allRatings.add(rating);
            }
        }
        
        double avgRating = calculateAverageRating(allRatings);
        System.out.println("Average rating: " + avgRating);
    }
    
    public static double calculateAverageRating(ArrayList<Integer> ratings) {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        
        int sum = 0;
        for (Integer rating : ratings) {
            if (rating != null) {
                sum += rating; // auto-unboxing
            }
        }
        
        return (double) sum / ratings.size();
    }
}