
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
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
    public void simpeTests (){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Encrypted"+"\t" + encrypted);
        String second =breakCaesarCipher(encrypted);
        System.out.println("Decrypted"+"\t" + second);
    }
    public String breakCaesarCipher (String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        //System.out.println("maxDex is " + maxDex);
        int dkey = maxDex -4;
        if (maxDex <4){
            dkey = 26 - (4-maxDex);
        }
        //CaesarCipher is a java class
        CaesarCipher cc = new CaesarCipher(26-dkey);
        //System.out.println(cc.encrypt(encrypted));
        return cc.encrypt(input);
    }

}
