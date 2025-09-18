interface A { void show(); }
interface B { void display(); }
class Impl implements A, B {
    public void show() { System.out.println("Show"); }
    public void display() { System.out.println("Display"); }
}
public class Rule10 {
    public static void main(String[] args) {
        Impl i = new Impl();
        i.show();
        i.display();
    }
}