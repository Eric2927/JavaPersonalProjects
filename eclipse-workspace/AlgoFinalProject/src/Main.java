import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
    	/*String fileName = "src\\LoremBig.txt";
        File file = new File(fileName);
        Node fileHuffmanTree = HuffmanTree.generateHuffmanTree(file);
        FileEncoding.huffmanEncode(file, fileHuffmanTree, "src\\huffmanEncode.txt");
        FileEncoding.asciiEncode(file, "src\\asciiEncode.txt");
        FileEncoding.lzvEncode(fileName, "src\\LZVEncode.txt");
        long time1 = System.nanoTime();
        int bitsInHuffman = FileEncoding.numCharacters("src\\huffmanEncode.txt");
        long time2 = System.nanoTime();
        int bitsInAscii = FileEncoding.numCharacters("src\\asciiEncode.txt");
        long time3 = System.nanoTime();
        int charsInLZV = FileEncoding.numCharacters("src\\LZVEncode.txt");
        long time4 = System.nanoTime();
        System.out.println("Huffman runtime = "+(time2-time1)*Math.pow(10, -6));
        System.out.println("Ascii runtime = "+(time3-time2)*Math.pow(10, -6));
        System.out.println("LZW runtime = "+(time4-time3)*Math.pow(10, -6));
        System.out.println("Bits in Huffman "+bitsInHuffman+" \n"+"Bits in Ascii "+bitsInAscii+"\nChars in LZW "+charsInLZV);
        FileDecoding.lzvDecode("src\\LZVEncode.txt", "src\\LZVDecode.txt", FileEncoding.lzvDict);
        FileDecoding.huffmanDecode(fileHuffmanTree, "src\\huffmanEncode.txt", "src\\huffmanDecode.txt");
        FileDecoding.asciiDecode("src\\asciiEncode.txt", "src\\asciiDecode.txt"); */
    	
    	File file = new File("src/abacaba.txt");
    	makeAbacabaFile(8);
    	Node huffmanRoot = HuffmanTree.generateHuffmanTree(file);
    	HuffmanTree.printCodesBFS(huffmanRoot);
    }
    
    public static void makeAbacabaFile(int n) throws IOException {
    	String abacaba = generateAbacaba(n);
    	File file = new File("src/abacaba.txt");	// Makes sure that the file is there
    	FileWriter myFileWriter = new FileWriter("src/abacaba.txt");
    	myFileWriter.write(abacaba);
    	myFileWriter.close();
    }
    
    public static String generateAbacaba(int n) {
    	if (n != 0) {
    		n --;
    		return generateAbacaba(n) + (char)(n + 98) + generateAbacaba(n);
    	}
    	else {
    		return "a";
    	}
    }
}
