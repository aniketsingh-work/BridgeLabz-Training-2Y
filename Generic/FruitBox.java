import java.util.ArrayList;
import java.util.List;

class FruitBox<T extends Fruit> {
    private List<T> fruits;
    
    public FruitBox() {
        this.fruits = new ArrayList<>();
    }
    
    public void add(T fruit) {
        fruits.add(fruit);
    }
    
    public void display() {
        System.out.println("Fruits in the box:");
        for (T fruit : fruits) {
            System.out.println("- " + fruit.getName());
        }
    }
    
    public List<T> getFruits() {
        return fruits;
    }
}
