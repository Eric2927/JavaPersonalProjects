import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileEncoding {
	public static ArrayList<String> lzvDict;

	public static void huffmanEncode(File file, Node huffmanTree, String pathToWrite) throws IOException {
		HashMap<Integer, String> table = HuffmanTable.generateHuffmanTable(huffmanTree);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		FileWriter myWriter = new FileWriter(pathToWrite);
		int c = 0;
		while ((c = br.read()) != -1) {
			myWriter.write(table.get(c));
		}
		myWriter.close();
	}

	public static void asciiEncode(File file, String pathToWrite) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		FileWriter myWriter = new FileWriter(pathToWrite);
		int c = 0;
		while ((c = br.read()) != -1) {
			String s = Integer.toBinaryString(c);
			s = "00000000" + s;
			myWriter.write(s.substring(s.length() - 8));
		}
		myWriter.close();
	}

	public static int numCharacters(String pathToFile) throws IOException {
		File file = new File(pathToFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int c = 0;
		int counter = 0;
		while ((c = br.read()) != -1) {
			counter++;
		}
		return counter;
	}

	public static boolean lzvEncode(String inFilePath, String outFilePath) throws IOException {
		BufferedReader reader;
		BufferedWriter writer;
		reader = new BufferedReader(new FileReader(inFilePath));
		writer = new BufferedWriter(new FileWriter(outFilePath, false));
		ArrayList<String> dictionary = new ArrayList<>(255);
		for (int i = 0; i < 26; i++) {
			String temp = (char) (i + 65) + "";
			dictionary.add(temp);
		}
		String X, Z, line;
		while ((line = reader.readLine()) != null) {
			if (line.length() <= 0 && line != null) {
				line = reader.readLine();
				writer.write("\n");
			}
			int inc = 0;
			while (inc < line.length() - 1) {
				X = line.charAt(inc) + "";
				Z = line.charAt(++inc) + "";

				while (dictionary.contains(X + Z)) {
					X = X + Z;
					inc++;
					if (inc < line.length())
						Z = line.charAt(inc) + "";
				}
				dictionary.add(X + Z);

				if (dictionary.indexOf(X) == -1) {
					dictionary.add(X);
				}
				writer.write(dictionary.indexOf(X) + " ");
			}
			writer.write("\n");
		}
		lzvDict = dictionary;
		reader.close();
		writer.close();
		return true;
	}

}
