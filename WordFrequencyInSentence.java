import java.util.*;

public class WordFrequencyInSentence {
    public static Map<String, Integer> countWordFrequency(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = sentence.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " ").split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
    
    public static void main(String[] args) {
        String sentence = "Java is fun and Java is powerful";
        Map<String, Integer> result = countWordFrequency(sentence);
        
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}