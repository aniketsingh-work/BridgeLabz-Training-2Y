
class StudentNode{
    int roll;
    String name;
    int age;
    String grade;
    StudentNode next;

    StudentNode(int r,String n,int a,String g){
        roll=r; name=n; age=a; grade=g;
    }
}

public class StudentRecordSLL{
    StudentNode head;

    void add(int r,String n,int a,String g){
        StudentNode node=new StudentNode(r,n,a,g);
        node.next=head;
        head=node;
    }

    void display(){
        StudentNode t=head;
        while(t!=null){
            System.out.println(t.roll+" "+t.name+" "+t.grade);
            t=t.next;
        }
    }

    public static void main(String[] args){
        StudentRecordSLL s=new StudentRecordSLL();
        s.add(1,"Rahul",20,"A");
        s.add(2,"Aman",21,"B");
        s.display();
    }
}
