class BaseClass {
    void method() { System.out.println("Base"); }
}
class SubClass extends BaseClass {
    @Override
    void method() { System.out.println("Sub"); }
}
public class Rule15 {
    public static void main(String[] args) {
        SubClass s = new SubClass();
        s.method();
    }
}