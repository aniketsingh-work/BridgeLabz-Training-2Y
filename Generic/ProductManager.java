class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager", "Manages product development lifecycle");
    }
    
    @Override
    public void screenResume() {
        System.out.println("Screening resume for " + roleName + " position:");
        System.out.println("- Checking product strategy experience");
        System.out.println("- Evaluating market analysis skills");
        System.out.println("- Assessing stakeholder management capabilities");
    }
}