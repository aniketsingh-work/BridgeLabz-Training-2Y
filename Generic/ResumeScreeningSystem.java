import java.util.ArrayList;
import java.util.List;

public class ResumeScreeningSystem {
    // Wildcard method to handle multiple job roles in the screening pipeline
    public static void screenResumes(List<? extends JobRole> jobRoles) {
        System.out.println("Starting AI-driven resume screening for multiple job roles:");
        for (JobRole role : jobRoles) {
            System.out.println("\nScreening resumes for " + role.getRoleName() + " position:");
            role.screenResume();
        }
        System.out.println("\nAll resume screenings completed.\n");
    }
    
    public static void main(String[] args) {
        // Create resumes for different job roles
        Resume<SoftwareEngineer> swEngResume = new Resume<>("John Doe", "john.doe@email.com", new SoftwareEngineer());
        Resume<DataScientist> dataSciResume = new Resume<>("Jane Smith", "jane.smith@email.com", new DataScientist());
        Resume<ProductManager> pmResume = new Resume<>("Robert Johnson", "robert.johnson@email.com", new ProductManager());
        
        // Process individual resumes
        System.out.println("=== Individual Resume Processing ===");
        swEngResume.processResume();
        dataSciResume.processResume();
        pmResume.processResume();
        
        // Create a list of job roles to demonstrate wildcard usage
        List<JobRole> jobRoles = new ArrayList<>();
        jobRoles.add(new SoftwareEngineer());
        jobRoles.add(new DataScientist());
        jobRoles.add(new ProductManager());
        
        // Screen all resumes using the wildcard method
        System.out.println("=== Batch Resume Screening ===");
        screenResumes(jobRoles);
        
        // Demonstrate creating and processing multiple resumes
        System.out.println("=== Multiple Resumes for Same Role ===");
        Resume<SoftwareEngineer> resume1 = new Resume<>("Alice Brown", "alice.brown@email.com", new SoftwareEngineer());
        Resume<SoftwareEngineer> resume2 = new Resume<>("Charlie Wilson", "charlie.wilson@email.com", new SoftwareEngineer());
        
        resume1.processResume();
        resume2.processResume();
    }
}