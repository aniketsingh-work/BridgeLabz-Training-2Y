class AssignmentCourse extends CourseType {
    public AssignmentCourse(String courseName) {
        super(courseName);
    }
    
    @Override
    public void conductEvaluation() {
        System.out.println("Collecting and grading assignments for " + courseName);
    }
}