
public class SubStringSearch {

	// function that returns the index of a string where a pattern of characters
	// starts
	public static int bruteForce(String Text, String Pat) {
		// placeholders for the length of the strings
		int n = Text.length();
		int m = Pat.length();

		// if the string cannot contain the pattern return -1
		if (n < m) {
			return -1;
		}

		// loop through each substring of length m in the string we are searching
		for (int pos = 0; pos < n - m + 1; pos++) {
			// compare the characters to the pattern's characters at index i in Text
			int i;
			for (i = 0; i < m; i++) {
				if (Text.charAt(i + pos) != Pat.charAt(i)) {
					break;
				}
			}
			// if there was a match found return the index
			if (i == m) {
				return pos;
			}
		}
		// when the pattern is not found return -1
		return -1;
	}

	// KMPSearch designed using pseudo-code and git repository
	public static int KMPSearch(String txt, String pat) {
		int M = pat.length();
		int N = txt.length();

		// return -1 if pattern cannot be found in txt
		if (M > N) {
			return -1;
		}

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[] array)
		computeLPSArray(pat, M, lps);

		// use i as index for txt
		int i = 0;
		// loop through txt until the end
		while (i < N) {

			// if the characters match increase the indexes of txt and pattern
			if (pat.charAt(j) == txt.charAt(i)) {
				i++;
				j++;
			}

			// if the index of pattern reaches the end then the word has been found, return
			// the index of txt where it starts
			if (j == M) {
				return i - M;
			}

			// if no match is found after j attempts, calculate the jump when j>0, otherwise
			// increment i
			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j > 0) {
					j = lps[j - 1];
				} else
					i++;
			}
		}
		return -1;
	}

	public static void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];

					// Also, note that we do not increment
					// i here
				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}

	// main used to test the functions
	public static void main(String[] args) {

		String pattern = "pattern";
		String string = "String used to search for patterns";
		int sub = bruteForce(string, pattern);
		if (sub > -1) {
			System.out.println("Pattern found at index " + sub + ": " + string.substring(sub, sub + pattern.length()));
		} else {
			System.out.println(pattern + " not found in " + string);
		}
		sub = -1;
		sub = KMPSearch(string, pattern);
		if (sub > -1) {
			System.out.println("Pattern found at index " + sub + ": " + string.substring(sub, sub + pattern.length()));
		} else {
			System.out.println(pattern + " not found in \"" + string + "\"");
		}
	}
}
