
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    public WordsInFiles(){
        myMap = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            //If a word is not in the map, 
            if (!myMap.keySet().contains(word)){
                //create a new ArrayList of type String with this word, 
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                //have the word map to this ArrayList
                myMap.put(word,list);
            }else{
                //add the current filename to its ArrayList, unless the filename is already in the ArrayList. 
                if (!myMap.get(word).contains(f.getName())){
                    myMap.get(word).add(f.getName());
                }                
            }           
        }         
    }
    private void buildWordFileMap (){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
        /*
        for(String w : myMap.keySet()) {
            System.out.println(w + "\t" + myMap.get(w));
        }
        */
    }
    private int maxNumber(){
        int maxNumber = 0;
        for(String w : myMap.keySet()) {
            if(myMap.get(w).size() > maxNumber){
                maxNumber = myMap.get(w).size();
            }
            //System.out.println(w + "\t" + myMap.get(w));            
        } 
        return maxNumber;
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        for(String w : myMap.keySet()) {
            if(myMap.get(w).size() == number){
                list.add(w);
            } 
        }      
        return list;
    }
    private void printFilesIn (String word){
        System.out.println(word + " appears in the files: ");
        for(String w : myMap.keySet()) {
            if(w.equals(word)){
                for (String s : myMap.get(w)){
                System.out.println(s);}
            } 
        }     
    }
    public void tester(){
        buildWordFileMap ();
         System.out.println("total number of words is " + myMap.size());
        System.out.println("Max Number is " + maxNumber());
        int totalNumber = 0;
        
        for (String s: wordsInNumFiles(4)){
            //System.out.println(s);
            totalNumber++;
        }
        System.out.println("4 Number words are " + totalNumber );
        printFilesIn("tree");
    }

}
