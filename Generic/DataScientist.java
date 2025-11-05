class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist", "Analyzes data to extract insights and patterns");
    }
    
    @Override
    public void screenResume() {
        System.out.println("Screening resume for " + roleName + " position:");
        System.out.println("- Checking statistical analysis skills");
        System.out.println("- Evaluating machine learning experience");
        System.out.println("- Assessing data visualization capabilities");
    }
}