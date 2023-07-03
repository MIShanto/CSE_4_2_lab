import java.io.*;
import java.util.*;


public class caesar_cipher 
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
        int key = keyboardInput.nextInt();
        
        keyboardInput.close();

        String encryptedMessage = encrypt(message.toString(), key);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage, key);
        //System.out.println("Decrypted message: " + decryptedMessage);

        // Writing into the file
        FileWriter fileWriter = new FileWriter("out.txt");  
        fileWriter.write(decryptedMessage);
        fileWriter.close();        
    }
    
    public static String encrypt(String message, int key) 
    {
        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()) 
        {
            if (Character.isLetter(c)) 
            {
                if (Character.isUpperCase(c)) 
                    result.append((char) ('A' + (c - 'A' + key) % 26));
                else
                    result.append((char) ('a' + (c - 'a' + key) % 26));
            } 
            else 
                result.append(c);
        }

        return result.toString();
    }
    
    public static String decrypt(String encryptedMessage, int key) 
    {
        StringBuilder result = new StringBuilder();

        for (char c : encryptedMessage.toCharArray()) 
        {
            if (Character.isLetter(c)) 
            {
                if (Character.isUpperCase(c)) 
                    result.append((char) ('A' + (c - 'A' - key + 26) % 26));
                else
                    result.append((char) ('a' + (c - 'a' - key + 26) % 26));
            } 
            else
                result.append(c);
        }

        return result.toString();
    }
    
}


