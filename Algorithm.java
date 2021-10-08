import java.math.*;
import java.util.Scanner;

public class Algorithm {

	// calculate method takes in two BigInts to multiply
	private BigInteger calculate(BigInteger a, BigInteger b) {
		// initialising result with a value of 0
		BigInteger res = BigInteger.ZERO;
		// creating a BigInt to store the value 2 used to check for an even int and to
		// double and half values
		BigInteger two = BigInteger.valueOf(2);
		// signum returns 1 while b is positive
		while (b.signum() == 1) {
			// check if b is an even or odd number
			if (b.mod(two) != BigInteger.ZERO) {
				// if even add a to the result
				res = res.add(a);
			}
			// double a and half b every loop iteration
			a = a.multiply(two);
			b = b.divide(two);
		}
		// return the final result when b reaches 0
		return res;
	}

	public static void main(String[] args) {
		Algorithm c = new Algorithm();
		// ask for input
		System.out.println("Please enter two integers");
		Scanner in = new Scanner(System.in);
		// take in input using scanner and store in x and y
		BigInteger x = in.nextBigInteger();
		BigInteger y = in.nextBigInteger();
		// start timer to check the calculation time
		Stopwatch timer = new Stopwatch();
		// call the method and print the result
		System.out.println("The product of " + x + " and " + y + " is " + c.calculate(x, y));
		// stop the timer and print results
		System.out.println("elapsed time = " + timer.elapsedTime());
		in.close();
	}

}
