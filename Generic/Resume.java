class Resume<T extends JobRole> {
    private String candidateName;
    private String email;
    private T jobRole;
    
    public Resume(String candidateName, String email, T jobRole) {
        this.candidateName = candidateName;
        this.email = email;
        this.jobRole = jobRole;
    }
    
    public String getCandidateName() {
        return candidateName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public T getJobRole() {
        return jobRole;
    }
    
    public void processResume() {
        System.out.println("Processing resume for: " + candidateName + " (" + email + ")");
        jobRole.screenResume();
        System.out.println("Resume processing completed for " + jobRole.getRoleName() + " position.\n");
    }
    
    @Override
    public String toString() {
        return "Resume for " + candidateName + " applying for " + jobRole.getRoleName() + " position";
    }
}