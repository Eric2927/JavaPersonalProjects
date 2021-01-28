import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class HuffmanTree {

    public static int ASCII_RANGE = 256;

    public static Node generateHuffmanTree(File file) throws IOException {
        int[] frequencyArr = getFrequencyArr(file);
        PriorityQueue<Node> minHeap = new PriorityQueue<>(ASCII_RANGE, Comparator.comparingInt(node -> node.freq));
        for(int i=0; i<frequencyArr.length; i++){
            if(frequencyArr[i]>0){
                Node leafNode = new LeafNode(i, frequencyArr[i]);
                minHeap.add(leafNode);
            }
        }
        while(minHeap.size()>1){
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            InternalNode e = new InternalNode(left.freq + right.freq);
            e.left = left;
            e.right = right;
            minHeap.add(e);
        }
        return minHeap.poll();
    }

    public static void printCodes(Node root, int[] arr, int top){
        if(root instanceof InternalNode){
            arr[top] = 0;
            printCodes(((InternalNode) root).left, arr, top+1);
            arr[top] = 1;
            printCodes(((InternalNode) root).right, arr, top+1);
        }
        if(root instanceof LeafNode){
            System.out.print((char) ((LeafNode) root).ch + ": ");
            for(int i=0; i<top; i++){
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }
    
    public static void printCodesBFS(Node root) {
    	LinkedList<Node> queue = new LinkedList<Node>();
    	queue.add(root);
    	boolean[] array = new boolean[5000];
    	printCodesBFSHelper(queue, array, 1);
    }
    
    public static void printCodesBFSHelper(LinkedList<Node> queue, boolean[] nodeExist, int nodeCursor) {
    	Node cursor = queue.poll();
    	if (cursor instanceof InternalNode) {
    		if (((InternalNode) cursor).left != null) {
        		queue.add(((InternalNode) cursor).left);
        		nodeExist[nodeCursor*2] = true;
    		}
    		else {
    			nodeExist[nodeCursor*2] = false;
    		}
    		if (((InternalNode) cursor).right != null) {
        		queue.add(((InternalNode) cursor).right);
        		nodeExist[nodeCursor*2+1] = true;
    		}
    		else {
    			nodeExist[nodeCursor*2+1] = false;
    		}
    	}
    	else if (cursor instanceof LeafNode) {
    		System.out.println(((char)((LeafNode) cursor).ch) + ": " + Integer.toBinaryString(nodeCursor).substring(1));
    	}
    	int originalCursor = nodeCursor;
		do {
			nodeCursor ++;
		} while (nodeExist[nodeCursor] == false && nodeCursor <= (originalCursor*2 + 1));
		if (nodeCursor <= (originalCursor*2 + 1)) {
			printCodesBFSHelper(queue, nodeExist, nodeCursor);
		}
    }


    public static int[] getFrequencyArr(File file) throws IOException {
        int[] frequencyArr = new int[ASCII_RANGE];
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int c = 0;
        while((c = br.read()) != -1){
            frequencyArr[c]++;
        }
        return frequencyArr;
    }

}
