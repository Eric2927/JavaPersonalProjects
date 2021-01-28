public class InternalNode extends Node{
    Node left, right;
    public InternalNode(int freq){
        this.freq = freq;
        left = right = null;
    }
    public void display(){
        System.out.println(freq);
        left.display();
        right.display();
    }
}
