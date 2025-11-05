import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalPrinter {
    public static void printAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal.getName());
            animal.makeSound();
        }
    }
    
    public static void main(String[] args) {
        // Create lists of different animal types
        List<Dog> dogs = Arrays.asList(new Dog("Buddy"), new Dog("Max"));
        List<Cat> cats = Arrays.asList(new Cat("Whiskers"), new Cat("Fluffy"));
        
        // Print dogs
        System.out.println("Dogs:");
        printAnimals(dogs);
        
        System.out.println("\nCats:");
        printAnimals(cats);
        
        // Create a mixed list of animals
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Rex"));
        animals.add(new Cat("Mittens"));
        
        System.out.println("\nMixed animals:");
        printAnimals(animals);
    }
}