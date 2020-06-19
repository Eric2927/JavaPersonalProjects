import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDecoding {

	public static void huffmanDecode(Node huffmanTree, String pathToEncoded, String pathToWrite) throws IOException {
		File encodedBinFile = new File(pathToEncoded);
		FileReader fr = new FileReader(encodedBinFile);
		BufferedReader br = new BufferedReader(fr);
		FileWriter myWriter = new FileWriter(pathToWrite);
		int c = 0;
		Node cursor = huffmanTree;
		while ((c = br.read()) != -1) {
			if (cursor instanceof LeafNode) {
				myWriter.write(((LeafNode) cursor).ch);
				cursor = huffmanTree;
			}
			if (c == 48) {
				cursor = ((InternalNode) cursor).left;
			} else if (c == 49) {
				cursor = ((InternalNode) cursor).right;
			}
		}
		myWriter.close();
	}

	public static void asciiDecode(String pathToEncoded, String pathToWrite) throws IOException {
		File encodedBinFile = new File(pathToEncoded);
		FileReader fr = new FileReader(encodedBinFile);
		BufferedReader br = new BufferedReader(fr);
		FileWriter myWriter = new FileWriter(pathToWrite);
		int c = 0;
		String s = "";
		while ((c = br.read()) != -1) {
			s += (char) c;
			if (s.length() == 8) {
				myWriter.write((char) (Integer.parseInt(s, 2)));
				s = "";
			}
		}
	}

	public static boolean lzvDecode(String inFilePath, String outFilePath, ArrayList<String> dictionary)
			throws IOException {
		BufferedReader reader;
		BufferedWriter writer;
		Scanner in;
		reader = new BufferedReader(new FileReader(inFilePath));
		in = new Scanner(reader);
		writer = new BufferedWriter(new FileWriter(outFilePath, false));

		String curr, X, Z;
		String[] line;
		Integer[] ints;

		while (in.hasNextLine()) {
			curr = in.nextLine();
			while (curr.length() <= 0) {
				writer.write("\n");
				curr = in.nextLine();
			}
			line = curr.split(" "); // Convert input into an array
			ints = new Integer[line.length];
			for (int i = 0; i < ints.length; i++) // Parse Strings in array into Ints
				ints[i] = Integer.parseInt(line[i]);// and transfer to Integer array

			for (int i = 0; i < ints.length; i++) {
				X = dictionary.get(ints[i]);
				writer.write(X); // Write to output
			}
			writer.write("\n");
		}
		in.close();
		reader.close();
		writer.close();
		return true;
	}

}
