import java.math.*;

public class Diffie 
{
    /* 
    public static BigInteger bigmod(BigInteger n, BigInteger r, BigInteger m) 
    {
        if (r.equals(BigInteger.ZERO)) 
            return BigInteger.ONE;

        if (r.equals(BigInteger.ONE)) 
            return n.mod(m);

        BigInteger x;

        if (r.mod(new BigInteger("2")).equals(BigInteger.ONE)) 
            x = bigmod(n, r.subtract(BigInteger.ONE), m).multiply(n);
        else 
        {
            x = bigmod(n, r.divide(new BigInteger("2")), m);
            x = x.multiply(x);
        }

        return x.mod(m);
    }
    */

    public static void main(String[] args) 
    {
        BigInteger p = new BigInteger("353");
        BigInteger r = new BigInteger("3");

        BigInteger a = new BigInteger("97"); // alice private key
        BigInteger b = new BigInteger("233"); // bobs private key

        BigInteger x = r.modPow(a, p);
        BigInteger y = r.modPow(b, p);
        //BigInteger x = bigmod(r, a, p);
        //BigInteger y = bigmod(r, b, p);

        BigInteger ka = y.modPow(a, p);
        BigInteger kb = x.modPow(b, p);
        //BigInteger ka = bigmod(y, a, p);
        //BigInteger kb = bigmod(x, b, p);

        System.out.println("Alice's key: " + ka);
        System.out.println("Bob's key: " + kb);
    }
}
