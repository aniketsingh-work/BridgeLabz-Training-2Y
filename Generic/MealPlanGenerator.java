public class MealPlanGenerator {
    // Generic method to validate and generate a personalized meal plan dynamically
    public static <T extends MealPlan> void generatePersonalizedMealPlan(T mealPlan, String userName) {
        System.out.println("Validating meal plan for " + userName + "...");
        System.out.println("Meal plan type: " + mealPlan.getMealType());
        System.out.println("Validation successful. Generating personalized meal plan:");
        mealPlan.generateMealPlan();
        System.out.println("Enjoy your personalized " + mealPlan.getMealType() + " meal plan, " + userName + "!");
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Generate different meal plans for different users
        System.out.println("=== Personalized Meal Plan Generator ===\n");
        
        // Generate vegetarian meal plan for Alice
        generatePersonalizedMealPlan(new VegetarianMeal(), "Alice");
        
        // Generate vegan meal plan for Bob
        generatePersonalizedMealPlan(new VeganMeal(), "Bob");
        
        // Generate keto meal plan for Charlie
        generatePersonalizedMealPlan(new KetoMeal(), "Charlie");
        
        // Generate high-protein meal plan for Diana
        generatePersonalizedMealPlan(new HighProteinMeal(), "Diana");
        
        // Demonstrate using the Meal class
        System.out.println("=== Using Meal class ===");
        Meal<VegetarianMeal> aliceMeal = new Meal<>(new VegetarianMeal(), "Alice");
        aliceMeal.displayMealPlan();
    }
}