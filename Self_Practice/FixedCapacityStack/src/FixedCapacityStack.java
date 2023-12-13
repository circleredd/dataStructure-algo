import java.awt.desktop.SystemEventListener;

public class FixedCapacityStack {

    private String[] s;
    private int N = 0;
    public FixedCapacityStack(int capacity){    //constructor
        s = new String[capacity];
    }

    public void push(String item){
        s[N++] = item;
    }

    public String pop(){
        return s[--N];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public static void main(String[] args){
        FixedCapacityStack a = new FixedCapacityStack(20);
        a.push("easy");
        a.push("peasy");
        System.out.println(a.pop());
        System.out.println(a.pop());
    }
}
