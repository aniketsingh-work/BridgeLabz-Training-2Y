class Base {
    void process() { System.out.println("Base process"); }
}
class Derived extends Base {
    @Override
    void process() { System.out.println("Derived process"); }
}
public class Rule11 {
    public static void main(String[] args) {
        Base b = new Derived();
        b.process();
    }
}