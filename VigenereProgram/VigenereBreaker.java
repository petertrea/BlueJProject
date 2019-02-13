import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //returns a String consisting of every totalSlices
        String sliceString = "";
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            sliceString += message.charAt(i);
        }    
        return sliceString;
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
       
        for (int i=0; i<klength; i++){
            String sliceStr = "";
            sliceStr = sliceString(encrypted,i,klength);
            //System.out.println(sliceStr);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i]=cc.getKey(sliceStr);
            //System.out.println(cc.getKey(sliceStr));
        }
        return key;
    }
    public void breakVigenere () {
        //VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("messages/secretmessage4.txt");
        String message =fr.asString();
        
        HashMap <String, HashSet<String>> dicMap = new HashMap <String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String language = f.getName();
            //System.out.println(language);
            FileResource fr1= new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr1);
            dicMap.put(language,dictionary);
        }
        breakForAllLanguages(message,dicMap);
                
        //FileResource dr = new FileResource("dictionaries/English");
        //int[] key = new int[4];
        //key = vb.tryKeyLength(message,4,'e');
        //HashSet<String> dictionary = readDictionary(dr);
        //String decrypted = breakForLanguage(message,dictionary);
        //VigenereCipher vc = new VigenereCipher(key);
        //String decrypted = vc.decrypt(message);
        //System.out.println(decrypted);        
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> messageInput = new HashSet<String>();
        for (String line : fr.lines()){
            messageInput.add(line.toLowerCase());
        }
        return messageInput;
    }
    //return the integer count of how many valid words it found
    public int countWords(String message,HashSet<String> dictionary){
        int count = 0;
        for (String w : message.split("\\W")){
            //turn all to lower case to compare
            w=w.toLowerCase();
            if (dictionary.contains(w)){
                count ++;
            }            
        }
        return count;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        String output = "";
        int count = 0;
        int keyLength = 0; 
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i=1; i<100; i++){
            int[] key = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            //System.out.println(decrypted);            
            if (countWords(decrypted, dictionary) > count){
                count = countWords(decrypted, dictionary);
                output = decrypted;
                keyLength = i;
                //System.out.println(count);
            }
        }
        System.out.println("max count are "+count+ "  key length is " + keyLength);
        return output;
    }  
    public char mostCommonCharIn(HashSet<String> dictionary){
        char mostCommon = 'a';
        int maxIndex = 0;
        int curIndex = 0;
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (String s: dictionary){
            //s = s.toLowerCase();
            for (int i=0; i<s.length(); i++ ){
                char ch = s.charAt(i);
                int index = alpha.indexOf(Character.toLowerCase(ch));
                if (index != -1) {
                    counters [index] += 1;
                }
            }        
        } 
        for (int i=0; i<26; i++){
            if (counters[i]>maxIndex){
                maxIndex = counters[i];
                curIndex = i;
            }
        }
        //System.out.println(counters);
        //System.out.println("maxIndex is " + curIndex);
        mostCommon = alpha.charAt(curIndex);
        return mostCommon;        
    }
    public void breakForAllLanguages(String encrypted,HashMap<String,HashSet<String>> languages){
        int counts = 0;  
        String output = "";
        String usedLanguage = "";
        for (String language: languages.keySet()){
            String decrypted = breakForLanguage(encrypted,languages.get(language));            
            if (countWords(decrypted,languages.get(language))>counts ){
                counts = countWords(decrypted,languages.get(language));
                output = decrypted;
                usedLanguage = language;
            }            
        }
        System.out.println("Encrypted Language is " + usedLanguage);
        System.out.println(output);
    }
}
