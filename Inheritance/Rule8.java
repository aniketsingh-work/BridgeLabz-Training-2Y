class Vehicle {
    void run() { System.out.println("Vehicle runs"); }
}
class Bike extends Vehicle {
    @Override
    void run() { System.out.println("Bike runs"); }
}
public class Rule8 {
    public static void main(String[] args) {
        Bike b = new Bike();
        b.run();
    }
}