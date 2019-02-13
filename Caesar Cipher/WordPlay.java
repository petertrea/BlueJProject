
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WordPlay {
    public boolean isVowel(char ch){
        String vowel = "aeiouAEIOU";
        boolean isVowel = false;
        if (vowel.indexOf(ch) != -1) {
            isVowel = true;  
        }        
        return isVowel;
    }
    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = newPhrase.charAt(i);
            if (isVowel(currChar)){
                newPhrase.setCharAt(i,ch);
            }
        }
        return newPhrase.toString();
    }
    public String emphasize(String phrase, char ch) {
        //return a String is phrase but with the character ch (upperor or lowercase) //
        // replaced by '*' even index, '+" odd index;
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = newPhrase.charAt(i);
            if(Character.isUpperCase(currChar)) {
                if (currChar== ch) {
                    if (i%2 ==0){
                        newPhrase.setCharAt(i,'*');
                    } else {
                        newPhrase.setCharAt(i,'+');
                    }                        
                }
                currChar = Character.toLowerCase(currChar);
                if (currChar== ch) {
                    if (i%2 ==0){
                        newPhrase.setCharAt(i,'*');
                    } else {
                        newPhrase.setCharAt(i,'+');
                    }                        
                }
            }
            if(Character.isLowerCase(currChar)) {
                if (currChar== ch) {
                    if (i%2 ==0){
                        newPhrase.setCharAt(i,'*');
                    } else {
                        newPhrase.setCharAt(i,'+');
                    }                        
                }
                currChar = Character.toUpperCase(currChar);
                if (currChar== ch) {
                    if (i%2 ==0){
                        newPhrase.setCharAt(i,'*');
                    } else {
                        newPhrase.setCharAt(i,'+');
                    }                        
                }
            }
        }
        return newPhrase.toString();
    }
    public void testEmphasize(){ 
        String emphasize = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println("Mary Bella Abracadabra: " + emphasize);
    }
    public void testReplaceVowel(){
        String replaceVowels = replaceVowels("Hello World", '*');
        System.out.println("Hello World " + replaceVowels);
    } 
    public void testIsVowel(){
        boolean isVowel = isVowel('A');
        System.out.println("A is Vowel is " + isVowel);
    } 
}
