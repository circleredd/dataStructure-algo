import java.awt.desktop.SystemEventListener;

public class ResizeStack {
    public static class Node{
        public String i = "Inner class should be static, or you should first declare outer class.";
    }

    private String[] s;
    private int N = 0;
    public ResizeStack(int capacity){    //constructor
        s = new String[capacity];
    }

    public void push(String item){
        if(N > 0 && N >= s.length-1)
            resize(s.length*2);
        s[N++] = item;
    }

    public String pop(){
        if(N > 0 && N == s.length/4)
            resize(s.length/2);
        return s[--N];
    }
    public boolean isEmpty(){
        return N==0;
    }

    public void resize(int capacity){
        String[] copy = new String[capacity];
        for(int i=0; i<N; i++){
            copy[i] = s[i];
        }
        s = copy;
    }
    public static void main(String[] args){
        ResizeStack a = new ResizeStack(10);
        a.push("easy");
        a.push("peasy");
        System.out.println(a.pop());
        System.out.println(a.pop());

        //測試 nested class
        Node b = new Node();
        System.out.println(b.i);
    }
}
