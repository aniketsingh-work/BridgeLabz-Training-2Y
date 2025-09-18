class Superclass {
    private String name;
    protected void setName(String name) { this.name = name; }
    protected String getName() { return name; }
}
class Subclass extends Superclass {
    void print() { setName("Java"); System.out.println(getName()); }
}
public class Rule7 {
    public static void main(String[] args) {
        Subclass s = new Subclass();
        s.print();
    }
}