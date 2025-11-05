class ResearchCourse extends CourseType {
    public ResearchCourse(String courseName) {
        super(courseName);
    }
    
    @Override
    public void conductEvaluation() {
        System.out.println("Evaluating research projects for " + courseName);
    }
}