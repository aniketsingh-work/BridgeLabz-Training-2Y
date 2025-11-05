public class WrapperComparison {
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;
        
        System.out.println("a == b: " + (a == b));
        System.out.println("c == d: " + (c == d));
        System.out.println("a.equals(b): " + (a.equals(b)));
        
        System.out.println("\nExplanation:");
        System.out.println("For values between -128 and 127, Integer objects are cached, so a and b refer to the same object.");
        System.out.println("For values outside this range (like 200), new objects are created, so c and d are different objects.");
        System.out.println("The equals() method compares values, not references, so a.equals(b) returns true.");
    }
}