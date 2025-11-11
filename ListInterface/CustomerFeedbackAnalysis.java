import java.util.*;

public class CustomerFeedbackAnalysis {
    
    // Use List to store all feedback entries
    private List<String> allFeedbackEntries;
    
    // Use Set to filter out duplicate feedbacks
    private Set<String> uniqueFeedbacks;
    
    // Use Queue to process feedback in order
    private Queue<String> feedbackQueue;
    
    // Use Stack to track most recent feedbacks for quick review
    private Stack<String> recentFeedbacks;
    
    public CustomerFeedbackAnalysis() {
        this.allFeedbackEntries = new ArrayList<>();
        this.uniqueFeedbacks = new HashSet<>();
        this.feedbackQueue = new LinkedList<>();
        this.recentFeedbacks = new Stack<>();
    }
    
    // 1. Add feedback messages
    public void addFeedback(String feedback) {
        allFeedbackEntries.add(feedback);
        
        // Add to unique feedbacks (this automatically handles duplicates)
        if (uniqueFeedbacks.add(feedback)) {
            // Only add to queue and stack if it's unique
            feedbackQueue.add(feedback);
            recentFeedbacks.push(feedback);
            System.out.println("Added new feedback: " + feedback);
        } else {
            System.out.println("Duplicate feedback detected and filtered: " + feedback);
        }
    }
    
    // 2. Remove duplicates and process sequentially
    public void processFeedbacks() {
        System.out.println("Processing feedbacks in order:");
        int count = 0;
        while (!feedbackQueue.isEmpty()) {
            String feedback = feedbackQueue.remove();
            System.out.println("Processing feedback #" + (++count) + ": " + feedback);
            
            // Simulate processing time
            try {
                Thread.sleep(10); // Small delay to simulate processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    // 3. Display the last few feedbacks using the stack
    public void displayRecentFeedbacks(int count) {
        System.out.println("Displaying last " + count + " feedbacks:");
        
        // Create a temporary stack to preserve the original
        Stack<String> tempStack = new Stack<>();
        List<String> recentList = new ArrayList<>();
        
        // Get the recent feedbacks
        int itemsToGet = Math.min(count, recentFeedbacks.size());
        for (int i = 0; i < itemsToGet; i++) {
            if (!recentFeedbacks.isEmpty()) {
                String feedback = recentFeedbacks.pop();
                recentList.add(0, feedback); // Add to beginning to maintain order
                tempStack.push(feedback);
            }
        }
        
        // Restore the original stack
        while (!tempStack.isEmpty()) {
            recentFeedbacks.push(tempStack.pop());
        }
        
        // Display the feedbacks
        for (int i = 0; i < recentList.size(); i++) {
            System.out.println((i+1) + ". " + recentList.get(i));
        }
    }
    
    // Additional method to analyze feedback sentiment (simple implementation)
    public void analyzeFeedbackSentiment() {
        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;
        
        for (String feedback : uniqueFeedbacks) {
            String lowerFeedback = feedback.toLowerCase();
            
            if (lowerFeedback.contains("good") || lowerFeedback.contains("great") || 
                lowerFeedback.contains("excellent") || lowerFeedback.contains("love") || 
                lowerFeedback.contains("amazing") || lowerFeedback.contains("wonderful")) {
                positiveCount++;
            } else if (lowerFeedback.contains("bad") || lowerFeedback.contains("terrible") || 
                      lowerFeedback.contains("hate") || lowerFeedback.contains("awful") || 
                      lowerFeedback.contains("poor") || lowerFeedback.contains("disappoint")) {
                negativeCount++;
            } else {
                neutralCount++;
            }
        }
        
        System.out.println("Feedback Sentiment Analysis:");
        System.out.println("Positive: " + positiveCount);
        System.out.println("Negative: " + negativeCount);
        System.out.println("Neutral: " + neutralCount);
    }
    
    // Getters
    public List<String> getAllFeedbackEntries() {
        return new ArrayList<>(allFeedbackEntries);
    }
    
    public Set<String> getUniqueFeedbacks() {
        return new HashSet<>(uniqueFeedbacks);
    }
    
    public Queue<String> getFeedbackQueue() {
        return new LinkedList<>(feedbackQueue);
    }
    
    public Stack<String> getRecentFeedbacks() {
        return (Stack<String>) recentFeedbacks.clone();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Customer Feedback Analysis ===");
        
        CustomerFeedbackAnalysis feedbackSystem = new CustomerFeedbackAnalysis();
        
        // Add some feedback messages (including duplicates)
        feedbackSystem.addFeedback("The service was excellent and fast!");
        feedbackSystem.addFeedback("I love this product, it's amazing!");
        feedbackSystem.addFeedback("The delivery was late and disappointing");
        feedbackSystem.addFeedback("Great customer support, very helpful");
        feedbackSystem.addFeedback("The service was excellent and fast!"); // Duplicate
        feedbackSystem.addFeedback("Could be better, not satisfied with quality");
        feedbackSystem.addFeedback("I love this product, it's amazing!"); // Duplicate
        feedbackSystem.addFeedback("Fast shipping and good packaging");
        
        System.out.println("\n1. Total feedback entries: " + feedbackSystem.getAllFeedbackEntries().size());
        System.out.println("   Unique feedbacks: " + feedbackSystem.getUniqueFeedbacks().size());
        
        System.out.println("\n2. Recent feedbacks (last 3):");
        feedbackSystem.displayRecentFeedbacks(3);
        
        System.out.println("\n3. Processing all feedbacks in order:");
        feedbackSystem.processFeedbacks();
        
        System.out.println("\n4. Feedback sentiment analysis:");
        feedbackSystem.analyzeFeedbackSentiment();
    }
}