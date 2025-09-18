class Bird {
    void fly() { System.out.println("Bird flies"); }
}
class Sparrow extends Bird {
    void fly() { System.out.println("Sparrow flies"); }
}
public class Rule3 {
    public static void main(String[] args) {
        Bird b = new Sparrow();
        b.fly();
    }
}