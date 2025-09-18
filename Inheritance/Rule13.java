class Human {
    Human(String name) { System.out.println("Human: " + name); }
}
class Student extends Human {
    Student(String name) { super(name); }
}
public class Rule13 {
    public static void main(String[] args) {
        Student s = new Student("Alex");
    }
}