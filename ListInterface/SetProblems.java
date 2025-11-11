import java.util.*;

public class SetProblems {
    
    // 1. Check if Two Sets Are Equal
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        if (set1.size() != set2.size()) {
            return false;
        }
        
        return set1.containsAll(set2);
    }
    
    // 2. Union and Intersection of Two Sets
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }
    
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }
    
    // 3. Symmetric Difference
    public static <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> union = union(set1, set2);
        Set<T> intersection = intersection(set1, set2);
        
        Set<T> result = new HashSet<>(union);
        result.removeAll(intersection);
        return result;
    }
    
    // Alternative approach for symmetric difference
    public static <T> Set<T> symmetricDifferenceAlternative(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>();
        
        // Add elements in set1 but not in set2
        for (T element : set1) {
            if (!set2.contains(element)) {
                result.add(element);
            }
        }
        
        // Add elements in set2 but not in set1
        for (T element : set2) {
            if (!set1.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }
    
    // 4. Convert a Set to a Sorted List
    public static List<Integer> convertSetToSortedList(Set<Integer> set) {
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }
    
    // Alternative using streams
    public static List<Integer> convertSetToSortedListStream(Set<Integer> set) {
        return set.stream()
                  .sorted()
                  .collect(ArrayList::new, List::add, List::addAll);
    }
    
    // 5. Find Subsets
    public static <T> boolean isSubset(Set<T> subset, Set<T> superset) {
        return superset.containsAll(subset);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Set Interface Problems ===");
        
        // Test 1: Check if Two Sets Are Equal
        System.out.println("\n1. Check if Two Sets Are Equal:");
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 2, 1));
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println("Are equal: " + areSetsEqual(set1, set2));
        
        // Test 2: Union and Intersection of Two Sets
        System.out.println("\n2. Union and Intersection of Two Sets:");
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Arrays.asList(3, 4, 5));
        System.out.println("SetA: " + setA);
        System.out.println("SetB: " + setB);
        System.out.println("Union: " + union(setA, setB));
        System.out.println("Intersection: " + intersection(setA, setB));
        
        // Test 3: Symmetric Difference
        System.out.println("\n3. Symmetric Difference:");
        System.out.println("Symmetric Difference: " + symmetricDifference(setA, setB));
        
        // Test 4: Convert a Set to a Sorted List
        System.out.println("\n4. Convert a Set to a Sorted List:");
        Set<Integer> unsortedSet = new HashSet<>(Arrays.asList(5, 3, 9, 1));
        System.out.println("Input: " + unsortedSet);
        List<Integer> sortedList = convertSetToSortedList(unsortedSet);
        System.out.println("Sorted List: " + sortedList);
        
        // Test 5: Find Subsets
        System.out.println("\n5. Find Subsets:");
        Set<Integer> smallSet = new HashSet<>(Arrays.asList(2, 3));
        Set<Integer> bigSet = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        System.out.println("Set1: " + smallSet);
        System.out.println("Set2: " + bigSet);
        System.out.println("Is subset: " + isSubset(smallSet, bigSet));
    }
}