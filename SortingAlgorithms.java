
public class SortingAlgorithms {

	// selection sort method
	public static void selection(int a[]) {

		// loop through the array
		for (int i = 0; i < a.length - 1; i++) {
			// set the minimum value's index to i
			int min = i;
			// nested loop to iterate through the array
			for (int j = i + 1; j < a.length; j++) {
				// find the minimum value in the array by comparing each value to current min
				if (a[j] < a[min]) {
					min = j;
				}
			}
			// swap the minimum element found with index i
			int val = a[min];
			a[min] = a[i];
			a[i] = val;
		}
	}

	// insertion sort method
	public static void insertion(int a[]) {
		// iterate through the array
		for (int i = 1; i < a.length; ++i) {
			// hold the value that is in index i
			int temp = a[i];
			// variable j to hold the index before i
			int j = i - 1;
			// loop until j=0 or value at index j is greater than temp
			while (j >= 0 && a[j] > temp) {
				// make the value after the current j = current j
				a[j + 1] = a[j];
				j -= 1;
			}
			// the index after the final j is now given the temp value
			a[j + 1] = temp;
		}
	}

	// using given shuffle method
	public static void shuffle(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// choose index uniformly in [0, i]
			int r = (int) (Math.random() * (i + 1));
			int swap = a[r];
			a[r] = a[i];
			a[i] = swap;
		}
	}

	// helper function used for bogoSort
	public static boolean isSorted(int a[]) {

		for (int i = 0; i < a.length - 1; ++i) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	// bogo sort method
	public static void bogo(int a[]) {
		// randomly rearrange the array until it is sorted
		while (!isSorted(a)) {
			shuffle(a);
		}
	}

	// helper function to print an array
	public static void printArray(int a[]) {

		String array = "";
		for (int i = 0; i < a.length - 1; i++) {
			array += a[i] + ", ";
		}
		array += a[a.length - 1];
		System.out.println(array);
	}

	public static void main(String[] args) {
		int intArray[] = { 5, 3, 2, 6, 1, 4 };
		printArray(intArray);
		bogo(intArray);
		printArray(intArray);

	}
}
