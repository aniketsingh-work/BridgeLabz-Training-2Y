class Box<T> {
    private T value;
    
    public void set(T value) {
        this.value = value;
    }
    
    public T get() {
        return this.value;
    }
}

public class BoxDemo {
    public static void main(String[] args) {
        // Test with Integer
        Box<Integer> intBox = new Box<>();
        intBox.set(10);
        System.out.println("Integer value: " + intBox.get());
        
        // Test with String
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println("String value: " + stringBox.get());
        
        // Test with Double
        Box<Double> doubleBox = new Box<>();
        doubleBox.set(15.5);
        System.out.println("Double value: " + doubleBox.get());
    }
}