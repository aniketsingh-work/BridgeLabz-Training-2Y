
class MovieNode{
    String title,director;
    int year;
    double rating;
    MovieNode next,prev;

    MovieNode(String t,String d,int y,double r){
        title=t; director=d; year=y; rating=r;
    }
}

public class MovieManagementDLL{
    MovieNode head,tail;

    void addEnd(String t,String d,int y,double r){
        MovieNode n=new MovieNode(t,d,y,r);
        if(head==null){ head=tail=n; return; }
        tail.next=n;
        n.prev=tail;
        tail=n;
    }

    void display(){
        MovieNode t=head;
        while(t!=null){
            System.out.println(t.title+" "+t.director+" "+t.rating);
            t=t.next;
        }
    }

    public static void main(String[] args){
        MovieManagementDLL m=new MovieManagementDLL();
        m.addEnd("Inception","Nolan",2010,9.0);
        m.addEnd("Interstellar","Nolan",2014,9.5);
        m.display();
    }
}
