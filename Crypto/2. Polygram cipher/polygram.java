import java.io.*;
import java.util.*;

public class polygram {

    static Map<String, String> encryptionMap = new HashMap<>();
    static Map<String, String> decryptionMap = new HashMap<>();

    static void CreateMap() throws FileNotFoundException 
    {
        Scanner in = new Scanner(new File("dictionary.txt"));
            while (in.hasNext()) 
            {
                String w1 = in.next(), w2 = in.next();
                encryptionMap.put(w1, w2);
                decryptionMap.put(w2, w1);
            }
            
            in.close();
    }

    static String encrypt(String msg) 
    {
        String cipherText = "", token = "";

        for (char ch : msg.toCharArray()) 
        {
            if (Character.isLetter(ch)) 
            {
                token += ch;

                if (encryptionMap.containsKey(token)) 
                {
                    cipherText += encryptionMap.get(token);

                    token = "";
                }
            } 
            else
                cipherText += ch;
        }

        return cipherText;
    }

    static String decrypt(String cipherText) 
    {
        String message = "", token = "";

        for (char ch : cipherText.toCharArray())
         {
            if (Character.isLetter(ch)) 
            {
                token += ch;

                if (decryptionMap.containsKey(token)) 
                {
                    message += decryptionMap.get(token);

                    token = "";
                }
            } 
            else
                message += ch;
        }

        return message;
    }

    public static void main(String[] args) throws IOException
    {
        CreateMap();

        String message = "";

        System.out.print("Enter the message you want to encrypt: ");
        
        //Scanner keyboardInput = new Scanner(System.in);
        //message = keyboardInput.nextLine(); // for input from CLI

        //For input from file 
        //start..
        FileReader fileReader = new FileReader("in.txt"); 

        int line;
        while((line = fileReader.read()) != -1)    
            message += (char)line; 

        fileReader.close();    
        //end..
        
        String encryptedMessage = encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage);
        //System.out.println("Decrypted message: " + decryptedMessage);

        // Writing into the file
        FileWriter fileWriter = new FileWriter("out.txt");  
        fileWriter.write(decryptedMessage);
        fileWriter.close();
    }
}
