class Bank {
    final void policy() { System.out.println("Fixed policy"); }
}
class Branch extends Bank {}
public class Rule5 {
    public static void main(String[] args) {
        Branch b = new Branch();
        b.policy();
    }
}