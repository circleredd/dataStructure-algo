import java.awt.desktop.SystemEventListener;

public class LinkedStackOfString {
    private class Node {
        String item;
        Node next;
    }
    public Node first = null;
    public Node last = null;
    LinkedStackOfString(){
    }

    public void push(String item){
        Node tmp = new Node();
        tmp.item = item;
        if(first == null){
            first = tmp;
            tmp.next = null;
        }
        else{
            tmp.next = last;
        }
        last = tmp;
    }

    public String pop(){
        String item = last.item;
        last = last.next;
        return item;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public static void main(String[] args){
        LinkedStackOfString a = new LinkedStackOfString();
        a.push("easy");
        a.push("peasy");
        System.out.println(a.pop());
        System.out.println(a.pop());
    }
}
