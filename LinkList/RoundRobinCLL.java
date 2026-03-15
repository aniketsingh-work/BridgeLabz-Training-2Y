
class ProcessNode{
    int id,burst;
    ProcessNode next;

    ProcessNode(int i,int b){
        id=i; burst=b;
    }
}

public class RoundRobinCLL{
    ProcessNode head;

    void addProcess(int id,int burst){
        ProcessNode node=new ProcessNode(id,burst);

        if(head==null){
            head=node;
            node.next=head;
            return;
        }

        ProcessNode t=head;
        while(t.next!=head) t=t.next;

        t.next=node;
        node.next=head;
    }

    void display(){
        if(head==null) return;
        ProcessNode t=head;
        do{
            System.out.println("Process "+t.id+" Burst "+t.burst);
            t=t.next;
        }while(t!=head);
    }

    public static void main(String[] args){
        RoundRobinCLL rr=new RoundRobinCLL();
        rr.addProcess(1,5);
        rr.addProcess(2,3);
        rr.display();
    }
}
