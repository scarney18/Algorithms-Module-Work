import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/******************************************************************************
 *  Compilation:  javac Huffman.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 * Add instructions and documentation related to your Huffman algorithm here...
 *
 ******************************************************************************/

/**
 * Add in your information about each method etc. here
 *
 *
 * @author Your name
 */
public class Huffman {

	// alphabet size of extended ASCII
	private static final int R = 256;

	// Do not instantiate.
	private Huffman() {
	}

	// Huffman trie node
	private static class Node implements Comparable<Node> {
		private final char ch;
		private final int freq;
		private final Node left, right;

		Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		// is the node a leaf node?
		private boolean isLeaf() {
			assert ((left == null) && (right == null)) || ((left != null) && (right != null));
			return (left == null) && (right == null);
		}

		// compare, based on frequency
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	/**
	 * Reads a sequence of 8-bit bytes from standard input; compresses them using
	 * Huffman codes with an 8-bit alphabet; and writes the results to standard
	 * output.
	 */
	public static void compress(String inFile, String outFile) {
		// read the input
		BinaryIn input = new BinaryIn(inFile);
		String intext = input.readString();

		// tabulate frequency counts
		char[] text = intext.toCharArray();
		int[] freq = new int[R];
		for(int i=0; i<R; i++) {
			freq[i] = 0;
		}

		for (int i = 0; i < text.length; i++) {
			freq[text[i]]++;
		}

		// build Huffman trie
		Node head = buildTrie(freq);
		
		// build code table
		String[] table = new String[R];
		buildCode(table, head, "");

		// print trie for decoder
		BinaryOut output = new BinaryOut(outFile);
		writeTrie(head, output);

		// print number of bytes in original uncompressed message
		output.write(text.length);

		// use Huffman code to encode input
		for (int i = 0; i < text.length; i++) {
            String huffCode = table[text[i]];
            for (int j = 0; j < huffCode.length(); j++) {
                if (huffCode.charAt(j) == '0') {
                	output.write(false);
                }
                else if (huffCode.charAt(j) == '1') {
                	output.write(true);
                }	
            }
		}
		output.close();
	}

	/**
	 * Reads a sequence of bits that represents a Huffman-compressed message from
	 * standard input; expands them; and writes the results to standard output.
	 */
	public static void decompress(String inFile, String outFile) {
		// read in Huffman trie from input stream
		BinaryIn input = new BinaryIn(inFile);
		Node head = readTrie(input);
		
		// number of bytes to write
		int length = input.readInt();
		
		// decode using the Huffman trie
		BinaryOut output = new BinaryOut(outFile);
		for(int i=0; i<length; i++) {
			Node curr = head;
			while(!curr.isLeaf()) {
				boolean next = input.readBoolean();
				if(next) {
					curr = curr.right;
				}
				else {
					curr = curr.left;
				}
			}
			output.write(curr.ch);
		}
		output.close();
	}

	// build the Huffman trie given frequencies
	private static Node buildTrie(int[] freq) {

		// initialze priority queue with singleton trees
		MinPQ<Node> pq = new MinPQ<Node>();
		for (char i = 0; i < R; i++)
			if (freq[i] > 0)
				pq.insert(new Node(i, freq[i], null, null));

		// special case in case there is only one character with a nonzero frequency
		if (pq.size() == 1) {
			if (freq['\0'] == 0)
				pq.insert(new Node('\0', 0, null, null));
			else
				pq.insert(new Node('\1', 0, null, null));
		}

		// merge two smallest trees
		while (pq.size() > 1) {
			Node left = pq.delMin();
			Node right = pq.delMin();
			Node parent = new Node('\0', left.freq + right.freq, left, right);
			pq.insert(parent);
		}
		return pq.delMin();
	}

	// write bitstring-encoded trie to standard output
	private static void writeTrie(Node x, BinaryOut output) {
		
		if (x.isLeaf()) {
			output.write(true);
			output.write(x.ch, 8);
			return;
		}
		output.write(false);
		writeTrie(x.left, output);
		writeTrie(x.right, output);
	}

	// make a lookup table from symbols and their encodings
	private static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1');
		} else {
			st[x.ch] = s;
		}
	}

	private static Node readTrie(BinaryIn input) {
		boolean isLeaf = input.readBoolean();
		if (isLeaf) {
			return new Node(input.readChar(), -1, null, null);
		} else {
			return new Node('\0', -1, readTrie(input), readTrie(input));
		}
	}

	/**
	 * Sample client that calls {@code compress()} if the command-line argument is
	 * "compress" an {@code decompress()} if it is "decompress".
	 *
	 * @param args the command-line arguments
	 */
	//example inputs: 	compress input.txt output.txt
	//					decompress input.txt output.txt
	public static void main(String[] args) {
		Stopwatch sw = new Stopwatch();
		if      (args[0].equals("compress")) compress(args[1],args[2]);
        else if (args[0].equals("decompress")) decompress(args[1],args[2]);
		System.out.println("elapsed time: "+sw.elapsedTime());	
	}
	
}
