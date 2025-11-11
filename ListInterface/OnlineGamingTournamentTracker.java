import java.util.*;

public class OnlineGamingTournamentTracker {
    
    static class Player {
        private String playerId;
        private String playerName;
        private int score;
        
        public Player(String playerId, String playerName, int score) {
            this.playerId = playerId;
            this.playerName = playerName;
            this.score = score;
        }
        
        // Getters and setters
        public String getPlayerId() { return playerId; }
        public String getPlayerName() { return playerName; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
        
        @Override
        public String toString() {
            return playerName + "(ID:" + playerId + ", Score:" + score + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Player player = (Player) obj;
            return Objects.equals(playerId, player.playerId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(playerId);
        }
    }
    
    static class Match {
        private String matchId;
        private Player player1;
        private Player player2;
        private String status; // SCHEDULED, IN_PROGRESS, COMPLETED
        
        public Match(String matchId, Player player1, Player player2) {
            this.matchId = matchId;
            this.player1 = player1;
            this.player2 = player2;
            this.status = "SCHEDULED";
        }
        
        // Getters
        public String getMatchId() { return matchId; }
        public Player getPlayer1() { return player1; }
        public Player getPlayer2() { return player2; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        @Override
        public String toString() {
            return "Match{" + matchId + ": " + player1.getPlayerName() + " vs " + 
                   player2.getPlayerName() + ", Status:" + status + "}";
        }
    }
    
    static class Result {
        private String matchId;
        private Player winner;
        private Player loser;
        private int winnerScore;
        private int loserScore;
        
        public Result(String matchId, Player winner, Player loser, int winnerScore, int loserScore) {
            this.matchId = matchId;
            this.winner = winner;
            this.loser = loser;
            this.winnerScore = winnerScore;
            this.loserScore = loserScore;
        }
        
        // Getters
        public String getMatchId() { return matchId; }
        public Player getWinner() { return winner; }
        public Player getLoser() { return loser; }
        public int getWinnerScore() { return winnerScore; }
        public int getLoserScore() { return loserScore; }
        
        @Override
        public String toString() {
            return "Result{Match:" + matchId + ", Winner:" + winner.getPlayerName() + 
                   "(" + winnerScore + ") vs " + loser.getPlayerName() + "(" + loserScore + ")}";
        }
    }
    
    // Use Set to register players (no duplicates)
    private Set<Player> registeredPlayers;
    
    // Use Queue to store upcoming matches
    private Queue<Match> upcomingMatches;
    
    // Use List to store match outcomes
    private List<Result> matchResults;
    
    // Use TreeSet to display rankings in sorted order
    private TreeSet<Player> playerRankings;
    
    public OnlineGamingTournamentTracker() {
        this.registeredPlayers = new HashSet<>();
        this.upcomingMatches = new LinkedList<>();
        this.matchResults = new ArrayList<>();
        // TreeSet with custom comparator for descending score order
        this.playerRankings = new TreeSet<>((p1, p2) -> {
            // Compare scores in descending order (higher score first)
            int scoreComparison = Integer.compare(p2.getScore(), p1.getScore());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            // If scores are equal, compare by player name
            return p1.getPlayerName().compareTo(p2.getPlayerName());
        });
    }
    
    // 1. Register players and ensure uniqueness
    public boolean registerPlayer(Player player) {
        if (registeredPlayers.contains(player)) {
            System.out.println("Player " + player.getPlayerName() + " already registered!");
            return false;
        }
        
        registeredPlayers.add(player);
        playerRankings.add(player);
        System.out.println("Player " + player.getPlayerName() + " registered successfully.");
        return true;
    }
    
    // 2. Schedule matches and process them one by one
    public void scheduleMatch(Player player1, Player player2) {
        String matchId = "MATCH" + System.currentTimeMillis();
        Match match = new Match(matchId, player1, player2);
        upcomingMatches.add(match);
        System.out.println("Scheduled match: " + match);
    }
    
    public boolean processNextMatch() {
        if (upcomingMatches.isEmpty()) {
            System.out.println("No upcoming matches to process");
            return false;
        }
        
        Match match = upcomingMatches.remove();
        match.setStatus("IN_PROGRESS");
        System.out.println("Starting match: " + match.getMatchId());
        
        // Simulate match outcome - random winner with random scores
        Random random = new Random();
        Player winner, loser;
        int winnerScore, loserScore;
        
        if (random.nextBoolean()) {
            winner = match.getPlayer1();
            loser = match.getPlayer2();
        } else {
            winner = match.getPlayer2();
            loser = match.getPlayer1();
        }
        
        // Generate random scores (higher chance for the winner)
        winnerScore = 10 + random.nextInt(15); // 10-24
        loserScore = 5 + random.nextInt(10);   // 5-14 (but could be higher than winner if we want)
        
        // Ensure winner has higher score
        if (winnerScore <= loserScore) {
            int temp = winnerScore;
            winnerScore = loserScore + 1;
            loserScore = temp;
        }
        
        // Update player scores (add points to winner)
        winner.setScore(winner.getScore() + winnerScore);
        loser.setScore(loser.getScore() + loserScore);
        
        // Create result record
        Result result = new Result(match.getMatchId(), winner, loser, winnerScore, loserScore);
        matchResults.add(result);
        match.setStatus("COMPLETED");
        
        System.out.println("Match completed: " + result);
        
        // Update rankings with new scores
        playerRankings.remove(winner);
        playerRankings.remove(loser);
        playerRankings.add(winner);
        playerRankings.add(loser);
        
        return true;
    }
    
    // 3. Record and store match results
    public void recordResult(String matchId, Player winner, Player loser, int winnerScore, int loserScore) {
        Result result = new Result(matchId, winner, loser, winnerScore, loserScore);
        matchResults.add(result);
    }
    
    // 4. Display a live leaderboard (sorted descending by score)
    public void displayLeaderboard() {
        System.out.println("=== LIVE LEADERBOARD ===");
        int rank = 1;
        for (Player player : playerRankings) {
            System.out.println(rank + ". " + player);
            rank++;
        }
    }
    
    // Getters
    public Set<Player> getRegisteredPlayers() {
        return new HashSet<>(registeredPlayers);
    }
    
    public Queue<Match> getUpcomingMatches() {
        return new LinkedList<>(upcomingMatches);
    }
    
    public List<Result> getMatchResults() {
        return new ArrayList<>(matchResults);
    }
    
    public TreeSet<Player> getPlayerRankings() {
        return new TreeSet<>(playerRankings);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Online Gaming Tournament Tracker ===");
        
        OnlineGamingTournamentTracker tournament = new OnlineGamingTournamentTracker();
        
        // Register players
        Player player1 = new Player("P001", "Alice", 0);
        Player player2 = new Player("P002", "Bob", 0);
        Player player3 = new Player("P003", "Charlie", 0);
        Player player4 = new Player("P004", "Diana", 0);
        
        tournament.registerPlayer(player1);
        tournament.registerPlayer(player2);
        tournament.registerPlayer(player3);
        tournament.registerPlayer(player4);
        
        // Try to register a duplicate player
        tournament.registerPlayer(new Player("P001", "Alice Duplicate", 0));
        
        System.out.println("\n1. Registered players:");
        for (Player player : tournament.getRegisteredPlayers()) {
            System.out.println(" " + player);
        }
        
        // Schedule some matches
        tournament.scheduleMatch(player1, player2);
        tournament.scheduleMatch(player3, player4);
        tournament.scheduleMatch(player1, player3);
        
        System.out.println("\n2. Upcoming matches:");
        Queue<Match> matches = tournament.getUpcomingMatches();
        while (!matches.isEmpty()) {
            System.out.println(" " + matches.remove());
        }
        
        System.out.println("\n3. Initial leaderboard:");
        tournament.displayLeaderboard();
        
        System.out.println("\n4. Processing matches:");
        while (tournament.getUpcomingMatches().size() > 0) {
            tournament.processNextMatch();
        }
        
        System.out.println("\n5. Final leaderboard after matches:");
        tournament.displayLeaderboard();
        
        System.out.println("\n6. Match results:");
        for (Result result : tournament.getMatchResults()) {
            System.out.println(" " + result);
        }
    }
}