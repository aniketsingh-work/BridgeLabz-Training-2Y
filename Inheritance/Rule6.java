class Parent {
    void display() { System.out.println("Parent"); }
}
class Child extends Parent {
    @Override
    void display() { System.out.println("Child"); }
}
public class Rule6 {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display();
    }
}