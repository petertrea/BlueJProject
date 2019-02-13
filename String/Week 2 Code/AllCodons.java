
/**
 * Finding three stop codons.
 * 
 * @author Ping Cui 
 * @version 1.0
 */
import java.lang.Math;
public class AllCodons {
    
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex; 
        // Find stopCodon starting from (startIndex +3), call this result currIndex
        currIndex = dnaStr.indexOf(stopCodon, startIndex +3);
        // As long as currIndex is not equal to -1
        while (currIndex !=-1){
            // Check if (currIndex – startIndex ) is a muiltiple of 3
            if ((currIndex - startIndex) % 3 ==0) { 
                // If so, the current currIndex is your answer
                return currIndex;
            } else {
                // If not, update currIndex to the index of the next stopCodon starting from (currIndex +1)
                currIndex = dnaStr.indexOf(stopCodon, currIndex +1);
            }        
        }
        // Your Answer is dnaStr.length().      
        return dnaStr.length();
    }
    
    public String findGene(String dna){
        // Find first occurrence of "ATG", call its index startIndex;
        int startIndex = dna.indexOf("ATG");        
        if (startIndex == -1) { 
            // If startIndex is -1, then your answer is the empty string;
            return "";
        } else { 
            // findStopCodon(dnaStr, startIndex, "TAA") and call the result taaIndex;
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            // findStopCodon(dnaStr, startIndex, "TAG") and call the result tagIndex;
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            // findStopCodon(dnaStr, startIndex, "TGA") and call the result tgaIndex;
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            // Take the smallest of taaIndex, tagIndex, and tagIndex, call it minIndex;
            //int p1 = Math.min(taaIndex, tagIndex);
            //int p2 = Math.min(tagIndex, tgaIndex);
            int minIndex = Math.min(Math.min(taaIndex, tagIndex),Math.min(tagIndex, tgaIndex));
            // if minIndex is dnaStr.length(), your answer is the empty string;
            if (minIndex == dna.length()) { 
                return "";
            } else {
                // otherwise the answer is text from startIndex to minIndex + 3;
                return dna.substring(startIndex,minIndex+3);
            }
        }       
    }
    public void testFindStop(){
        //            01234567890123456789012345
        String dna = "TTTTTTTTTTAAGGGGGGGGGTAACC";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex !=9) System.out.println ("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex !=21) System.out.println ("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex !=26) System.out.println ("error on 26");
        dex = findStopCodon(dna,9,"TAg");
        if (dex !=26) System.out.println ("error on 26");
        
        System.out.println("tests finished!");
        
    }
}
