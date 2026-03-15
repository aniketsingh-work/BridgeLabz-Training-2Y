
class BookNode{
    int id;
    String title,author;
    BookNode next,prev;

    BookNode(int i,String t,String a){
        id=i; title=t; author=a;
    }
}

public class LibraryDLL{
    BookNode head,tail;

    void addBook(int id,String t,String a){
        BookNode b=new BookNode(id,t,a);
        if(head==null){ head=tail=b; return; }
        tail.next=b;
        b.prev=tail;
        tail=b;
    }

    void display(){
        BookNode t=head;
        while(t!=null){
            System.out.println(t.id+" "+t.title+" "+t.author);
            t=t.next;
        }
    }

    public static void main(String[] args){
        LibraryDLL lib=new LibraryDLL();
        lib.addBook(1,"Java","James");
        lib.addBook(2,"DSA","CLRS");
        lib.display();
    }
}
