import java.util.Arrays;

public class QuickSort {

	// partition which returns the index of the partition value chosen
	private int partition(int[] Array, int Start, int End) {
		// make the pivot value the value at the end of the array to start
		int pivot = Array[End];
		// create the pivot index we use to point to our current index
		int pi = Start - 1;
		// loop through the array
		for (int i = Start; i < End; i++) {
			// if the value at index i is less than or equal to the pivot we want
			// to swap the values and increment our pivot index
			if (Array[i] <= pivot) {
				pi++;
				Swap(Array, pi, i);
			}
		}
		// swap the final pivot index with the end index of the array as that was our
		// chosen pivot
		Swap(Array, pi + 1, End);
		return pi + 1;
	}

	// recursive function to sort an array
	private void quickSort(int[] Array, int start, int end) {
		if (start < end) {
			int pivot = partition(Array, start, end);
			quickSort(Array, start, pivot - 1);
			quickSort(Array, pivot + 1, end);
		}
	}

	// helper function to swap index a and b in an int array
	private static void Swap(int[] Array, int a, int b) {
		int temp = Array[a];
		Array[a] = Array[b];
		Array[b] = temp;
	}

	// helper function to get the median value of an array's first, last and middle
	// value
	private static int getMedian(int[] array, int left, int right) {
		int center = (left + right) / 2;

		if (array[left] > array[center])
			Swap(array, left, center);

		if (array[left] > array[right])
			Swap(array, left, right);

		if (array[center] > array[right])
			Swap(array, center, right);

		Swap(array, center, right);
		return array[right];
	}

	// a better version of partition above
	private int enhancedPartition(int[] Array, int start, int end, int pivot) {

		int pi = start - 1;
		// iterate through the array
		for (int i = start; i < end; i++) {
			// swap the current pivot value if needs be
			if (Array[i] <= pivot) {
				pi++;
				Swap(Array, pi, i);
			}
		}
		Swap(Array, pi + 1, end);
		return pi + 1;
	}

	// enhanced version of quicksort above
	private void enhancedQuickSort(int[] Array, int start, int end) {
		// insertion sort if the array is small
		if (Array.length < 10) {
			insertion(Array);
			return;
		}

		// use the median value of the first, last and middle of the array as a pivot
		// value
		if (start < end) {
			int median = getMedian(Array, start, end);
			int pivot = enhancedPartition(Array, start, end, median);
			enhancedQuickSort(Array, start, pivot - 1);
			enhancedQuickSort(Array, pivot + 1, end);
		}
	}

	// helper function to randomise array
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

	// helper function of insertion sort
	public static void insertion(int array[]) {
		int n = array.length;
		for (int j = 1; j < n; j++) {
			int key = array[j];
			int i = j - 1;
			while ((i > -1) && (array[i] > key)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	// function to print array for testing
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static boolean isSorted(int a[]) {

		for (int i = 0; i < a.length - 1; ++i) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		int[] test = new int[100000];
		for (int i = 0; i < test.length; i++) {
			test[i] = i;
		}
		shuffle(test);
		int[] test2 = test.clone();
		QuickSort QS = new QuickSort();
		double time = 0;
		Stopwatch sw = new Stopwatch();
		QS.enhancedQuickSort(test, 0, test.length - 1);
		time = sw.elapsedTime();
		System.out.println("Sorting took " + time + " seconds for enhancedQuickSort ");

		Stopwatch sw1 = new Stopwatch();
		QS.quickSort(test2, 0, test2.length - 1);
		time = sw1.elapsedTime();
		System.out.println("Sorting took " + time + " seconds for quickSort");
	}
}
