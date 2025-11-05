class VegetarianMeal implements MealPlan {
    private String mealType;
    
    public VegetarianMeal() {
        this.mealType = "Vegetarian";
    }
    
    @Override
    public String getMealType() {
        return mealType;
    }
    
    @Override
    public void generateMealPlan() {
        System.out.println("Generating Vegetarian Meal Plan:");
        System.out.println("- Breakfast: Oatmeal with fruits");
        System.out.println("- Lunch: Quinoa salad with vegetables");
        System.out.println("- Dinner: Lentil curry with rice");
    }
}