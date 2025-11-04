package college.faculty;

public class Faculty {
    private String name;
    private String subject;
    
    public Faculty(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }
    
    public void displayName() {
        System.out.println("Faculty Name: " + name);
    }
    
    public void displaySubject() {
        System.out.println("Subject: " + subject);
    }
    
    public String getName() {
        return name;
    }
    
    public String getSubject() {
        return subject;
    }
}