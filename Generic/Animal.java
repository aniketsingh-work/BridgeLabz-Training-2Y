abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract void makeSound();
    
    @Override
    public String toString() {
        return this.name;
    }
}