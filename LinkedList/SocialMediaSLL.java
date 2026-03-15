
class UserNode{
    int id;
    String name;
    UserNode next;

    UserNode(int i,String n){
        id=i; name=n;
    }
}

public class SocialMediaSLL{
    UserNode head;

    void addUser(int id,String name){
        UserNode u=new UserNode(id,name);
        u.next=head;
        head=u;
    }

    void display(){
        UserNode t=head;
        while(t!=null){
            System.out.println(t.id+" "+t.name);
            t=t.next;
        }
    }

    public static void main(String[] args){
        SocialMediaSLL sm=new SocialMediaSLL();
        sm.addUser(1,"Vivek");
        sm.addUser(2,"Aman");
        sm.display();
    }
}
