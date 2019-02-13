
/**
 * Write a description of WorldLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WorldLengths {
    public int[] countWorldLengths (FileResource resource, int[] counts){
        //read in the words from resource 
        for(String word: resource.words()){
            //count the number of words of each length
            int length = word.length();            
            if (!Character.isLetter(word.charAt(length-1))&&(length>1)){
                length = length -1;
            }
            if (!Character.isLetter(word.charAt(0))){
                length = length -1;
            }   
            counts[length] += 1;
            //System.out.println (word + "\t" + length);
        }
        return counts;
    }
    public int indexOfMax (int[] values){
        int indexOfMax = 0;
        int maxValue = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > maxValue){
                indexOfMax = i;
                maxValue = values[i];
            }
        }
        System.out.println ("Max Value is " + maxValue);
        return indexOfMax;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource ();
        int[] counts = new int[31];
        int[] count = countWorldLengths(fr,counts);
        int indexOfMax = indexOfMax(count);
        System.out.println("indexOfMax is " + indexOfMax);
    }

}
