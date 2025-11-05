// Demo class to test FruitBox
public class FruitBoxDemo {
    public static void main(String[] args) {
        // Create a FruitBox for Apples
        FruitBox<Apple> appleBox = new FruitBox<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.display();
        
        // Create a FruitBox for Mangoes
        FruitBox<Mango> mangoBox = new FruitBox<>();
        mangoBox.add(new Mango());
        mangoBox.add(new Mango());
        mangoBox.display();
        
        // Create a FruitBox for general fruits
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        fruitBox.add(new Apple());
        fruitBox.add(new Mango());
        fruitBox.display();
        
        // This would cause a compilation error if we tried to add a non-fruit:
        // Car car = new Car(); // This class doesn't exist, but if it did, it wouldn't compile
        // fruitBox.add(car); // This won't compile because Car doesn't extend Fruit
    }
}