public class LeafNode extends Node {
    int ch;
    public LeafNode(int ch, int freq){
        this.ch = ch;
        this.freq = freq;
    }
    public void display(){
        System.out.println(ch + " " + freq);
    }
}
