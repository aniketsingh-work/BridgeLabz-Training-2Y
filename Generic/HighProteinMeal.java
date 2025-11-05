class HighProteinMeal implements MealPlan {
    private String mealType;
    
    public HighProteinMeal() {
        this.mealType = "High-Protein";
    }
    
    @Override
    public String getMealType() {
        return mealType;
    }
    
    @Override
    public void generateMealPlan() {
        System.out.println("Generating High-Protein Meal Plan:");
        System.out.println("- Breakfast: Greek yogurt with nuts and berries");
        System.out.println("- Lunch: Grilled chicken breast with quinoa");
        System.out.println("- Dinner: Lean beef with sweet potato and broccoli");
    }
}