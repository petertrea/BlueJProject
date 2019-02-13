
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private int mainKey1 = 17 ; 
    private int mainKey2 = 3 ; 
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    public CaesarCipherTwo(int key1, int key2){ 
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    }
    public String encrypt(String input){
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i=0; i<encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);        
            //Find the index of currChar in the alphabet (call it idx)
            int idx = -1;
            char newChar =' ';
            if((Character.isUpperCase(currChar))){
                 idx = alphabet.indexOf(currChar); 
                 //If currChar is in the alphabet
                 if (idx != -1) {
                     //Get the idxth character of shiftedAlphabet (newChar)
                     if (i%2 == 0){
                         newChar = shiftedAlphabet1.charAt(idx);
                     } else{
                         newChar = shiftedAlphabet2.charAt(idx);    
                     }
                 }
                     encrypted.setCharAt(i,newChar);
            }  //Otherwise: do nothing 
            
            if((Character.isLowerCase(currChar))){
                 currChar = Character.toUpperCase(currChar);
                 idx = alphabet.indexOf(currChar); 
                 if (idx != -1) {
                     if (i%2 == 0){
                         newChar = shiftedAlphabet1.charAt(idx);
                     } else{
                         newChar = shiftedAlphabet2.charAt(idx);    
                     }
                     newChar =  Character.toLowerCase(newChar);
                     encrypted.setCharAt(i,newChar);
                 }
            }                     
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }   
     public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1,26-mainKey2); 
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
    

}
