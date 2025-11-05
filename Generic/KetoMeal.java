class KetoMeal implements MealPlan {
    private String mealType;
    
    public KetoMeal() {
        this.mealType = "Keto";
    }
    
    @Override
    public String getMealType() {
        return mealType;
    }
    
    @Override
    public void generateMealPlan() {
        System.out.println("Generating Keto Meal Plan:");
        System.out.println("- Breakfast: Avocado and eggs");
        System.out.println("- Lunch: Grilled chicken with vegetables in olive oil");
        System.out.println("- Dinner: Salmon with butter and asparagus");
    }
}