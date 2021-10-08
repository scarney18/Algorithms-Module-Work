
public class Trie {

	// Alphabet size (# of symbols) we pick 26 for English alphabet
	static final int ALPHABET_SIZE = 26;

	// class for Trie node
	static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		// isEndOfWord is true if the node represents end of a word i.e. leaf node
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	}

	static TrieNode root;

	// If not key present, inserts into trie
	// If the key is prefix of Trie node, 
	// marks leaf node
	static void insert(String key) {
		// using pointer curr to the current trie node
		TrieNode curr = root;

		// iterate through the key's characters with variable e
		for (char e : key.toCharArray()) {
			// convert the char to an int for the index of children[]
			int index = e - 'a';
			if (curr.children[index] == null) {
				// if the character key is not present insert it
				curr.children[index] = new TrieNode();
			}
			// go to the next character in the key
			curr = curr.children[index];
		}
		// set the last character as the end of a word
		curr.isEndOfWord = true;

	}

	// Returns true if key presents in trie, else false
	static boolean search(String key) {
		// pointer to current trie node
		TrieNode curr = root;

		// iterate through the given key as a char array
		for (char e : key.toCharArray()) {
			// convert the char to an index of children[]
			int index = e - 'a';
			if (curr.children[index] == null) {
				// if the current character of the key does not exist then the key isn't in the
				// trie
				return false;
			}
			// go to the next character in the key
			curr = curr.children[index];
		}
		// returns true if all the character were in the trie and the last character was
		// the end of a word
		return (curr.isEndOfWord);
	}

	// Driver
	public static void main(String args[]) {
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver" };

		String output[] = { "Not present in trie", "Present in trie" };

		root = new TrieNode();

		// Construct trie
		int i;
		for (i = 0; i < keys.length; i++) {
			insert(keys[i]);
		}

		// Search for different keys
		System.out.println("bank: " + output[search("bank") ? 1 : 0]);
		System.out.println("banking: " + output[search("banking") ? 1 : 0]);
		System.out.println("book: " + output[search("book") ? 1 : 0]);
		System.out.println("boo: " + output[search("boo") ? 1 : 0]);
		System.out.println("simple: " + output[search("simple") ? 1 : 0]);
		System.out.println("silver: " + output[search("silver") ? 1 : 0]);

	}

	// end of class
}