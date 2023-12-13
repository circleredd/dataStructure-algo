public class QueueLinkedlist {
    private  Node first, last;  //first -> dequeue, last -> enqueue
    private class Node{
        String item;
        Node next;
//        public  Node(String item){
//            this.item = item;
//            this.next = null;
//        }
    }

    public boolean is_Empty(){
        return first == null;
    }
    public void enqueue(String item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(is_Empty())
            first = last;
        else
            oldlast.next = last;

    }
    public String dequeue(){
        String item = first.item;
        first = first.next;
        if(is_Empty())
            last = null;
        return item;
    }
    public static void main(String[] args) {
        QueueLinkedlist a = new QueueLinkedlist();
        a.enqueue("Today");
        a.enqueue("is");
        a.enqueue("Friday");
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());
    }
}
