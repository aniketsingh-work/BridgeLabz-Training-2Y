class Printer {
    void print() { System.out.println("Default printing"); }
}
class AdvancedPrinter {
    private Printer printer = new Printer();
    void print() { printer.print(); System.out.println("Advanced printing"); }
}
public class Rule18 {
    public static void main(String[] args) {
        AdvancedPrinter ap = new AdvancedPrinter();
        ap.print();
    }
}