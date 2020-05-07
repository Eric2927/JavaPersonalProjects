import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;

public class HW5 {

	public static void main(String[] args) {
		// problem1();
		// problem2();
		// problem3();
	}
	
	public static void problem1() {
		File myFile = new File("ElephantsChild.txt");
		final int C = 123;
		final int m = 1000;
		try {
			final Scanner inFile = new Scanner(myFile, "ISO-8859-1");
			int hash = -1;
			while (inFile.hasNext()) {
				// The problem didn't specify what I should do with the hashed words, so I just chose to print them
				System.out.println(computeHashAddress(inFile.next(), C, m));
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static void problem2() {
		final int C = 123;
		final int size = 1000;
		String[] hashTable = new String[size];
		File myFile = new File("ElephantsChild.txt");
		try {
			final Scanner inFile = new Scanner(myFile, "ISO-8859-1");
			String word = "";
			while (inFile.hasNext()) {
				word = inFile.next();
				word = word.replaceAll("[^a-zA-Z'-]","");
				hash(word, hashTable, C, size);
			}
			inFile.close();
			int numKeys = 0;
			double loadFactor = 0;
			int hashAddress = -1;
			
			int longestEmptyAreaLocation = -1;
			int longestEmptyAreaLength = -1;
			int emptySizeCounter = 0;
			
			int longestClusterLocation = -1;
			int longestClusterLength = -1;
			int clusterSizeCounter = 0;
			
			int[] hashAddressFrequencies = new int[size];
			int mostCommonHashAddress = -1;
			int highestHashAddressFrequency = -1;
			
			int furthestDistanceFromAddress = -1;
			String wordFurthestFromAddress = "";
			for (int i = 0; i < size; i ++) {
				if (hashTable[i] != null) {
					hashAddress = computeHashAddress(hashTable[i], C, size);
					hashAddressFrequencies[hashAddress] = hashAddressFrequencies[hashAddress] + 1;
					numKeys ++;
					
					emptySizeCounter = 0;
					
					clusterSizeCounter ++;
					if (clusterSizeCounter > longestClusterLength) {
						longestClusterLength = clusterSizeCounter;
						longestClusterLocation = i - longestClusterLength + 1;
					}
					
					if (Math.abs(i - hashAddress) > furthestDistanceFromAddress) {
						furthestDistanceFromAddress = Math.abs(i - hashAddress);
						wordFurthestFromAddress = hashTable[i];
					}
					
					System.out.println(i + "\t\t" + hashTable[i] + "\t\t" + hashAddress);
				}
				else {
					clusterSizeCounter = 0;
					
					emptySizeCounter ++;
					if (emptySizeCounter > longestEmptyAreaLength) {
						longestEmptyAreaLength = emptySizeCounter;
						longestEmptyAreaLocation = i - longestEmptyAreaLength + 1;
					}
				}
			}
			
			loadFactor = numKeys/(double)size;
			
			for (int i = 0; i < size; i ++) {
				if (hashAddressFrequencies[i] > highestHashAddressFrequency) {
					highestHashAddressFrequency = hashAddressFrequencies[i];
					mostCommonHashAddress = i;
				}
			}
			
			System.out.println("Number of Keys: " + numKeys);
			System.out.println("Load Factor: " + loadFactor);
			System.out.println("Longest Empty Area Starting Address: " + longestEmptyAreaLocation);
			System.out.println("Longest Empty Area Length: " + longestEmptyAreaLength);
			System.out.println("Longest Cluster Starting Address: " + longestClusterLocation);
			System.out.println("Longest Cluster Length: " + longestClusterLength);
			System.out.println("Hash Address with greatest number of distinct words: " + mostCommonHashAddress);
			System.out.println("Number of words that share the hash address above: " + highestHashAddressFrequency);
			System.out.println("Word furthest from its actual hash address: " + wordFurthestFromAddress);
			System.out.println("Distance of the word above from its actual hash address: " + furthestDistanceFromAddress);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	
	// For my implementation
	public static void problem3() {
		File myFile = new File("graph.txt");
		/*int[][] adjMatrix = {{0, 50, 7, 10, 0, 0, 0, 0, 0, 0},
				{50, 0, 30, 0, 3, 0, 99, 0, 0, 0},
				{7, 30, 0, 6, 27, 15, 0, 0, 0, 0},
				{10, 0, 6, 0, 0, 11, 0, 0, 4, 0},
				{0, 3, 27, 0, 0, 12, 120, 105, 0, 0},
				{0, 0, 15, 11, 12, 0, 0, 119, 5, 0},
				{0, 99, 0, 0, 120, 0, 0, 2, 0, 67},
				{0, 0, 0, 0, 105, 119, 2, 0, 122, 66},
				{0, 0, 0, 4, 0, 5, 0, 122, 0, 190},
				{0, 0, 0, 0, 0, 0, 67, 66, 190, 0}}; */
		int[][] adjMatrix = null;
		try {
			Scanner inFile = new Scanner(myFile, "ISO-8859-1");
			int numNodes = inFile.nextInt();
			adjMatrix = new int[numNodes][numNodes];
			for (int i = 0; i < numNodes; i ++) {
				for (int j = 0; j < numNodes; j ++) {
					adjMatrix[i][j] = inFile.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		
		// Let's just assume the person executing this program isn't trying to break it
		Scanner user = new Scanner(System.in);
		System.out.print("Start Node: ");
		int startNodeNum = user.nextInt();
		System.out.print("Destination Node: ");
		int destinationNodeNum = user.nextInt();
		user.close();
		
		Node[] nodes = new Node[adjMatrix.length];
		for (int i = 0; i < adjMatrix.length; i ++) {
			Node aNode = new Node(i);
			nodes[i] = aNode;
		}
		
		PriorityQueue<Node> nodesToVisit = new PriorityQueue<Node>();
		Node cursor = null;
		nodes[startNodeNum].setShortestDistance(0);
		nodesToVisit.add(nodes[startNodeNum]);
		
		while (!nodesToVisit.isEmpty()) {
			cursor = nodesToVisit.poll();
			for (int i = 0; i < adjMatrix[cursor.getNum()].length; i ++) {
				if (adjMatrix[cursor.getNum()][i] != 0) {
					if (adjMatrix[cursor.getNum()][i] + cursor.getShortestDistance() < nodes[i].getShortestDistance()) {
						nodes[i].setShortestDistance(adjMatrix[cursor.getNum()][i] + cursor.getShortestDistance());
						nodes[i].setPenultimateNode(cursor.getNum());
						nodesToVisit.add(nodes[i]);
					}
				}
			}
		}
		
		System.out.println("Shortest distance to node " + destinationNodeNum + " is " + nodes[destinationNodeNum].getShortestDistance());
		String path = "";
		cursor = nodes[destinationNodeNum];
		path = path + destinationNodeNum;
		while (cursor.getNum() != startNodeNum) {
			cursor = nodes[cursor.getPenultimateNodeNum()];
			path = cursor.getNum() + " " + path;
		}
		System.out.println(path);
		
		
	}
	
	// Computes the hash address of a word, based on the suggested hash function from Levitin
	public static int computeHashAddress(String word, int C, int m) {
		int h = 0;
		for (int i = 0; i < word.length(); i ++) {
			h = (h * C + ((int)word.charAt(i))) % m;
		}
		return h;
	}
	
	// Populates a given hashTable with the given word
	public static void hash(String word, String[] hashTable, int C, int m) {
		int hashAddress = computeHashAddress(word, C, m);
		if (hashTable[hashAddress] == null) {
			hashTable[hashAddress] = word;
		}
		else {
			while (hashTable[hashAddress] != null) {
				if (hashTable[hashAddress].compareTo(word) == 0) {
					break;
				}
				hashAddress ++;
				if (hashAddress >= 1000) {
					hashAddress = 0;
				}
			}
			hashTable[hashAddress] = word;
		}
	}

}
