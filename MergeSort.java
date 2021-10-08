
public class MergeSort {

	// method to merge two halves of an array
	void merge(int arr[], int lo, int mid, int hi) {
		// get the sizes of each half
		int size1 = mid - lo + 1;
		int size2 = hi - mid;

		// initialise temporary arrays for the left and right half
		int left[] = new int[size1];
		int right[] = new int[size2];

		// fill the arrays with their elements
		for (int i = 0; i < size1; ++i)
			left[i] = arr[lo + i];
		for (int j = 0; j < size2; ++j)
			right[j] = arr[mid + 1 + j];

		// variables to serve as pointers in the array
		int i = 0, j = 0, k = lo;

		// sort the array by comparing the elements of both halves
		while (i < size1 && j < size2) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		// finish sorting the left if not done
		while (i < size1) {
			arr[k] = left[i];
			i++;
			k++;
		}
		// finish sorting the right
		while (j < size2) {
			arr[k] = right[j];
			j++;
			k++;
		}
	}

	void mergeSort(int arr[], int lo, int hi) {

		if (lo < hi) {
			// Find the middle point
			int mid = lo + (hi - lo) / 2;

			// Sort both halves
			mergeSort(arr, lo, mid);
			mergeSort(arr, mid + 1, hi);

			// Merge the sorted halves
			merge(arr, lo, mid, hi);
		}
	}

	void enhancedMergeSort(int arr[], int lo, int hi) {
		// use insertion sort with a cutoff of 10 for small arrays
		if (hi <= lo + 10) {
			insertion(arr, lo, hi);
			return;
		}
		if (lo < hi) {
			// Find the middle point
			int mid = lo + (hi - lo) / 2;

			// Sort both halves
			mergeSort(arr, lo, mid);
			mergeSort(arr, mid + 1, hi);

			// Merge the sorted halves if needs be
			if (arr[mid] > arr[mid + 1]) {
				merge(arr, lo, mid, hi);
			}

		}
	}

	// insertion sort function from previous weeks
	public static void insertion(int a[], int start, int end) {

		for (int i = start; i < end; ++i) {

			int temp = a[i];
			int j = i - 1;

			while (j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				j -= 1;
			}
			a[j + 1] = temp;
		}
	}

	// shuffle helper function
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

	// helper function used for testing
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// main method used to time the algorithms and test them
	public static void main(String[] args) {

		MergeSort MS = new MergeSort();

		int[] test = new int[10000000];
		for (int i = 0; i < test.length; i++) {
			test[i] = i;
		}
		shuffle(test);
		int[] test2 = test.clone();
		Stopwatch sw = new Stopwatch();
		double time = 0;

		MS.mergeSort(test, 0, test.length - 1);
		time = sw.elapsedTime();
		System.out.println("Sorting took " + time + " seconds for mergeSort");

		Stopwatch sw1 = new Stopwatch();
		MS.enhancedMergeSort(test2, 0, test2.length - 1);
		time = sw1.elapsedTime();
		System.out.println("Sorting took " + time + " seconds for enhancedMergeSort");
	}
}
