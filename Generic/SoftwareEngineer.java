class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer", "Develops and maintains software applications");
    }
    
    @Override
    public void screenResume() {
        System.out.println("Screening resume for " + roleName + " position:");
        System.out.println("- Checking programming language proficiency");
        System.out.println("- Evaluating software development experience");
        System.out.println("- Assessing problem-solving skills");
    }
}