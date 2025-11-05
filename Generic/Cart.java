import java.util.ArrayList;
import java.util.List;

class Cart<T> {
    private List<T> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(T item) {
        items.add(item);
    }
    
    public void removeItem(T item) {
        items.remove(item);
    }
    
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
        } else {
            System.out.println("Items in cart:");
            for (T item : items) {
                System.out.println("- " + item);
            }
        }
    }
    
    public int size() {
        return items.size();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}