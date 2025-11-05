class Meal<T extends MealPlan> {
    private T mealPlan;
    private String userName;
    
    public Meal(T mealPlan, String userName) {
        this.mealPlan = mealPlan;
        this.userName = userName;
    }
    
    public T getMealPlan() {
        return mealPlan;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void displayMealPlan() {
        System.out.println("Personalized meal plan for " + userName + " (" + mealPlan.getMealType() + "):");
        mealPlan.generateMealPlan();
    }
}