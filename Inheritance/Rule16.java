class Account {
    void deposit() { System.out.println("Deposit money"); }
}
class Savings extends Account {
    @Override
    void deposit() { System.out.println("Deposit in savings"); }
}
public class Rule16 {
    public static void main(String[] args) {
        Account a = new Savings();
        a.deposit();
    }
}