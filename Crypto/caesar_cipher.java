import java.util.Scanner;

public class caesar_cipher 
{
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message you want to encrypt: ");
        String message = scanner.nextLine();
        System.out.print("Enter the key: ");
        int key = scanner.nextInt();
        
        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
        
        scanner.close();
    }
    
    public static String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ('A' + (c - 'A' + key) % 26));
                } else {
                    result.append((char) ('a' + (c - 'a' + key) % 26));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    public static String decrypt(String encryptedMessage, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : encryptedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ('A' + (c - 'A' - key + 26) % 26));
                } else {
                    result.append((char) ('a' + (c - 'a' - key + 26) % 26));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
}


