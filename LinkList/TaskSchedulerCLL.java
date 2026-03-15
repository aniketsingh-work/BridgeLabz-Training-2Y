
class TaskNode{
    int id;
    String name;
    TaskNode next;

    TaskNode(int i,String n){
        id=i; name=n;
    }
}

public class TaskSchedulerCLL{
    TaskNode head;

    void addTask(int id,String name){
        TaskNode node=new TaskNode(id,name);

        if(head==null){
            head=node;
            node.next=head;
            return;
        }

        TaskNode t=head;
        while(t.next!=head) t=t.next;

        t.next=node;
        node.next=head;
    }

    void display(){
        if(head==null) return;
        TaskNode t=head;
        do{
            System.out.println(t.id+" "+t.name);
            t=t.next;
        }while(t!=head);
    }

    public static void main(String[] args){
        TaskSchedulerCLL ts=new TaskSchedulerCLL();
        ts.addTask(1,"Coding");
        ts.addTask(2,"Study");
        ts.display();
    }
}
