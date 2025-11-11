import java.util.*;

public class SportsTournamentScheduler {
    
    static class Team {
        private String teamId;
        private String teamName;
        private int points;
        private int matchesPlayed;
        private int wins;
        private int losses;
        private int draws;
        
        public Team(String teamId, String teamName) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.points = 0;
            this.matchesPlayed = 0;
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
        }
        
        // Getters and setters
        public String getTeamId() { return teamId; }
        public String getTeamName() { return teamName; }
        public int getPoints() { return points; }
        public void setPoints(int points) { this.points = points; }
        public int getMatchesPlayed() { return matchesPlayed; }
        public void setMatchesPlayed(int matchesPlayed) { this.matchesPlayed = matchesPlayed; }
        public int getWins() { return wins; }
        public void setWins(int wins) { this.wins = wins; }
        public int getLosses() { return losses; }
        public void setLosses(int losses) { this.losses = losses; }
        public int getDraws() { return draws; }
        public void setDraws(int draws) { this.draws = draws; }
        
        // Method to update stats after a match
        public void addWin() {
            this.wins++;
            this.points += 3;
            this.matchesPlayed++;
        }
        
        public void addLoss() {
            this.losses++;
            this.matchesPlayed++;
        }
        
        public void addDraw() {
            this.draws++;
            this.points += 1;
            this.matchesPlayed++;
        }
        
        @Override
        public String toString() {
            return teamName + "(ID:" + teamId + ", Points:" + points + 
                   ", W:" + wins + " L:" + losses + " D:" + draws + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Team team = (Team) obj;
            return Objects.equals(teamId, team.teamId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(teamId);
        }
    }
    
    static class Match {
        private String matchId;
        private Team team1;
        private Team team2;
        private String status; // SCHEDULED, IN_PROGRESS, COMPLETED
        private String venue;
        
        public Match(String matchId, Team team1, Team team2, String venue) {
            this.matchId = matchId;
            this.team1 = team1;
            this.team2 = team2;
            this.venue = venue;
            this.status = "SCHEDULED";
        }
        
        // Getters
        public String getMatchId() { return matchId; }
        public Team getTeam1() { return team1; }
        public Team getTeam2() { return team2; }
        public String getStatus() { return status; }
        public String getVenue() { return venue; }
        public void setStatus(String status) { this.status = status; }
        
        @Override
        public String toString() {
            return "Match{" + matchId + ": " + team1.getTeamName() + " vs " + 
                   team2.getTeamName() + " at " + venue + ", Status:" + status + "}";
        }
    }
    
    static class Result {
        private String matchId;
        private Team winningTeam;
        private Team losingTeam;
        private String resultType; // WIN, LOSS, DRAW
        private int winningTeamScore;
        private int losingTeamScore;
        
        public Result(String matchId, Team winningTeam, Team losingTeam, String resultType, 
                     int winningTeamScore, int losingTeamScore) {
            this.matchId = matchId;
            this.winningTeam = winningTeam;
            this.losingTeam = losingTeam;
            this.resultType = resultType;
            this.winningTeamScore = winningTeamScore;
            this.losingTeamScore = losingTeamScore;
        }
        
        // Getters
        public String getMatchId() { return matchId; }
        public Team getWinningTeam() { return winningTeam; }
        public Team getLosingTeam() { return losingTeam; }
        public String getResultType() { return resultType; }
        public int getWinningTeamScore() { return winningTeamScore; }
        public int getLosingTeamScore() { return losingTeamScore; }
        
        @Override
        public String toString() {
            if (resultType.equals("DRAW")) {
                return "Result{Match:" + matchId + ", Draw: " + winningTeam.getTeamName() + 
                       " " + winningTeamScore + " - " + losingTeamScore + " " + losingTeam.getTeamName() + "}";
            } else {
                return "Result{Match:" + matchId + ", Winner: " + winningTeam.getTeamName() + 
                       " " + winningTeamScore + " - " + losingTeamScore + " " + losingTeam.getTeamName() + "}";
            }
        }
    }
    
    // Use Set to register unique teams
    private Set<Team> registeredTeams;
    
    // Use Queue to schedule matches in order
    private Queue<Match> scheduledMatches;
    
    // Use List to store match outcomes
    private List<Result> matchResults;
    
    // Use TreeSet to display rankings based on points
    private TreeSet<Team> teamRankings;
    
    public SportsTournamentScheduler() {
        this.registeredTeams = new HashSet<>();
        this.scheduledMatches = new LinkedList<>();
        this.matchResults = new ArrayList<>();
        // TreeSet with custom comparator for descending points order
        this.teamRankings = new TreeSet<>((t1, t2) -> {
            // Compare points in descending order (higher points first)
            int pointsComparison = Integer.compare(t2.getPoints(), t1.getPoints());
            if (pointsComparison != 0) {
                return pointsComparison;
            }
            // If points are equal, compare by team name
            return t1.getTeamName().compareTo(t2.getTeamName());
        });
    }
    
    // 1. Register teams and schedule matches
    public boolean registerTeam(Team team) {
        if (registeredTeams.contains(team)) {
            System.out.println("Team " + team.getTeamName() + " already registered!");
            return false;
        }
        
        registeredTeams.add(team);
        teamRankings.add(team);
        System.out.println("Team registered: " + team.getTeamName());
        return true;
    }
    
    public void scheduleMatch(Team team1, Team team2, String venue) {
        if (!registeredTeams.contains(team1) || !registeredTeams.contains(team2)) {
            System.out.println("One or both teams not registered for match");
            return;
        }
        
        String matchId = "MATCH" + System.currentTimeMillis();
        Match match = new Match(matchId, team1, team2, venue);
        scheduledMatches.add(match);
        System.out.println("Scheduled match: " + match);
    }
    
    // 2. Process matches in the queue
    public boolean processNextMatch() {
        if (scheduledMatches.isEmpty()) {
            System.out.println("No scheduled matches to process");
            return false;
        }
        
        Match match = scheduledMatches.remove();
        match.setStatus("IN_PROGRESS");
        System.out.println("Starting match: " + match.getMatchId());
        
        // Simulate match outcome - random result with random scores
        Random random = new Random();
        Team winner = null, loser = null;
        String resultType = "";
        int team1Score, team2Score;
        
        // Generate random scores
        team1Score = random.nextInt(5); // 0-4 goals
        team2Score = random.nextInt(5); // 0-4 goals
        
        if (team1Score > team2Score) {
            winner = match.getTeam1();
            loser = match.getTeam2();
            resultType = "WIN";
        } else if (team2Score > team1Score) {
            winner = match.getTeam2();
            loser = match.getTeam1();
            resultType = "WIN";
        } else {
            // Draw
            resultType = "DRAW";
            match.getTeam1().addDraw();
            match.getTeam2().addDraw();
            
            // Update rankings with new stats
            teamRankings.remove(match.getTeam1());
            teamRankings.remove(match.getTeam2());
            teamRankings.add(match.getTeam1());
            teamRankings.add(match.getTeam2());
            
            // Create result record for draw
            Result result = new Result(match.getMatchId(), match.getTeam1(), match.getTeam2(), 
                                     "DRAW", team1Score, team2Score);
            matchResults.add(result);
            match.setStatus("COMPLETED");
            
            System.out.println("Match completed with draw: " + result);
            return true;
        }
        
        // Update team stats for win/loss
        winner.addWin();
        loser.addLoss();
        
        // Update rankings with new stats
        teamRankings.remove(winner);
        teamRankings.remove(loser);
        teamRankings.add(winner);
        teamRankings.add(loser);
        
        // Create result record
        Result result = new Result(match.getMatchId(), winner, loser, resultType, 
                                 Math.max(team1Score, team2Score), Math.min(team1Score, team2Score));
        matchResults.add(result);
        match.setStatus("COMPLETED");
        
        System.out.println("Match completed: " + result);
        return true;
    }
    
    // 3. Record results in the list
    public void recordResult(String matchId, Team winningTeam, Team losingTeam, 
                           String resultType, int winningScore, int losingScore) {
        Result result = new Result(matchId, winningTeam, losingTeam, resultType, 
                                 winningScore, losingScore);
        matchResults.add(result);
    }
    
    // 4. Display leaderboard using a sorted set
    public void displayLeaderboard() {
        System.out.println("=== TOURNAMENT LEADERBOARD ===");
        int rank = 1;
        for (Team team : teamRankings) {
            System.out.println(rank + ". " + team);
            rank++;
        }
    }
    
    // Getters
    public Set<Team> getRegisteredTeams() {
        return new HashSet<>(registeredTeams);
    }
    
    public Queue<Match> getScheduledMatches() {
        return new LinkedList<>(scheduledMatches);
    }
    
    public List<Result> getMatchResults() {
        return new ArrayList<>(matchResults);
    }
    
    public TreeSet<Team> getTeamRankings() {
        return new TreeSet<>(teamRankings);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sports Tournament Scheduler ===");
        
        SportsTournamentScheduler tournament = new SportsTournamentScheduler();
        
        // Register teams
        Team teamA = new Team("T001", "Lions");
        Team teamB = new Team("T002", "Tigers");
        Team teamC = new Team("T003", "Eagles");
        Team teamD = new Team("T004", "Sharks");
        
        tournament.registerTeam(teamA);
        tournament.registerTeam(teamB);
        tournament.registerTeam(teamC);
        tournament.registerTeam(teamD);
        
        // Try to register a duplicate team
        tournament.registerTeam(new Team("T001", "Duplicate Lions"));
        
        System.out.println("\n1. Registered teams: " + tournament.getRegisteredTeams().size());
        for (Team team : tournament.getRegisteredTeams()) {
            System.out.println(" " + team);
        }
        
        // Schedule matches
        tournament.scheduleMatch(teamA, teamB, "Stadium A");
        tournament.scheduleMatch(teamC, teamD, "Stadium B");
        tournament.scheduleMatch(teamA, teamC, "Stadium A");
        tournament.scheduleMatch(teamB, teamD, "Stadium B");
        
        System.out.println("\n2. Scheduled matches: " + tournament.getScheduledMatches().size());
        
        System.out.println("\n3. Initial leaderboard:");
        tournament.displayLeaderboard();
        
        System.out.println("\n4. Processing matches:");
        while (tournament.getScheduledMatches().size() > 0) {
            tournament.processNextMatch();
        }
        
        System.out.println("\n5. Final leaderboard after all matches:");
        tournament.displayLeaderboard();
        
        System.out.println("\n6. Match results:");
        for (Result result : tournament.getMatchResults()) {
            System.out.println(" " + result);
        }
    }
}