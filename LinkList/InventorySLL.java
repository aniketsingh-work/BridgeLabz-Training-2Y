
class ItemNode{
    int id,qty;
    String name;
    double price;
    ItemNode next;

    ItemNode(int i,String n,int q,double p){
        id=i; name=n; qty=q; price=p;
    }
}

public class InventorySLL{
    ItemNode head;

    void addItem(int id,String name,int q,double price){
        ItemNode node=new ItemNode(id,name,q,price);
        node.next=head;
        head=node;
    }

    double totalValue(){
        double sum=0;
        ItemNode t=head;
        while(t!=null){
            sum+=t.price*t.qty;
            t=t.next;
        }
        return sum;
    }

    void display(){
        ItemNode t=head;
        while(t!=null){
            System.out.println(t.id+" "+t.name+" "+t.qty+" "+t.price);
            t=t.next;
        }
    }

    public static void main(String[] args){
        InventorySLL inv=new InventorySLL();
        inv.addItem(1,"Laptop",2,50000);
        inv.addItem(2,"Mouse",5,500);
        inv.display();
        System.out.println("Total Value = "+inv.totalValue());
    }
}
