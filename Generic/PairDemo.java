public class PairDemo {
    public static void main(String[] args) {
        // Example: Pair<String, Integer> student = new Pair<>("Amol", 20);
        Pair<String, Integer> student = new Pair<>("Amol", 20);
        System.out.println("Student name: " + student.getFirst());
        System.out.println("Student age: " + student.getSecond());
        
        // Another example with different types
        Pair<Double, String> priceItem = new Pair<>(19.99, "Laptop");
        System.out.println("Price: " + priceItem.getFirst());
        System.out.println("Item: " + priceItem.getSecond());
    }
}