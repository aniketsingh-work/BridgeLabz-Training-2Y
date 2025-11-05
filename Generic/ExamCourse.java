class ExamCourse extends CourseType {
    public ExamCourse(String courseName) {
        super(courseName);
    }
    
    @Override
    public void conductEvaluation() {
        System.out.println("Conducting final examination for " + courseName);
    }
}