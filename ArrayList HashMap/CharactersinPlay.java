
/**
 * Write a description of CharactersinPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersinPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersinPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    private void update(String person){
        int index = names.indexOf(person);
        if (index == -1){
           names.add(person);
           counts.add(1);
           } else {
           int freq = counts.get(index)+1;
           counts.set(index,freq);
        }
    }
    public void findAllCharacters(){
         FileResource resource = new FileResource();
         //clear the appropriate instance variables before each new file.
         names.clear();
         counts.clear();
         for(String s: resource.lines()){
             int pointPosition = s.indexOf('.');
             if (pointPosition != -1){ 
                String name = s.substring(0,pointPosition);
                update(name);
             }
         }
    }
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        for(int i=0; i<names.size();i++){
            if (num2 >=counts.get(i) && counts.get(i)>= num1){
                System.out.println("Main Character: " + names.get(i) + "\t" + counts.get(i));
            }
        }   
        
    } 
    public void tester(){
        charactersWithNumParts(2, 500);
        /*
        findAllCharacters();
        for(int i=0; i<names.size();i++){
            if (counts.get(i)>30){
                System.out.println("Main Character: " + names.get(i) + "\t" + counts.get(i));
            }
        } */     
    }

}
