import java.io.*;
import java.util.*;

public class dtc 
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

        String ciphertext = encrypt(message, key);
        System.out.println("Encrypted: " + ciphertext);

        ciphertext = encrypt(ciphertext, key);
        System.out.println("Encrypted: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decrypted);

        decrypted = decrypt(decrypted, key);
        System.out.println("Decrypted: " + decrypted);

        // Writing into the file
        FileWriter fileWriter = new FileWriter("out.txt");  
        fileWriter.write(decrypted);
        fileWriter.close();     
    }
    
    public static String encrypt(String plaintext, int key)
    {
        int numRows = (int) Math.ceil((double) plaintext.length() / key);
        char[][] matrix = new char[numRows][key];

        int index = 0;
        for (int row = 0; row < numRows; row++) 
        {
            for (int col = 0; col < key; col++) 
            {
                if (index < plaintext.length()) 
                {
                    matrix[row][col] = plaintext.charAt(index);
                    index++;
                } 
                else 
                {
                    matrix[row][col] = ' ';
                }
            }
        }

        StringBuilder ciphertext = new StringBuilder();

        for (int col = 0; col < key; col++) 
        {
            for (int row = 0; row < numRows; row++) 
            {
                ciphertext.append(matrix[row][col]);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int key) 
    {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key);

        char[][] matrix = new char[numRows][key];

        int index = 0;
        for (int col = 0; col < key; col++) 
        {
            for (int row = 0; row < numRows; row++) 
            {
                matrix[row][col] = ciphertext.charAt(index);
                index++;
            }
        }
        StringBuilder plaintext = new StringBuilder();

        for (int row = 0; row < numRows; row++) 
        {
            for (int col = 0; col < key; col++) 
            {
                plaintext.append(matrix[row][col]);
            }
        }

        return plaintext.toString();
    }
    
}
