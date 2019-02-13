
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount(){
	initialMap();
    }
    private void initialMap(){
        map = new HashMap<String, Integer>() ;
    }
    public void buildCodonMaps (int start, String dna){
        //Get rid of white space
        dna = dna.trim();
        map.clear();
        for(int i=start; i<dna.length()-2; i+=3){
            String w=dna.substring(i,i+3);
            //how to use map!
            if (map.keySet().contains(w)){
                map.put(w,map.get(w)+1);
            } else {
                map.put(w,1);
            }
        }
        for(String w : map.keySet()) {
            int occurrences = map.get(w);
            if (occurrences > 0){
                //System.out.println(occurrences + "\t" + w);               
            }
        }
        System.out.println("dna.length()" + dna.length());
    }
    public String getMostCommonCodon (){
        int maxOccur = 0; 
        String maxString = null;
        for(String w : map.keySet()) {            
            if (map.get(w) > maxOccur){
                maxOccur = map.get(w);
                maxString = w;
            }
        }
        //System.out.println(maxString + "\t" + maxOccur);
        return maxString;
    }
    public void printCodonCounts(int start, int stop) {
        System.out.println("Counts of codons between 1 and 5 inclusive are: ");
        for(String w : map.keySet()) {            
            if (stop >= map.get(w) && map.get(w) >= start){                
                System.out.println( w + "\t" + map.get(w));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        s = s.toUpperCase();
        for (int i=0; i<3; i++){
            buildCodonMaps(i,s);
            System.out.println("Reading frame starting with "+i+" results in "+map.size() + " unique codons");
            String max = getMostCommonCodon ();
            System.out.println("and most common codon is  "+ max + " with count " + map.get(max));
            printCodonCounts(1,7);
            System.out.println("\n");            
        }
            
    }

}
