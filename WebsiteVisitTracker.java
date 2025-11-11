import java.util.*;

public class WebsiteVisitTracker {
    private Map<String, Integer> pageVisits;
    
    public WebsiteVisitTracker() {
        pageVisits = new HashMap<>();
    }
    
    public void visitPage(String pageName) {
        pageVisits.put(pageName, pageVisits.getOrDefault(pageName, 0) + 1);
    }
    
    public void printPagesByVisitCount() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(pageVisits.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public String getPageWithMostVisits() {
        String mostVisitedPage = null;
        int maxVisits = 0;
        
        for (Map.Entry<String, Integer> entry : pageVisits.entrySet()) {
            if (entry.getValue() > maxVisits) {
                maxVisits = entry.getValue();
                mostVisitedPage = entry.getKey();
            }
        }
        
        return mostVisitedPage;
    }
    
    public static void main(String[] args) {
        WebsiteVisitTracker tracker = new WebsiteVisitTracker();
        String[] visits = {"home", "about", "products", "home", "products", "contact", "home", "about", "home", "products"};
        
        for (String page : visits) {
            tracker.visitPage(page);
        }
        
        System.out.println("Pages by visit count:");
        tracker.printPagesByVisitCount();
        
        System.out.println("Most visited page: " + tracker.getPageWithMostVisits());
    }
}