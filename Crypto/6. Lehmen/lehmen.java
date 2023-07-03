import java.math.*;

class lehmen
{
	static int lehmann(BigInteger n, int t)
	{

    // generating a random base less than n
    BigInteger a = new BigInteger(n.subtract(BigInteger.valueOf(3)).toString()).add(BigInteger.valueOf(2));
    
    // calculating exponent
    BigInteger e = n.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));

    // iterate to check for different base values
    // for given number of tries 't'
    while(t > 0)
    {
        BigInteger x = a.modPow(e, n);

        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) // In modular world checking -1 and n-1 is same. 
        {
            a = new BigInteger(n.subtract(BigInteger.valueOf(3)).toString()).add(BigInteger.valueOf(2));
            t -= 1;
        }
        // else return negative
        else
            return -1;

    }

    // return positive after attempting
    return 1;
}

// Driver code
public static void main (String[] args)
{
    BigInteger n = BigInteger.valueOf(127); // number to be tested
    int t = 10; // number of tries

    // if n is 2, it is prime
    if(n.equals(BigInteger.valueOf(2)))
        System.out.println("2 is Prime.");

    // if even, it is composite
    if(n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
        System.out.println(n + " is Composite");

    // if odd, check
    else
    {
        int flag = lehmann(n, t);

        if(flag == 1)
            System.out.println(n + " may be Prime.");

        else
            System.out.println(n + " is Composite.");
    }
}
}