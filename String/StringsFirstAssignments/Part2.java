
/**
 * Part 2: Finding a Gene - Using the Simplified Algorithm Reorganized
 * This assignment will determine if a DNA strand has a gene in it 
 * by using the simplified algorithm from the lesson, 
 * but organizing the code in a slightly different way.
 * You will modify the method findSimpleGene to have three parameters,
 * one for the DNA string, one for the start codon and one for the stop codon.
 * Specifically, you should do the following:
 * 1. Create a new Java Class named Part2 in the StringsFirstAssignments project.
 * 2. Copy and paste the two methods findSimpleGene and testSimpleGene 
 * from the Part1 class into the Part2 class.
 * 
 * @author (Ping Cui) 
 * @version (2019 Jan 12)
 */
public class Part2 {
    // The method findSimpleGene has one parameter for the DNA string named dna.
    // Modify findSimpleGene to add two additional parameters, 
    //  one named startCodon for the start codon and one named stopCodon for the stop codon.
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        /* 
         * Modify the findSimpleGene method to work with DNA strings that are 
         * either all uppercase letters such as “ATGGGTTAAGTC”
         * or all lowercase letters such as “gatgctataat”. 
        */
       // Becuase of it's dna string, start codon atg, stop codon taa; so, use letter"a" do the test;
        int upOrLower = dna.indexOf("a");
        String DNA;
        String startCodonUp;
        String stopCodonUp;
        // Found lower case
        if (upOrLower != -1) {
            DNA = dna.toUpperCase();
            startCodonUp = startCodon.toUpperCase();
            stopCodonUp = stopCodon.toUpperCase();
        }else {
            // Not lower case
            DNA = dna;
            startCodonUp = startCodon;
            stopCodonUp = stopCodon;
        }       
        
        int start = DNA.indexOf(startCodonUp);
        if(start == -1){
            return " No startCodon";
        }
        int stop = DNA.indexOf(stopCodonUp,start+3);
        
        if ((stop-start)%3 == 0) {
            String outString =DNA.substring(start, stop+3);
            // Found lower case;
            if (upOrLower !=-1){
                return outString.toLowerCase();
            }else{
                return outString;
            }
        }else {
            return "bad gene section!";
        }
    }
    public void testSimpleGene (){
        // Clear the terminal to avoid confusing
        System.out.println(\u000C);
        // Calling findSimpleGene with “ATGGGTTAAGTC” 
        // should return the answer with uppercase letters, the gene “ATGGGTTAA”, 
        String dna = "ATGGGTTAAGTC";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("DNA Strand is  " + dna);
        String gene = findSimpleGene(dna,startCodon,stopCodon );
        System.out.println("gene is  " + gene);  
        
        // calling findSimpleGene with “gatgctataat” 
        // should return the answer with lowercase letters, the gene “atgctataa”
        dna = "gatgctataat";
        startCodon = "atg";
        stopCodon = "taa";
        System.out.println("DNA Strand is  " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("gene is  " + gene);
        /*
         * HINT: there are two string methods toUpperCase() and toLowerCase(). 
         * If dna is the string “ATGTAA” then dna.toLowerCase() results in the string “atgtaa”.
         */
    }
}
