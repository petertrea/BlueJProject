
/**
 * Write a description of DeCryptCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[]  countLetters(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int i=0; i<s.length(); i++ ){
            char ch = s.charAt(i);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counters [index] += 1;
            }
        }
        for (int i=0; i<alpha.length(); i++ ){
            //System.out.println(alpha.charAt(i) + "\t" + counters[i]);
        }
        return counters;
    }
    public int maxIndex(int[] counters){
        int maxIndex = 0;
        int maxCount = 0;
        for (int i=0; i< counters.length; i++){
            if (counters[i] > maxCount){
                maxCount = counters[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public String decrypt(String encrypted){
        
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        //System.out.println("maxDex is " + maxDex);
        int dkey = maxDex -4;
        if (maxDex <4){
            dkey = 26 - (4-maxDex);
        }
        //CaesarCipher is a java class
        CaesarCipher cc = new CaesarCipher(26-dkey);
        //System.out.println(cc.encrypt(encrypted));
        return cc.encrypt(encrypted);
    }
    public String halfOfString (String Message, int start) {
        String sb = "";
        // very good way to handle the situation!
        for (int i=start; i<Message.length(); i+=2){
           sb = sb + Message.charAt(i);
        }
        return sb;
    }
    public int getKey (String s) {
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex -4;
        if (maxDex <4){
            dkey = 26 - (4-maxDex);
        }        
        return dkey;
    }
    public String decryptTwoKeys(String encrypted){
        String sb0 = halfOfString(encrypted,0);
        String sb1 = halfOfString(encrypted,1);        
        int key1 = getKey (sb0);
        int key2 = getKey (sb1);
        System.out.println("key1 = "+ key1 +"\t" + "key2 = "+ key2);
        CaesarCipher cc = new CaesarCipher(26-key1, 26-key2);
        return cc.encryptTwoKeys(encrypted); 
    }
    /*
    public void eyeballDecrpt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        for (int i=0; i< 26; i++){
            String s = cc.encrypt(encrypted,i);
            System.out.println(i+ "\t" +s);
        }    
    }
    */
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = decryptTwoKeys(message);
        //String decrypted = decrypt(message);
        //eyeballDecrpt(message);
        System.out.println(decrypted);
        //String decrypted = encryptTwoKeys(encrypted, 26-key);
        //String halfString = halfOfString(message,0);
        //System.out.println(halfString);
        System.out.println(message);
    }
}

