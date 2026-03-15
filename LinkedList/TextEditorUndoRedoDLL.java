
class StateNode{
    String text;
    StateNode next,prev;

    StateNode(String t){
        text=t;
    }
}

public class TextEditorUndoRedoDLL{
    StateNode current;

    void addState(String text){
        StateNode n=new StateNode(text);
        if(current!=null){
            current.next=n;
            n.prev=current;
        }
        current=n;
    }

    void undo(){
        if(current!=null && current.prev!=null)
            current=current.prev;
    }

    void redo(){
        if(current!=null && current.next!=null)
            current=current.next;
    }

    void display(){
        if(current!=null)
            System.out.println(current.text);
    }

    public static void main(String[] args){
        TextEditorUndoRedoDLL editor=new TextEditorUndoRedoDLL();
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.undo();
        editor.display();
    }
}
