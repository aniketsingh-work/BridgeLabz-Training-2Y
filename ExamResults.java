import java.util.*;

public class ExamResults {
    private Map<String, Map<String, Integer>> subjectScores;
    
    public ExamResults() {
        subjectScores = new HashMap<>();
    }
    
    public void addSubject(String subject) {
        subjectScores.put(subject, new HashMap<>());
    }
    
    public void addStudentScore(String subject, String studentName, int marks) {
        if (!subjectScores.containsKey(subject)) {
            addSubject(subject);
        }
        subjectScores.get(subject).put(studentName, marks);
    }
    
    public String getTopScorerInSubject(String subject) {
        if (!subjectScores.containsKey(subject)) {
            return null;
        }
        
        String topScorer = null;
        int highestMarks = Integer.MIN_VALUE;
        
        for (Map.Entry<String, Integer> entry : subjectScores.get(subject).entrySet()) {
            if (entry.getValue() > highestMarks) {
                highestMarks = entry.getValue();
                topScorer = entry.getKey();
            }
        }
        
        return topScorer;
    }
    
    public double getAverageScoreInSubject(String subject) {
        if (!subjectScores.containsKey(subject) || subjectScores.get(subject).isEmpty()) {
            return 0.0;
        }
        
        int total = 0;
        int count = 0;
        for (int score : subjectScores.get(subject).values()) {
            total += score;
            count++;
        }
        
        return (double) total / count;
    }
    
    public List<String> getSubjectsWithHighScorers() {
        List<String> subjectsWithHighScorers = new ArrayList<>();
        
        for (Map.Entry<String, Map<String, Integer>> subjectEntry : subjectScores.entrySet()) {
            String subject = subjectEntry.getKey();
            boolean hasHighScorer = false;
            
            for (int score : subjectEntry.getValue().values()) {
                if (score > 90) {
                    hasHighScorer = true;
                    break;
                }
            }
            
            if (hasHighScorer) {
                subjectsWithHighScorers.add(subject);
            }
        }
        
        return subjectsWithHighScorers;
    }
    
    public static void main(String[] args) {
        ExamResults results = new ExamResults();
        results.addStudentScore("Math", "Alice", 95);
        results.addStudentScore("Math", "Bob", 87);
        results.addStudentScore("Math", "Charlie", 92);
        
        results.addStudentScore("Science", "Alice", 89);
        results.addStudentScore("Science", "Bob", 94);
        results.addStudentScore("Science", "Charlie", 91);
        
        results.addStudentScore("English", "Alice", 85);
        results.addStudentScore("English", "Bob", 78);
        results.addStudentScore("English", "Charlie", 96);
        
        System.out.println("Top scorer in Math: " + results.getTopScorerInSubject("Math"));
        System.out.println("Average score in Science: " + results.getAverageScoreInSubject("Science"));
        System.out.println("Subjects with at least one student scoring above 90: " + results.getSubjectsWithHighScorers());
    }
}