import java.math.*;
import java.util.*;

class RSA 
{
    public static void main(String args[])
    {

        BigInteger p = new BigInteger("47");
        BigInteger q = new BigInteger("71");

        BigInteger n = p.multiply(q);

        BigInteger z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
       
        BigInteger e;
        
        Random rand = new Random();

        do 
        {
            e = new BigInteger(z.bitLength(), rand);
        } 
        while (!e.gcd(z).equals(BigInteger.ONE));

        BigInteger d = e.modInverse(z);
        
        // The message to be encrypted
        String message = "6882326879666683";

        // The block size in bytes
        int blockSize = 3; // 1 to (n-1) in length

        // Split the message into blocks
        List<String> blocks = splitString(message, blockSize);

        // Encrypt each block
        List<BigInteger> encryptedBlocks = encryptBlock(n, e, blocks);
        System.out.println("Encrypted message: " + encryptedBlocks);
        // Decrypt each block
        String decryptedMessage = decryptBlock(n, d, encryptedBlocks);

        System.out.println("Decrypted message: " + decryptedMessage);

    }

    private static List<BigInteger> encryptBlock(BigInteger n, BigInteger e, List<String> blocks) 
    {
        List<BigInteger> encryptedBlocks = new ArrayList<>();

        for (String block : blocks) 
        {
            BigInteger msg = new BigInteger(block);

            BigInteger C = msg.modPow(e, n);

            encryptedBlocks.add(C);
        }

        return encryptedBlocks;
    }

    private static String decryptBlock(BigInteger n, BigInteger d, List<BigInteger> encryptedBlocks)
    {
        String decryptedMessage = "";

        for (BigInteger C : encryptedBlocks) 
        {
            BigInteger msgback = C.modPow(d, n);

            decryptedMessage += msgback.toString();
        }

        return decryptedMessage;
    }

    static BigInteger gcd(BigInteger e, BigInteger z) 
    {
        if (e.equals(BigInteger.ZERO)) 
            return z;
        else 
            return gcd(z.mod(e), e);
    }

    static List<String> splitString(String str, int size)
    {
        List<String> parts = new ArrayList<>();

        int length = str.length();

        for (int i = 0; i < length; i += size) 
        {
            parts.add(str.substring(i, Math.min(length, i + size)));
        }

        return parts;
    }
}

