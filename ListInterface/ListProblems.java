import java.util.*;

public class ListProblems {
    
    // 1. Reverse a List - without using built-in reverse methods
    public static <T> void reverseArrayList(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            T temp = list.get(i);
            list.set(i, list.get(size - 1 - i));
            list.set(size - 1 - i, temp);
        }
    }
    
    public static <T> void reverseLinkedList(LinkedList<T> list) {
        int size = list.size();
        ListIterator<T> front = list.listIterator();
        ListIterator<T> back = list.listIterator(size);
        
        for (int i = 0; i < size / 2; i++) {
            T frontVal = front.next();
            back.previous();
            T backVal = back.previous();
            
            back.set(frontVal);
            front.set(backVal);
        }
    }
    
    // Alternative approach for LinkedList using recursion
    public static <T> void reverseLinkedListRecursive(LinkedList<T> list) {
        reverseLinkedListRecursiveHelper(list, 0, list.size() - 1);
    }
    
    private static <T> void reverseLinkedListRecursiveHelper(LinkedList<T> list, int start, int end) {
        if (start >= end) return;
        
        T startVal = list.get(start);
        T endVal = list.get(end);
        
        list.set(start, endVal);
        list.set(end, startVal);
        
        reverseLinkedListRecursiveHelper(list, start + 1, end - 1);
    }
    
    // 2. Find Frequency of Elements
    public static Map<String, Integer> findFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        for (String element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }
        
        return frequencyMap;
    }
    
    // 3. Rotate Elements in a List
    public static <T> void rotateList(List<T> list, int positions) {
        if (list.isEmpty() || positions == 0) return;
        
        int size = list.size();
        positions = positions % size; // Handle cases where positions > size
        
        // Split the list into two parts and rearrange
        List<T> rotated = new ArrayList<>();
        
        // Add elements from position 'positions' to the end
        for (int i = positions; i < size; i++) {
            rotated.add(list.get(i));
        }
        
        // Add elements from the beginning up to 'positions'
        for (int i = 0; i < positions; i++) {
            rotated.add(list.get(i));
        }
        
        // Clear original list and add rotated elements
        list.clear();
        list.addAll(rotated);
    }
    
    // Alternative approach using Collections.rotate
    public static <T> void rotateListAlternative(List<T> list, int positions) {
        if (list.isEmpty() || positions == 0) return;
        
        int size = list.size();
        positions = positions % size;
        
        // Use sublist operations to rotate
        List<T> sublist1 = new ArrayList<>(list.subList(positions, size));
        List<T> sublist2 = new ArrayList<>(list.subList(0, positions));
        
        list.clear();
        list.addAll(sublist1);
        list.addAll(sublist2);
    }
    
    // 4. Remove Duplicates While Preserving Order
    public static <T> List<T> removeDuplicatesPreserveOrder(List<T> list) {
        Set<T> seen = new LinkedHashSet<>();
        List<T> result = new ArrayList<>();
        
        for (T element : list) {
            if (seen.add(element)) { // add() returns false if element already exists
                result.add(element);
            }
        }
        
        return result;
    }
    
    // 5. Find the Nth Element from the End in LinkedList
    public static <T> T findNthFromEnd(LinkedList<T> list, int n) {
        if (n <= 0 || n > list.size()) {
            throw new IllegalArgumentException("Invalid n value: " + n);
        }
        
        ListIterator<T> forward = list.listIterator();
        ListIterator<T> backward = list.listIterator();
        
        // Move forward iterator n positions ahead
        for (int i = 0; i < n; i++) {
            if (!forward.hasNext()) {
                return null; // Not enough elements
            }
            forward.next();
        }
        
        // Now move both iterators until forward reaches the end
        while (forward.hasNext()) {
            forward.next();
            backward.next();
        }
        
        // backward is now at the nth element from the end
        return backward.next();
    }
    
    // Alternative approach using size calculation (but the problem says without calculating size)
    // So we'll stick with the two-pointer approach above
    public static <T> T findNthFromEndWithSize(LinkedList<T> list, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        
        int size = list.size();
        if (n > size) {
            return null; // Not enough elements
        }
        
        return list.get(size - n);
    }
    
    public static void main(String[] args) {
        System.out.println("=== List Interface Problems ===");
        
        // Test 1: Reverse a List
        System.out.println("\n1. Reverse a List:");
        List<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Original ArrayList: " + arrayList);
        reverseArrayList(arrayList);
        System.out.println("Reversed ArrayList: " + arrayList);
        
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original LinkedList: " + linkedList);
        reverseLinkedList(linkedList);
        System.out.println("Reversed LinkedList: " + linkedList);
        
        // Test 2: Find Frequency of Elements
        System.out.println("\n2. Find Frequency of Elements:");
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange");
        System.out.println("Input: " + fruits);
        Map<String, Integer> frequency = findFrequency(fruits);
        System.out.println("Frequency: " + frequency);
        
        // Test 3: Rotate Elements in a List
        System.out.println("\n3. Rotate Elements in a List:");
        List<Integer> rotateList = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original: " + rotateList);
        rotateList(rotateList, 2);
        System.out.println("Rotated by 2: " + rotateList);
        
        // Test 4: Remove Duplicates While Preserving Order
        System.out.println("\n4. Remove Duplicates While Preserving Order:");
        List<Integer> dupList = Arrays.asList(3, 1, 2, 2, 3, 4);
        System.out.println("Original: " + dupList);
        List<Integer> uniqueList = removeDuplicatesPreserveOrder(dupList);
        System.out.println("Without duplicates: " + uniqueList);
        
        // Test 5: Find Nth Element from the End
        System.out.println("\n5. Find Nth Element from the End:");
        LinkedList<String> letters = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("List: " + letters);
        String nthElement = findNthFromEnd(letters, 2);
        System.out.println("2nd element from end: " + nthElement);
    }
}