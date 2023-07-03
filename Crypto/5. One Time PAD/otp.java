import java.io.*;
import java.util.*;

public class otp 
{
    public static void main(String[] args) throws IOException
    {
        String message = "";

        System.out.print("Enter the message you want to encrypt: ");
        
        Scanner keyboardInput = new Scanner(System.in);

        //message = keyboardInput.nextLine(); // for input from CLI


        //For input from file 
        //start..
        FileReader fileReader = new FileReader("in.txt"); 

        int line;
        while((line = fileReader.read()) != -1)    
            message += (char)line; 

        fileReader.close();    
        //end..

        System.out.print("Enter the key: ");
        String key = keyboardInput.nextLine();

        keyboardInput.close();

        String ciphertext = encrypt(message, key);
        System.out.println("Encrypted: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decrypted);

        // Writing into the file
        FileWriter fileWriter = new FileWriter("out.txt");  
        fileWriter.write(decrypted);
        fileWriter.close();     
    }
    
    public static String encrypt(String text, String key)
	{
		String cipherText = "";

		for (int i = 0; i < key.length(); i++) 
        {
			cipherText += (char)(((text.charAt(i) - 'A'+ key.charAt(i)- 'A') % 26) + 'A');
		}

		return cipherText;
	}

    public static String decrypt(String s, String key)
	{
		String plainText = "";

		for (int i = 0; i < key.length(); i++) 
        {
			plainText += (char)(((s.charAt(i) - 'A' - (key.charAt(i) - 'A') + 26) % 26 ) + 'A');
		}

		return plainText;
	}
    
}
