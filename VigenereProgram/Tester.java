import edu.duke.*;
import java.io.*;
import java.util.*;

public class Tester {
    public void CipherTester(){
        CaesarCipher Tester = new CaesarCipher(21);
        //FileResource fr = new FileResource();
        //String message =fr.asString();
        //String encrypted = Tester.encrypt(message);
        char encrypted = Tester.encryptLetter('Z');
        System.out.println(encrypted);
    } 
    public void CrackerTester(){
       CaesarCracker Tester = new CaesarCracker('a');
       FileResource fr = new FileResource();
       String message =fr.asString();
       String decrypted = Tester.decrypt(message);
       int key = Tester.getKey(message);
       System.out.println("Key is " + key);
       System.out.println(decrypted);
    }
    public void VigenereCipherTester(){
        int [] key = {17,14,12,4};
        VigenereCipher Tester = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String message =fr.asString();
        //String encrypted = Tester.encrypt(message);
        //System.out.println(encrypted);
        String decrypted = Tester.decrypt(message);
        System.out.println(decrypted);
    }
    public void VigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String message =fr.asString();    
        int[] key = new int[4];
        key = vb.tryKeyLength(message,4,'e');
        for (int i=0; i<4; i++){
            System.out.println(key[i]);
        }
        
    }
    public void testCommonCharIn(){
         VigenereBreaker vb = new VigenereBreaker();
         FileResource fr = new FileResource();
         HashSet<String> dictionary = vb.readDictionary(fr);
         char mostCommon = vb.mostCommonCharIn(dictionary);
         System.out.println("Most Common Char is " + mostCommon);
    }

}
