
class TicketNode{
    int id;
    String customer,movie;
    TicketNode next;

    TicketNode(int i,String c,String m){
        id=i; customer=c; movie=m;
    }
}

public class TicketReservationCLL{
    TicketNode head;

    void addTicket(int id,String c,String m){
        TicketNode node=new TicketNode(id,c,m);

        if(head==null){
            head=node;
            node.next=head;
            return;
        }

        TicketNode t=head;
        while(t.next!=head) t=t.next;

        t.next=node;
        node.next=head;
    }

    void display(){
        if(head==null) return;
        TicketNode t=head;
        do{
            System.out.println(t.id+" "+t.customer+" "+t.movie);
            t=t.next;
        }while(t!=head);
    }

    public static void main(String[] args){
        TicketReservationCLL ts=new TicketReservationCLL();
        ts.addTicket(1,"Vivek","Avengers");
        ts.addTicket(2,"Aman","Batman");
        ts.display();
    }
}
