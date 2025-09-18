class Phone {
    void call() { System.out.println("Phone calls"); }
}
class SmartPhone extends Phone {}
public class Rule12 {
    public static void main(String[] args) {
        SmartPhone sp = new SmartPhone();
        sp.call();
    }
}