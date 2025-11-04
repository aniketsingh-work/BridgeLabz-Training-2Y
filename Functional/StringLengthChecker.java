import java.util.*;
import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {
        // Sample messages to check
        List<String> messages = Arrays.asList(
            "Hello World",
            "This is a short message",
            "This is a considerably longer message that might exceed our character limit for certain platforms",
            "Hi",
            "A message that is exactly at the limit of fifty characters for testing purposes",
            "Another example message to demonstrate the functionality of our string length checker",
            "SMS",
            "This is a very long message that definitely exceeds any reasonable character limit and should trigger multiple alerts for different thresholds"
        );
        
        System.out.println("String Length Checker System:");
        System.out.println("=============================");
        
        // Using Function<String, Integer> to get string length
        Function<String, Integer> getLength = String::length;
        
        // Print all messages with their lengths
        System.out.println("Messages with lengths:");
        messages.forEach(msg -> 
            System.out.println("Message: \"" + msg + "\" | Length: " + getLength.apply(msg) + " characters")
        );
        System.out.println();
        
        // Check messages against different character limits
        int smsLimit = 160;
        int socialMediaLimit = 280;
        int customLimit = 50;
        
        System.out.println("Checking against SMS limit (" + smsLimit + " characters):");
        checkMessagesWithLimit(messages, smsLimit, getLength, "SMS");
        System.out.println();
        
        System.out.println("Checking against Social Media limit (" + socialMediaLimit + " characters):");
        checkMessagesWithLimit(messages, socialMediaLimit, getLength, "Social Media");
        System.out.println();
        
        System.out.println("Checking against Custom limit (" + customLimit + " characters):");
        checkMessagesWithLimit(messages, customLimit, getLength, "Custom");
        System.out.println();
        
        // Using Function to transform messages based on length
        Function<String, String> addLengthPrefix = msg -> getLength.apply(msg) + ": " + msg;
        
        System.out.println("Messages with length prefix:");
        messages.stream()
                .map(addLengthPrefix)
                .forEach(System.out::println);
        System.out.println();
        
        // Using Function to categorize messages by length
        Function<String, String> categorizeByLength = msg -> {
            int length = getLength.apply(msg);
            if (length < 10) return "Very Short";
            else if (length < 20) return "Short";
            else if (length < 50) return "Medium";
            else if (length < 100) return "Long";
            else return "Very Long";
        };
        
        System.out.println("Messages categorized by length:");
        messages.forEach(msg -> 
            System.out.println("\"" + msg + "\" -> " + categorizeByLength.apply(msg) + 
                             " (" + getLength.apply(msg) + " chars)")
        );
        System.out.println();
        
        // Using Function composition
        Function<String, Boolean> isTooLongForSMS = getLength.andThen(len -> len > 160);
        Function<String, Boolean> isWithinSmsLimit = isTooLongForSMS.andThen(result -> !result);
        
        System.out.println("SMS-appropriate messages:");
        messages.stream()
                .filter(msg -> isWithinSmsLimit.apply(msg))
                .forEach(msg -> System.out.println("✓ \"" + msg + "\" (" + getLength.apply(msg) + " chars)"));
        System.out.println();
        
        System.out.println("Messages too long for SMS:");
        messages.stream()
                .filter(msg -> isTooLongForSMS.apply(msg))
                .forEach(msg -> System.out.println("✗ \"" + msg + "\" (" + getLength.apply(msg) + " chars)"));
        
        System.out.println("\nUsing Function functional interface allows for flexible string length");
        System.out.println("operations with method references and function composition.");
    }
    
    // Method that accepts a function to check message length against a limit
    public static void checkMessagesWithLimit(List<String> messages, 
                                             int limit, 
                                             Function<String, Integer> lengthFunction,
                                             String platformName) {
        messages.stream()
                .filter(msg -> lengthFunction.apply(msg) > limit)
                .forEach(msg -> System.out.println("ALERT: Message exceeds " + platformName + 
                                                 " limit (" + limit + " chars): \"" + 
                                                 msg.substring(0, Math.min(30, msg.length())) + 
                                                 (msg.length() > 30 ? "..." : "") + "\""));
        
        long count = messages.stream()
                .filter(msg -> lengthFunction.apply(msg) > limit)
                .count();
        
        if (count == 0) {
            System.out.println("All messages are within " + platformName + " limit.");
        }
    }
}