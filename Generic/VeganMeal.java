class VeganMeal implements MealPlan {
    private String mealType;
    
    public VeganMeal() {
        this.mealType = "Vegan";
    }
    
    @Override
    public String getMealType() {
        return mealType;
    }
    
    @Override
    public void generateMealPlan() {
        System.out.println("Generating Vegan Meal Plan:");
        System.out.println("- Breakfast: Smoothie bowl with chia seeds");
        System.out.println("- Lunch: Chickpea and vegetable wrap");
        System.out.println("- Dinner: Mushroom risotto with nutritional yeast");
    }
}