
public class Fibonacci {

	// iterative version of the fibonacci algorithm
	static int fibonacciIterative(int n) {
		// if n is less than or equal to 1 return n
		if (n <= 1)
			return n;
		// variables for the current and the previous values
		int fib = 1;
		int prevFib = 1;

		// loop until we have added two values n times
		for (int i = 2; i < n; i++) {
			// save the current value of fib which will become the previous number
			int temp = fib;
			// the new fib equals the current plus the previous values
			fib = fib + prevFib;
			prevFib = temp;
		}
		// return the final result obtained
		return fib;
	}

	// recursive function of the fibonacci algorithm
	static int fibRecursive(int n) {
		// return n if less than or equal 1
		if (n <= 1)
			return n;
		// otherwise recursively return the sum of n-1 and n-2
		else
			return fibRecursive(n - 1) + fibRecursive(n - 2);
	}

	// main function used to test the methods
	public static void main(String[] args) {
		int i = 35;

		Stopwatch sRec = new Stopwatch();
		System.out.println(fibRecursive(i) + "\nTime taken for recursive function: " + sRec.elapsedTime());

		Stopwatch sIter = new Stopwatch();
		System.out.println(fibonacciIterative(i) + "\nTime taken for iterative function: " + sIter.elapsedTime());

	}
}
