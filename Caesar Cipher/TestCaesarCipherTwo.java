
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[]  countLetters(String s){
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
    private int maxIndex(int[] counters){
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
    private String halfOfString (String Message, int start) {
        String sb = "";
        // very good way to handle the situation!
        for (int i=start; i<Message.length(); i+=2){
           sb = sb + Message.charAt(i);
        }
        return sb;
    }
    private int getKey (String s) {
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex -4;
        if (maxDex <4){
            dkey = 26 - (4-maxDex);
        }        
        return dkey;
    }
     public void simpeTests (){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Encrypted"+"\t" + encrypted);
        String second =breakCaesarCipher(encrypted);
        System.out.println("Decrypted"+"\t" + second);
    }
    public String breakCaesarCipher (String input){
        String sb0 = halfOfString(input,0);
        String sb1 = halfOfString(input,1);        
        int key1 = getKey (sb0);
        int key2 = getKey (sb1);
        System.out.println("key1 = "+ key1 +"\t" + "key2 = "+ key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
        return cc.encrypt(input);
    }

}
