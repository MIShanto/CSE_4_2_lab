import java.math.*;

class RobinMiller 
{
	// This function is called for all k trials.
	// It returns false if n is composite and
	// returns false if n is probably prime.
	// d is an odd number such that d*2<sup>r</sup>
	// = n-1 for some r >= 1

	static boolean miillerTest(BigInteger d, BigInteger n) 
	{

    // Pick a random number in [2..n-2]
    // Corner cases make sure that n > 4
    BigInteger a = new BigInteger(String.valueOf((int)Math.random())).mod(n.subtract(new BigInteger("4")))
	.add(new BigInteger("2"));

    // Compute a^d % n
    BigInteger x = a.modPow(d, n);

    if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE)))
        return true;

    // Keep squaring x while one of the following doesn't happen
    // (i) d does not reach n-1
    // (ii) (x^2) % n is not 1
    // (iii) (x^2) % n is not n-1
    while (!d.equals(n.subtract(BigInteger.ONE))) 
    {
        x = x.multiply(x).mod(n);
        d = d.multiply(BigInteger.valueOf(2));

        if (x.equals(BigInteger.ONE))
            return false;
        if (x.equals(n.subtract(BigInteger.ONE)))
            return true;
    }

    // Return composite
    return false;

	}

	// It returns false if n is composite
	// and returns true if n is probably
	// prime. k is an input parameter that
	// determines accuracy level. Higher
	// value of k indicates more accuracy.
	static boolean isPrime(BigInteger n, int k) {

		// Corner cases
		if (n.compareTo(BigInteger.ONE) <= 0 || n.equals(BigInteger.valueOf(4)))
			return false;
		if (n.compareTo(BigInteger.valueOf(3)) <= 0)
			return true;
	
		// Find r such that n = 2^d * r + 1 for some r >= 1
		BigInteger d = n.subtract(BigInteger.ONE);
	
		while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
			d = d.divide(BigInteger.valueOf(2));
	
		// Iterate given number of 'k' times
		for (int i = 0; i < k; i++)
			if (!miillerTest(d, n))
				return false;
	
		return true;
	}	
	// Driver program
	public static void main(String args[]) 
	{

		int k = 4; // Number of iterations

		System.out.println("All primes smaller than 100: ");

		//isPrime(13, k);
		for (int n = 1; n < 100; n++) {
			if (isPrime(BigInteger.valueOf(n), k))
				System.out.print(n + " ");
		}
	}
}