
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        FileResource resource = new FileResource();
        for(String s: resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index)+1;
                myFreqs.set(index,freq);
            }
        }
    }
    public int findIndexOfMax(){
        int indexOfMax = 0;
        int maxOccurance = 0;
        for(int i=0; i<myFreqs.size();i++){
            if(myFreqs.get(i)>maxOccurance){
                maxOccurance = myFreqs.get(i);
                indexOfMax = i;
            } 
        }         
        return indexOfMax;        
    }
    public void tester(){
        findUnique();
        int indexOfMax = findIndexOfMax();
        System.out.println("Number of unique words: " + myWords.size() +
            " The word that occurs most often and its count are: " + myWords.get(indexOfMax)
             + " " + myFreqs.get(indexOfMax));
        //for(int i=0; i<myWords.size();i++){
        //    System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        //}        
    }

}
