abstract class Shape {
    abstract void draw();
    void info() { System.out.println("Shape info"); }
}
class Circle extends Shape {
    void draw() { System.out.println("Circle drawn"); }
}
public class Rule9 {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw();
        s.info();
    }
}