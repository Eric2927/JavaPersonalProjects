import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanTable {

    public static HashMap<Integer, String> generateHuffmanTable(Node huffmanTree){
        HashMap<Integer, String> table = new HashMap<>();
        generateHuffmanTableHelper(huffmanTree, table, "");
        return table;
    }

    public static void generateHuffmanTableHelper(Node huffmanTree, HashMap<Integer, String> table, String s){
        if(huffmanTree instanceof InternalNode){
            generateHuffmanTableHelper(((InternalNode) huffmanTree).left, table, s + "0");
            generateHuffmanTableHelper(((InternalNode) huffmanTree).right, table, s + "1");
        }else if(huffmanTree instanceof LeafNode){
            table.put(((LeafNode) huffmanTree).ch, s);
        }
    }
}
