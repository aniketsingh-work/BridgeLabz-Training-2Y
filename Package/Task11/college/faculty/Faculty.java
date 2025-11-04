package college.faculty;

public class Faculty {
    private String name;
    private String department;
    private String subject;
    private int experience; // in years
    
    public Faculty(String name, String department, String subject, int experience) {
        this.name = name;
        this.department = department;
        this.subject = subject;
        this.experience = experience;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public int getExperience() {
        return experience;
    }
    
    public void displayFacultyInfo() {
        System.out.println("Faculty Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Subject: " + subject);
        System.out.println("Experience: " + experience + " years");
    }
}