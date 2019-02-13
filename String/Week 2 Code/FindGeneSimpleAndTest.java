
/**
 * Write a description of FindGeneSimpleAndTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
    // start condon is "ATG"
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1){
        return "There is no Gene: no ATG found!";
    }
    // stop codon is "TAA" looking after "ATG" Position;
    int stopIndex = dna.indexOf("TAA", startIndex+3);
    if (stopIndex == -1){
        return "There is no Gene: no TAA found!";
    } else if((stopIndex-startIndex)%3 != 0){
        return "There is no valid Gene!";
    }
    String result = dna.substring(startIndex,stopIndex + 3);
    return result;
    }
    public void testFindGeneSimple(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA Strand is " + dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);    
        
        dna = "AATGCGTATATGGT";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "AATGCTAGGGGTAGTATATAAGGT";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "ATGTAA";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "ATTGTAA";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "ATGTTA";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "ATTGTTA";
        System.out.println("DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " + gene);
    }

}
