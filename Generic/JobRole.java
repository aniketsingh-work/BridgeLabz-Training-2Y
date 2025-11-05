abstract class JobRole {
    protected String roleName;
    protected String description;
    
    public JobRole(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public abstract void screenResume();
}