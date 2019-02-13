
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneWhile {
    public String findGene(String dna){
        // Find first occurrence of "ATG" and call its index startIndex
        int startIndex = dna.indexOf("ATG");
        // Find the "TAA" starting from (startIndex +3, call this result currIndex
        int currIndex = dna.indexOf("TAA", startIndex+ 3);
        // As long as currInex is not equal to -1
        while (currIndex != -1) {
            // Check if (currIndex - startIndex) is a multiple of 3
            if ((currIndex - startIndex)%3 == 0) {
                // if so, the text between startIndex and currIndex +3 is your answer
                return dna.substring(startIndex, currIndex +3); 
            } else { 
                // if not, update currIndex to the Index of the new "TAA",Starting from (currIndex +1)
                currIndex = dna.indexOf("TAA", currIndex+1 );            
            } 
        
        }        
        // Your answer is the empty string
        return "";
    }
    public void testFindGene(){
        String dna = "AATGCAGTAATATGGT";
        System.out.println("DNA Strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is: " + gene);    
        
        dna = "AATGCGTATATGGT";
        System.out.println("DNA Strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "AATGCTAGGGGTAAGTAATATAAGGT";
        System.out.println("DNA Strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is: " + gene);
    
    }

}
