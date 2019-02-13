
/**
 * Write a description of CharCount here.
 * This Class for count 26 Characters occrances;
 * Ping CUi 
 * @version 1.0 Jan 29, 2019
 */
public class CharCount {
    public void charCount(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int i=0; i<s.length(); i++ ){
            char ch = s.charAt(i);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counters [index] += 1;
            }
        }
        for (int i=0; i<s.length(); i++ ){
            System.out.println(alpha.charAt(i) + "\t" + counters[i]);
        }
    }

}
