public class GameScoreboard {
    public static void main(String[] args) {
        Integer[] scores = {100, null, 250, 150, null, 300};
        int notPlayed = 0;
        int totalValidScore = 0;
        
        for (Integer score : scores) {
            if (score == null) {
                notPlayed++;
            } else {
                totalValidScore += score; // auto-unboxing
            }
        }
        
        System.out.println("Players who haven't played: " + notPlayed);
        System.out.println("Total of valid scores: " + totalValidScore);
    }
}