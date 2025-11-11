import java.util.*;

public class VotingCount {
    private Map<String, Integer> votes;
    
    public VotingCount() {
        votes = new HashMap<>();
    }
    
    public void castVote(String candidate) {
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
    }
    
    public String getWinner() {
        String winner = null;
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }
    
    public void printResults() {
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }
    
    public static void main(String[] args) {
        VotingCount election = new VotingCount();
        String[] candidates = {"Alice", "Bob", "Charlie"};
        
        // Simulate 10 votes
        String[] votes = {"Alice", "Bob", "Alice", "Charlie", "Alice", "Bob", "Bob", "Alice", "Charlie", "Alice"};
        for (String vote : votes) {
            election.castVote(vote);
        }
        
        System.out.println("Election Results:");
        election.printResults();
        System.out.println("Winner: " + election.getWinner());
    }
}