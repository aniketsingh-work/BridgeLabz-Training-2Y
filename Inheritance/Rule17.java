abstract class Appliance {
    abstract void use();
}
class Fan extends Appliance {
    void use() { System.out.println("Fan spinning"); }
}
public class Rule17 {
    public static void main(String[] args) {
        Appliance a = new Fan();
        a.use();
    }
}